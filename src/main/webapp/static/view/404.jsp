<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404错误，您所访问的页面不存在！</title>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="../css/base.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%   String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
</head>

<body>
<div id="errorpage">
    <div class="tfans_error">
        <div class="logo"></div>
        <div class="errortans clearfix">
            <div class="e404"></div>
            <img src="../images/404.jpg" alt="404">
            <p><b>出错啦！</b></p>
            <p>您访问的页面不存在</p>
            <div class="bt" ><a href="<%=basePath%>app/forMain">返回首页</a></div>
        </div>
    </div>
</div>


</body>
</html>
