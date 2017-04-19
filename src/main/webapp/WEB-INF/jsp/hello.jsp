<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/3/20
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        请求地址是： <%=basePath%>
    </P>
    <br/>
    <%
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            System.out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = request.getHeader(paramName);
            System.out.print("<td> " + paramValue + "</td></tr>\n");
        }
    %>

</body>
</html>
