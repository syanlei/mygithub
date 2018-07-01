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
    <script src="/js/base-v1.js" type="text/javascript"></script>
	<script charset="utf-8" type="text/javascript" src="/js/safe/validate_findPwd.js"></script>
	<script type="text/javascript" src="/js/safe/saved_resource(4)" source="widget"></script>
	<script type="text/javascript" src="/js/safe/BigInt.js"></script>
    <script type="text/javascript" src="/js/safe/RSA.js"></script>
    <script type="text/javascript" src="/js/safe/Barrett.js"></script>
    <title>找回密码</title>
<script type="text/javascript" async="" src="/js/safe/loadFa.js"></script></head>
</head>
<body>
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
                                <dl class="first done">
                                    <dt class="s-num">1</dt>
                                    <dd class="s-text">填写账户名<s></s><b></b></dd>
                                    <dd></dd>
                                </dl>
                                <dl class="normal doing">
                                    <dt class="s-num">2</dt>
                                    <dd class="s-text">验证身份<s></s><b></b></dd>
                                </dl>
                                <dl class="normal">
                                    <dt class="s-num">3</dt>
                                    <dd class="s-text">设置新密码<s></s><b></b></dd>
                                </dl>
                                <dl class="last">
                                    <dt class="s-num">&nbsp;</dt>
                                    <dd class="s-text">完成<s></s><b></b></dd>
                                </dl>
                            </div>

                            <div class="form formno">
                                <div class="item">
                                    <span class="label">请选择验证身份方式：</span>
                                    <div class="fl">
                                        <select class="selt" id="type" name="" onchange="selectVerifyType();">
                                                                                            <option value="mobile">已验证手机</option>
                                                                                                                                        <option value="email">邮箱</option>
                                                                                    </select>
                                        <div class="clr"></div>
                                    </div>
                                    <div class="clr"></div>
                                </div>

                                                                    <div id="mobileDiv">
                                        <div class="item">
                                            <span class="label">昵称：</span>
                                            <div class="fl">
                                                <strong class="ftx-un"> ${user.username}</strong>
                                                <label class="blank invisible" id="username_succeed"></label>
                                                <div class="clr"></div>
                                                <!-- <div class="msg-text">若该手机号无法接收验证短信，请拨打客服电话400-606-5500转7申诉更改验证手机</div> -->
                                            </div>
                                            <div class="clr"></div>
                                        </div>

                                        <div class="item">
                                            <span class="label">已验证手机：</span>
                                            <div class="fl">
                                                <strong id="mobileSpan" class="ftx-un">${user.showphone }</strong>
                                                <input type="hidden" id="_phone" value=${user.phone }>
                                                <div class="clr"></div>
                                                <!-- <div class="msg-text">若该手机号无法接收验证短信，请拨打客服电话400-606-5500转7申诉更改验证手机</div> -->
                                            </div>
                                            <div class="clr"></div>
                                        </div>

                                        <div class="item">
                                            <span class="label">请填写手机校验码：</span>
                                            <div class="fl">
                                                <input class="itxt" name="code" tabindex="1" id="code" onblure="checkCode();" onfocus="codeFocus();" onblur="codeBlur();" disabled="disabled" type="text">
                                                <a href="javascript:sendFindPwdCode(${user.id });" id="sendMobileCode" class="btn btn-10 ml10"><s></s>获取短信校验码</a>
                                                <div class="clr"></div>
                                                <div id="timeDiv" style="display:none" class="msg-text">校验码已发出，请注意查收短信，如果没有收到，你可以在<strong class="ftx-01">60</strong>秒后要求系统重新发送</div>
                                                <div id="code_error" class="msg-error"></div>
                                            </div>
                                            <div class="clr"></div>
                                        </div>

                                        <div class="item">
                                            <span class="label">&nbsp;</span>
                                            <div class="fl">
                                                <a href="javascript:void(0);" tabindex="8" id="submitCode" disabled="disabled" onclick="validFindPwdCode(${user.id});" class="btn-5">提交</a>
                                            </div>
                                            <div class="clr"></div>
                                        </div>
                                    </div>
                                
                                                                    <div id="emailDiv" style="display:none">
                                        <div class="item">
                                            <span class="label">昵称：</span>
                                            <div class="fl">
                                                <strong class="ftx-un">liu536588218</strong>
                                                <label class="blank invisible" id="username_succeed"></label>
                                                <div class="clr"></div>
                                                <!-- <div class="msg-text">若该手机号无法接收验证短信，请拨打客服电话400-606-5500转7申诉更改验证手机</div> -->
                                            </div>
                                            <div class="clr"></div>
                                        </div>

                                        <div class="item">
                                            <span class="label">邮箱地址：</span>
                                            <div class="fl">
                                                <strong id="mobileSpan" class="ftx-un">l*****7@163.com</strong>
                                                <div class="clr"></div>
                                                <!-- <div class="msg-text">若该手机号无法接收验证短信，请拨打客服电话400-606-5500转7申诉更改验证手机</div> -->
                                            </div>
                                            <div class="clr"></div>
                                        </div>

                                        <div class="item">
                                            <span class="label">&nbsp;</span>
                                            <div class="fl">
                                                <a href="javascript:sendFindPwdEmail(${user.id});" id="sendMail" class="btn-5">发送验证邮件</a>
                                            </div>
                                            <div class="clr"></div>
                                        </div>
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