<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link type="text/css" rel="stylesheet" href="/js/safe/saved_resource">
	<link type="text/css" rel="stylesheet" href="/js/safe/myjd.safe.css" source="combo">
	<link type="text/css" rel="stylesheet" href="/js/safe/saved_resource(1)" source="widget">
	<script type="text/javascript" src="/js/safe/base-v1.js"></script>
	<script type="text/javascript" src="/js/safe/saved_resource(2)"></script>
	<script type="text/javascript" src="/js/safe/saved_resource(3)"></script> 
	<script type="text/javascript" src="/js/safe/saved_resource(4)" source="widget"></script>
	<script type="text/javascript" src="/js/safe/BigInt.js"></script>
    <script type="text/javascript" src="/js/safe/RSA.js"></script>
    <script type="text/javascript" src="/js/safe/Barrett.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/safe/validate_findPwd.js"></script>
    <link href="/css/taotao.css" rel="stylesheet"/>
    <title>找回密码</title>
<script type="text/javascript" async="" src="/js/safe/loadFa.js"></script></head>


</head>
<body>
	<%-- <div>
	<jsp:include page="../commons/header.jsp" />
	</div> --%>
	<div id="container">
		<div class="w pt10">
			<div id="main">
				<div class="g-0">
					<div id="content">
						<div class="mod-main mod-comm">
							<div class="mt">
								<h3>找回密码</h3>
							</div>
							<div class="mc">
								<div id="sflex04" class="stepflex ">
									<dl class="first doing">
										<dt class="s-num">1</dt>
										<dd class="s-text">
											填写账户名<s></s><b></b>
										</dd>
										<dd></dd>
									</dl>
									<dl class="normal">
										<dt class="s-num">2</dt>
										<dd class="s-text">
											验证身份<s></s><b></b>
										</dd>
									</dl>
									<dl class="normal">
										<dt class="s-num">3</dt>
										<dd class="s-text">
											设置新密码<s></s><b></b>
										</dd>
									</dl>
									<dl class="last">
										<dt class="s-num">&nbsp;</dt>
										<dd class="s-text">
											完成<s></s><b></b>
										</dd>
									</dl>
								</div>

								<div class="form formno">
									<div class="item">
										<span class="label">账户名：</span>
										<div class="fl">
											<input type="text" class="itxt" tabindex="1"
												onfocus="usernameOnfocus();" onblur="usernameOnblur();"
												id="username" name="username" value="用户名/邮箱/已验证手机">
											<div class="clr"></div>
											<label id="username_error" calss=""></label>
											<!-- <div class="msg-text">若该手机号无法接收验证短信，请拨打客服电话400-606-5500转7申诉更改验证手机</div> -->
										</div>
										<div class="clr"></div>
									</div>

									<!-- <div class="item">
										<span class="label">验证码：</span>
										<div class="fl">
											<input type="text" id="authCode" name="authCode" tabindex="2"
												onblur="authCodeBlur();" class="itxt"> 
												<label><img
												style="cursor: pointer; width: 100px; height: 26px;"
												alt="验证码"
												onclick="verc(&#39;aaa803a2-845e-45b2-8fa0-f54031006716&#39;)"
												src="./js/image" class="ml10" id="JD_Verification1">看不清？
												<a class="ftx-05"
												href="javascript:verc('aaa803a2-845e-45b2-8fa0-f54031006716');">换一张</a></label>
											<div class="clr"></div>
											<div class="msg-error" id="authCode_error"></div>
										</div>
										<div class="clr"></div>
									</div> -->
									<div class="item">
										<span class="label">&nbsp;</span>
										<div class="fl">
											<a href="javascript:void(0);" onclick="doIndex();" id="findPwdSubmit" class="btn-5">提交</a>
										</div>
										<div class="clr"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<span class="clr"></span>
			</div>
		</div>
	</div>
</body>
</html>