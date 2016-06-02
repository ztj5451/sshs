<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆超时</title>
<script type="text/javascript">
	//强制跳转到新窗口
	if (self.location != top.location) {
		top.location.href = location.href;
	}
</script>

</head>
<body>
	<center>会话超时!您没有登录,或登录超时,请重新登录!</center>
	<center>
		<a href="<%=path%>/login.jsp">登陆</a>
	</center>
</body>
</html>