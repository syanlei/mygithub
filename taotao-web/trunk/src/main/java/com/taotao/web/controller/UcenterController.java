package com.taotao.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.web.bean.Invocie;
import com.taotao.web.bean.Order;
import com.taotao.web.service.OrderService;
import com.taotao.web.service.UcenterService;

@RequestMapping("ucenter")
@Controller
public class UcenterController {

    @Autowired
    private UcenterService ucenterService;

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表分页显示
     * 
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "my/orders", method = RequestMethod.GET)
    public ModelAndView myOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        ModelAndView mv = new ModelAndView("my-orders");
        Map<String, Object> map = this.ucenterService.queryOrders(page, rows);
        mv.addObject("orders", map);
        mv.addObject("page", page);
        // 总页数
        Integer total = Integer.valueOf(map.get("totle").toString());
        mv.addObject("totalPage", (total + rows - 1) / rows);
        return mv;
    }

    /**
     * 分页搜索订单列表
     * 
     * @param keyWords
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "my/orders/search", method = RequestMethod.POST)
    public ModelAndView searchMyOrder(@RequestParam("keyWords") String keyWords,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        ModelAndView mv = new ModelAndView("my-orders");
        Map<String, Object> map = this.ucenterService.search(keyWords, page, rows);
        mv.addObject("orders", map);
        mv.addObject("page", page);
        // 总页数
        Integer total = Integer.valueOf(map.get("total").toString());
        mv.addObject("totalPage", (total + rows - 1) / rows);
        return mv;
    }

    /**
     * 删除订单，伪删除
     * 
     * @param orderId
     * @return
     */
    @RequestMapping(value = "my/orders/remove/{orderId}", method = RequestMethod.GET)
    public String removeOrders(@PathVariable(value = "orderId") String orderId) {
        this.ucenterService.removeOrder(orderId);
        return "redirect:/ucenter/my/orders.html";
    }

    /**
     * 取消订单
     * 
     * @param orderId
     * @return
     */
    @RequestMapping(value = "my/orders/cancel/{orderId}", method = RequestMethod.GET)
    public String cancelOrder(@PathVariable("orderId") String orderId) {
        this.ucenterService.cancelOrder(orderId);
        return "redirect:/ucenter/my/orders.html";
    }

    /**
     * 还要买
     * 
     * @param orderId
     * @return
     */
    @RequestMapping(value = "my/orders/buy/{orderId}", method = RequestMethod.GET)
    public String buyOrder(@PathVariable("orderId") String orderId) {
        this.ucenterService.buyItemToCart(orderId);
        return "redirect:/cart/show.html";
    }

    /**
     * 查看订单详情
     * 
     * @param orderId
     * @return
     */
    @RequestMapping(value = "view/{orderId}", method = RequestMethod.GET)
    public ModelAndView myOrder(@PathVariable("orderId") String orderId) {
        ModelAndView mv = new ModelAndView("showorder");
        try {
            // 订单信息
            Order order = orderService.queryOrderById(orderId);
            // 发票信息
            Invocie invocie = ucenterService.queryInvocie(orderId);

            mv.addObject("order", order);
            mv.addObject("invocie", invocie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 根据状态查询订单
     * 
     * @param status
     * @param page
     * @param count
     * @return
     */
    @RequestMapping(value = "/queryStatus/{status}", method = RequestMethod.GET)
    public ModelAndView queryOrderByStatus(@PathVariable("status") Integer status,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        ModelAndView mv = new ModelAndView("my-orders");
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (status == 0) {
                map = ucenterService.queryOrders(page, rows);

            } else {
                map = ucenterService.queryOrderByStatus(status, page, rows);

            }
            Integer totle = Integer.valueOf(map.get("totle").toString());
            mv.addObject("orders", map);
            mv.addObject("page", page);
            mv.addObject("totalPage", (totle + rows - 1) / rows);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 根据时间查询订单
     * 
     * @param date
     * @param page
     * @param count
     * @return
     */
    @RequestMapping(value = "/queryTime/{date}", method = RequestMethod.GET)
    public ModelAndView queryOrderByDate(@PathVariable("date") Integer date,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {
        ModelAndView mv = new ModelAndView("my-orders");
        try {
            Map<String, Object> map = ucenterService.queryOrderByTime(date, page, rows);
            Integer totle = Integer.valueOf(map.get("totle").toString());
            mv.addObject("orders", map);
            mv.addObject("page", page);
            mv.addObject("totalpage", (totle + rows - 1) / rows);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return mv;
    }

}
