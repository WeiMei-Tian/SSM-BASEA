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
    <%--<%@include file="head.jsp"%>--%>
    <title>登录界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/sbadmin/bootstrap/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/sbadmin/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/userJs.js"></script>
    <script src="${pageContext.request.contextPath}/assets/common/showTip.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/sbadmin/bootstrap/dist/js/bootstrap.min.js"></script>
    <style>
        body{ text-align:center}
        .div{text-align: center;
            background-image: url("${pageContext.request.contextPath}/static/images/login_bg.jpg");
            position: absolute;left: 50%;top: 50%;
            margin-left: -300px;margin-top: -200px;}
        .div{ width:600px;
            height:400px;
            border:2px solid #EFF9F9;
        }
        .div_btn{display: inline-block}
        .tip{margin-top: 15px;font-size: 25px}
        .name{margin-top: 35px}
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
        .errorMsg{color: #761c19}
        .sumbit{margin-top: 30px}
        .reg_div{margin-top: 15px;
            text-align: left;
            width:350px}
    </style>
</head>
<body>

<div id="wrapper">

    <!-- 添加，修改用户的模态框 -->
    <div class="modal fade" id="addEditModal" tabindex="-1" role="dialog"
         aria-labelledby="addEditModalTitle" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addEditModalTitle"></h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">

                                <input id="username" type="text" class="form-control"
                                       placeholder="请输入用户名  *可以是字母，数字，下画线_或其组合)" onkeyup="this.value=this.value.replace(/[^\w_]/g,'')">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">登录密码</label>
                            <div class="col-sm-5">

                                <input id="password" type="text" class="form-control"
                                       placeholder="请输入登录密码">
                            </div>
                            <div class="col-sm-5">
                                <button id="setDefaultPwd" class="btn btn-success" onclick="document.getElementById('password').value=123456">初始密码为123456</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消</button>
                    <button id="btnRegisiter" type="button" class="btn btn-primary">注册</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

    <div class="div">
        <form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/loginAction">
            <div class="tip">欢迎你访问燃烧哥的Hello World！！！</div>
            <div class="name">
                <div class="col1">
                    用户名：
                </div>
                <div class="col3">
                    <form:input path="username" />
                </div>
            </div>
            <div>
                <div class="col1">
                </div>
                <div class="col2">
                    <form:errors path="username"></form:errors>
                </div>
            </div>

            <div class="password">
                <div class="col1">
                    密码：
                </div>
                <div class="col3">
                    <form:password path="password" />
                </div>
            </div>
            <div>
                <div class="col1">
                </div>
                <div class="col2">
                    <form:errors path="password"></form:errors><br/>
                </div>
            </div>

            <div class="div_btn">
                <input class="sumbit" type="submit" value="登录" /><br/>
            </div>

            <label class="errorMsg">${error}</label>
        </form:form>
        <br/>
        <button id="regisiter">注册</button>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        app.path(
            '${pageContext.request.contextPath}'
        );
        app.init()
    })
</script>
</html>
