<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统一认证中心</title>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css" />
    <script type="text/javascript" src="/static/js/jquery-latest.min.js"></script>
    <script type="text/javascript" src="/static/js/placeholder.js"></script>
</head>
<body>
${errorMsg}
<form id="slick-login" method="post" action="/login">
    <input type="hidden" name="redirectUrl" value="${redirectUrl}">
    <label>username</label><input type="text" name="username" class="placeholder" placeholder="账号">
    <label>password</label><input type="password" name="password" class="placeholder" placeholder="密码">
    <input type="submit" value="登录">
</form>
</body>
</html>
