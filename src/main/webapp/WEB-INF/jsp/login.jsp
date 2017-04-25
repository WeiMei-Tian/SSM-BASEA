<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2017/4/25
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="user" class="com.gmobile.domain.User" scope="request" ></jsp:useBean>

<%--<%@include file="tag.jsp"%>--%>
<html>
<head>
    <title>登录界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        /*body{ text-align:center}*/
        .div{text-align: center;
            background-image: url("../images/login_bg.jpg");
            position: absolute;left: 50%;top: 50%;
            margin-left: -300px;margin-top: -200px;}
        .div{ width:600px;
            height:400px;
            border:2px solid #EFF9F9;
        }
        .tip{margin-top: 15px}
        .name{margin-top: 20px}
        .col1{
            width: 100px;
            display: inline-block;
            text-align: center;
        }
        .col2,.col3{
            width: 200px;
            display: inline-block;
            text-align: center;
        }
        .sumbit{margin-top: 30px}
    </style>
</head>
<body>
<div class="div">
        <form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/app/loginAction">
            <div class="tip">登陆界面</div>
            <div class="name">
                <div class="col1">
                    用户名：
                </div>
                <div class="col3">
                    <form:input path="name" />
                </div>
            </div>
            <div>
                <div class="col1">
                </div>
                <div class="col2">
                    <form:errors path="name"></form:errors>
                </div>
            </div>

            <div class="password">
                <div class="col1">
                    密码：
                </div>
                <div class="col3">
                    <form:input path="password" />
                </div>
            </div>
            <div>
                <div class="col1">
                </div>
                <div class="col2">
                    <form:errors path="password"></form:errors><br/>
                </div>
            </div>
            <input class="sumbit" type="submit" value="Submit" />

        </form:form>
</div>
</body>
<script type="text/javascript">
    $(function() {

        })
</script>
</html>
