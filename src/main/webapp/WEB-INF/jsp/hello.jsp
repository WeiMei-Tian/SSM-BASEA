<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="../jsp/head.jsp"%>
    <title>Title</title>
    </head>
<body>
    <%   String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <p>
        今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
    </p>
    <br/>

    <P>
        请求地址是： <%=basePath%>  相对地址时:
    </P>
    <br/>
    <a href="${pageContext.request.contextPath}/user/imgInfo">进入用户图片上传界面</a>

</body>
<script type="text/javascript">
    $(function(){
        console.info("======ctx is====" + ${pageContext.request.contextPath});
    })
</script>
</html>
