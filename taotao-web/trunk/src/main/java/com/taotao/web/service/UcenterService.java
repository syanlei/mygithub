package com.taotao.web.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.bean.HttpResult;
import com.taotao.common.service.ApiService;
import com.taotao.web.bean.Invocie;
import com.taotao.web.bean.OrderItem;
import com.taotao.web.bean.OrderShipping;
import com.taotao.web.threadlocal.UserThreadLocal;
import com.taotao.web.utils.JsonUtil;

@Service
public class UcenterService {

	@Value("${TAOTAO_ORDER_URL}")
	private String TAOTAO_ORDER_URL;

	@Value("${TAOTAO_SEARCH_URL}")
	private String TAOTAO_SEARCH_URL;

	@Value("${TAOTAO_ORDER_QUERY_ORDER}")
	private String TAOTAO_ORDER_QUERY_ORDER;

	@Value("${TAOTAO_ORDER_INVOCIE}")
	private String TAOTAO_ORDER_INVOCIE;

	@Autowired
	private ApiService apiService;

	@Autowired
	private CartLoginService cartLoginService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 订单列表分页显示
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public Map<String, Object> queryOrders(Integer page, Integer rows) {

		String URL = UserThreadLocal.get().getUsername();

		String url = TAOTAO_ORDER_URL + "/order/query/" + URL + "/" + page
				+ "/" + rows;
		try {
			String jsonData = this.apiService.doGet(url);
			if (jsonData == null) {
				return new HashMap<String, Object>(0);
			}
			return MAPPER.readValue(jsonData, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>(0);
	}

	/**
         * 分页搜索订单列表
         * 
         * @param keyWords
         * @param page
         * @param rows
         * @return
         */
	public Map<String, Object> search(String keyWords, Integer page,
			Integer rows) {
		String url = TAOTAO_SEARCH_URL + "/order/search";
		try {
			HttpResult httpResult = this.apiService.doPost(
					url,
					JsonUtil.start("keyWords", keyWords)
							.put("userId", UserThreadLocal.get().getId())
							.put("page", page).put("rows", rows).get());
			if (httpResult.getCode() != 200) {
				return new HashMap<String, Object>(0);
			}
			JsonUtil jsonUtil = JsonUtil.getInstance();
			String jsonData = httpResult.getData();
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			jsonUtil.put("total", jsonNode.get("total").asLong());

			ArrayNode orderIds = (ArrayNode) jsonNode.get("list");
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			for (JsonNode node : orderIds) {
				String orderId = node.get("orderId").asText();
				if (data.containsKey(orderId)) {
					continue;
				}
				// 通过订单号查询订单
				data.put(orderId, queryOrderByOrderId(orderId));
			}
			jsonUtil.put("data", data.values());

			return jsonUtil.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>(0);
	}

	private Object queryOrderByOrderId(String orderId) {
		String url = TAOTAO_ORDER_URL + "/order/query/" + orderId;

		try {
			String jsonData = this.apiService.doGet(url);

			if (jsonData == null) {
				return new HashMap<String, Object>(0);
			}
			return MAPPER.readValue(jsonData, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new HashMap<String, Object>(0);
	}

	public void removeOrder(String orderId) {
		String url = TAOTAO_ORDER_URL + "/order/changeOrderStatus";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("status", -1);
			String json = MAPPER.writeValueAsString(map);
			HttpResult doPostJson = this.apiService.doPostJson(url, json);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelOrder(String orderId) {
		String url = TAOTAO_ORDER_URL + "/order/changeOrderStatus";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("status", 6);
			String json = MAPPER.writeValueAsString(map);
			this.apiService.doPostJson(url, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buyItemToCart(String orderId) {
		String url = TAOTAO_ORDER_URL + "/order/query/" + orderId;
		try {
			String jsonData = this.apiService.doGet(url);
			if (StringUtils.isNotEmpty(jsonData)) {
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				ArrayNode items = (ArrayNode) jsonNode.get("orderItems");
				for (JsonNode node : items) {
					Long itemId = node.get("itemId").asLong();
					cartLoginService.addItemToCart(itemId, null);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询发票
	public Invocie queryInvocie(String orderId) {
		try { // http://order.taotao.com/invocie/queryByInvocieId/{invocieId}
			String InvocieId = orderId + "invocieId";
			String url = TAOTAO_ORDER_INVOCIE + "queryByInvocieId/" + InvocieId;
			String jsonData = apiService.doGet(url);
			if (StringUtils.isNotEmpty(jsonData)) {
				return MAPPER.readValue(jsonData, Invocie.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	// 查询item
//	public List<OrderItem> queryItemId(String orderId) {
//
//		try {
//			String url = TAOTAO_ORDER_QUERY_ORDER + "/orderItem/" + orderId;
//			String jsonData = apiService.doGet(url);
//			if (StringUtils.isNotEmpty(jsonData)) {
//				return MAPPER.readValue(jsonData, MAPPER.getTypeFactory()
//						.constructCollectionType(List.class, OrderItem.class));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 根据状态查询订单
	 * @param status
	 * @param page
	 * @param count
	 * @return
	 */
	public Map<String, Object> queryOrderByStatus(Integer status, Integer page,
			Integer count) {
		try {
			String url = TAOTAO_ORDER_QUERY_ORDER + "/status/"
					+ UserThreadLocal.get().getUsername() + "/" + status + "/"
					+ page + "/" + count;
			String jsonData = apiService.doGet(url);
			if (StringUtils.isNotEmpty(jsonData)) {
				return MAPPER.readValue(jsonData, Map.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据时间查询订单
	 * 
	 * @param date
	 * @param page
	 * @param count
	 * @return
	 */
	public Map<String, Object> queryOrderByTime(Integer date, Integer page,
			Integer count) {
		try {
			String url = TAOTAO_ORDER_QUERY_ORDER + "/time/"
					+ UserThreadLocal.get().getUsername() + "/" + date + "/"
					+ page + "/" + count;
			String jsonData = apiService.doGet(url);
			if (StringUtils.isNotEmpty(jsonData)) {
				return MAPPER.readValue(jsonData, Map.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
