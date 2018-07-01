<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/js/safe/saved_resource">
	<link type="text/css" rel="stylesheet" href="/js/safe/myjd.safe.css" source="combo">
	<link type="text/css" rel="stylesheet" href="/js/safe/saved_resource(1)" source="widget">
	<script type="text/javascript" src="/js/safe/saved_resource(2)"></script>
	<script type="text/javascript" src="/js/safe/saved_resource(3)"></script>
    <script src="/js/safe/base-v1.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/safe/saved_resource(4)" source="widget"></script>
    <script charset="utf-8" type="text/javascript" src="/js/safe/validate_findPwd.js"></script>
    <script type="text/javascript" async="" src="/js/safe/loadFa.js"></script>
    <script type="text/javascript" src="/js/safe/BigInt.js"></script>
    <script type="text/javascript" src="/js/safe/RSA.js"></script>
    <script type="text/javascript" src="/js/safe/Barrett.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="container">
    <div class="w">
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
                                <dl class="normal done">
                                    <dt class="s-num">2</dt>
                                    <dd class="s-text">验证身份<s></s><b></b></dd>
                                </dl>
                                <dl class="normal doing">
                                    <dt class="s-num">3</dt>
                                    <dd class="s-text">设置新密码<s></s><b></b></dd>
                                </dl>
                                <dl class="last">
                                    <dt class="s-num">&nbsp;</dt>
                                    <dd class="s-text">完成<s></s><b></b></dd>
                                </dl>
                            </div>

                            <div class="form formno">
                                <div class="item" style="display:none">
                                    <span class="label">历史收货人手机号码：</span>
                                    <div class="fl">
                                        <input type="text" tabindex="1" onfocus="mobileFocus();" onblur="mobileBlur();" class="itxt" id="mobile">
                                       <!--  <input type="text"> -->
                                        <label class="blank invisible" id="username_succeed"></label>
                                        <span class="clr"></span>
                                        <label id="mobile_error" class=""></label>
                                    </div>
                                </div>

                                <div class="item">
									<span class="label">
										<img src="/images/safe/icon-quer.png" id="history-name-tip">历史收货人姓名：
									</span>
                                    <div class="fl">
                                        <!-- <input type="text" id="historyName" class="itxt highlight1" onblur="checkHistoryName();" onfocus="historyNameFocus();" tabindex="1"> -->
                                        <input type="text">
                                        <span class="clr"></span>
                                        <label class="msg-error" id="historyName_error"></label>
                                    </div>
                                </div>

                                <div class="item">
                                    <span class="label">新登录密码：</span>
                                    <div class="fl">
                                        <input type="password" id="password" class="itxt" onblur="passwordBlur();" onfocus="passwordFocus();" tabindex="1" value="">
                                        <span class="clr"></span>
                                        <div class="u-safe" style="display: none;" id="pwdstrength">
                                            安全程度：
                                            <i class="safe-rank06"></i>
                                        </div>
                                        <span class="clr"></span>
                                        <label class="" id="password_error"></label>
                                    </div>
                                </div>

                                <div class="item">
                                    <span class="label">确认新密码：</span>
                                    <div class="fl">
                                        <input type="password" id="repassword" class="itxt" onblur="repasswordBlur();" onfocus="repasswordFocus();" tabindex="2" value="">
                                        <span class="clr"></span>
                                        <label class="" id="repassword_error"></label>
                                    </div>
                                </div>

                                <div class="item">
                                    <span class="label">&nbsp;</span>
                                    <div class="fl">
                                        <a href="javascript:void(0);" class="btn-5"  onclick="updatePassword(${user.id})">提交</a>
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