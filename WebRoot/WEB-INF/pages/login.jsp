<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	response.setHeader("progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	//得到自定的登录错误提示信息
	String message = (String) request.getSession().getAttribute(
			"LoginFailureMessage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云融支付管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css"
	href="../../template/admin/css/base.css" />
<script src="../../js/jquery-1.7.2.js" type="text/javascript"></script>
<script type="text/javascript" src="../../template/admin/js/base.js"></script>
<script type="text/javascript" src="../../template/admin/js/admin.js"></script>
<script type="text/javascript" charset="utf-8">
$(function() {
	//验证码
		var $captchaImage = $("#captchaImage");
		// 刷新验证码
		$captchaImage.click(function() {
			var timestamp = (new Date()).valueOf();
			var imageSrc = $captchaImage.attr("src");
			if (imageSrc.indexOf("?") >= 0) {
				imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
			}
			imageSrc = imageSrc + "?timestamp=" + timestamp;
			$captchaImage.attr("src", imageSrc);
		}); 
		//登录失败返回错误信息
		<%if (message != null) {%>
		  $.dialog({type: "error", content: "<%=message%> ",
			modal : true,
			autoCloseTime : 5000
		});
<%}%>
	});

</script>
</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://demo.dwzjs.com"><img
					src="../../themes/default/images/login_logo.gif" /> </a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a>
						</li>
						<li><a href="#">反馈</a>
						</li>
						<li><a href="#" target="_blank">帮助</a>
						</li>
					</ul>
				</div>
				<h2 class="login_title">
					<img src="/themes/default/images/login_title.png" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="../login.action" method="post">
					<p>
						<label>用户名：</label> <input type="text" name="username" size="17"
							class="login_input" />
					</p>
					<p>
						<label>密码：</label> <input type="password" name="password"
							size="17" class="login_input" />
					</p>
					<p>
						<label>验证码：</label> <input class="code" type="text" size="4"
							maxlength="4" id="captcha" name="captcha" /><span><img
							id="captchaImage" src="../captcha.jpeg" style="cursor: pointer ;"
							alt="看不清，换一张" /> </span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img src="../../themes/default/images/login_banner.jpg" />
			</div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">下载驱动程序</a>
					</li>
					<li><a href="#">如何安装密钥驱动程序？</a>
					</li>
					<li><a href="#">忘记密码怎么办？</a>
					</li>
					<li><a href="#">为什么登录失败？</a>
					</li>
				</ul>
				<div class="login_inner">
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
				</div>
			</div>
		</div>
		<div id="login_footer">Copyright &copy; 2015 www.yunrong.com
			Inc. All Rights Reserved.</div>
	</div>
</body>
</html>