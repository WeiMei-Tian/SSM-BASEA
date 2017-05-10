<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="tag.jsp"%>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/sbadmin/bootstrap/dist/css/bootstrap.min.css">
    <!-- DataTables CSS -->
    <link
            href="${pageContext.request.contextPath}/assets/sbadmin/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
            rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="${pageContext.request.contextPath}/assets/sbadmin/datatables-responsive/css/dataTables.responsive.css"
          rel="stylesheet">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/assets/sbadmin/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/common/showTip.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/sbadmin/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- DataTables JavaScript -->
    <script
            src="${pageContext.request.contextPath}/assets/sbadmin/datatables/media/js/jquery.dataTables.min.js"></script>
    <script
            src="${pageContext.request.contextPath}/assets/sbadmin/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

<p>我拥有的角色如下：</p>
<c:forEach items="${roles}" var="role">
    <tr>
        <td align="center">${role}</td>
    </tr>
</c:forEach>
<br/>
<br/>
<p>我拥有的操作权限如下：</p>
<c:forEach items="${funs}" var="fun">
    <tr>
        <td align="center">${fun}</td>
    </tr>
</c:forEach>
<br/>
<br/>
<br/>
<br/>

<div id="wrapper">

    <!-- 删除提示模态框 -->
    <div id="deleteModal" class="modal fade" role="dialog"
         aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="gridSystemModalLabel">提示信息</h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">数据删除后不可回复，确定删除？</div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消</button>
                    <button id="btnDelete" type="button" class="btn btn-primary">确定删除</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    
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
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分配角色</label>
                            <div class="col-sm-10">
                                <button class="btn btn-info" id="btnAllocatePermission">
                                    分配角色
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消</button>
                    <button id="btnRegisiter" type="button" class="btn btn-primary">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <button id="addUser" type="button" class="btn btn-success">添加用户</button>
                    <button id="getRoles" type="button" class="btn btn-success">获取所有角色</button>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover"
                               id="dataTables-example">
                            <thead>
                            <tr>
                                <th>用户名</th>
                                <th>昵称ID</th>
                                <th>重置密码</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user" varStatus="status">
                                <tr>
                                    <td>${user.username}</td>
                                    <td>${user.id}</td>
                                    <td><button type="button"
                                                class="btn btn-outline btn-success btn-xs" accesskey="${user.id }">重置密码</button></td>
                                    <td>
                                        <button type="button" accesskey="${user.id }"
                                                class="btn btn-outline btn-primary btn-xs">编辑</button>
                                        <button type="button" accesskey="${user.id }"
                                                class="btn btn-outline btn-danger btn-xs">删除</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>

</div>

</body>

<script>

    $(document).ready(function(){

        var location = (window.location+'').split('/');
        var basePath = location[0]+'//'+location[2];

        // 添加修改的模态框
        var addEditModal = $("#addEditModal");

        // 点击添加用户按钮
        var isShowPassword;
        $("#addUser").click(function() {

            console.info("you click the btn add")
            isShowPassword=true;
            $("#password").parent().parent().show();
            $("#addEditModalTitle").text("添加用户");
            $("#btnSubmit").text("确定添加");
            $("#username").val("");
            $("#username").attr("disabled",false);

            // 显示模态框
            addEditModal.modal({
                show : true,
            });

        });

        $("#getRoles").click(function(){
            console.info("you click the btn getRoles")
            getAllRoles();
        });

        function getAllRoles(){
            $.ajax({
                url : '${pageContext.request.contextPath}' + '/user/roles',
                type : 'GET',
                success: function(data) {
                    var result = eval(data);
                    console.info(result.data[0].roleName);
                    ShowSuccess('获取角色数据成功');
                    setTimeout(function() {
                        window.location.reload();
                    }, 1000);
                },
                error: function(data) {
                    ShowFailure("操作失败："+data);
                }
            })
        }

        $('#btnRegisiter').click(function(){

            var username=$("#username").val().trim();
            var password=$("#password").val().trim();
            if (username.length == 0 || username.length > 15) {
                $("#username").parent().addClass("has-error");
                $("#username").focus();
                setTimeout(function() {
                    $("#username").parent().removeClass("has-error");
                }, 1500);
                return;
            }
            if (password.length == 0 || password.length <6){
                $("#password").parent().addClass("has-error");
                $("#password").focus();
                setTimeout(function() {
                    $("#password").parent().removeClass("has-error");
                }, 1500);
                return;
            }

            $.ajax({
                url : basePath + '/regisiterAction',
                type : 'POST',
                data : JSON.stringify({'username': username, 'password': password}),
                contentType : "application/json",
                dataType : "json",
                success : function(data){
                    var result = eval(data);
                    if(result.code == '0'){
                        ShowSuccess('注册用户成功');
                    }else if(result.code == '544'){
                        ShowSuccess('用户已存在');
                    }else{
                        ShowSuccess('注册用户失败');
                    }
                    setTimeout(function() {
                        window.location.reload();
                    }, 500);
                },
                error : function(data){
                    ShowSuccess('注册用户失败');
                }

            });
        });

        var deleteModal = $('#deleteModal');
        var deleteId;
        // 删除按钮
        $("button.btn-danger").click(function(){
            deleteModal.modal({
                show : true,
            });
            deleteId = $(this).attr("accesskey");

        })

        $("#btnDelete").click(function () {
            deleteUser(deleteId);
            deleteModal.hide()
        });

        function deleteUser(id){
            $.ajax({
                url : basePath + '/user/delete/'+id,
                type : 'GET',
                dataType : "json",
                success: function(data) {
                    var result = eval(data);
                    console.info("-----" + result.code);
                    if(result.code == '0'){
                        ShowSuccess("成功删除"+result.data+"条数据");
                    }else{
                        ShowFailure("操作失败："+data);
                    }
                    setTimeout(function() {
                        window.location.reload();
                    }, 1000);
                },
                error: function(data) {
                    ShowFailure("操作失败："+data);
                }
            })
        }

        // 表格居中
        $('td').attr("class", "text-center");
        $('th').attr("class", "text-center");
        // 分页插件
        $('#dataTables-example').DataTable({
            bSort : false,
            "sPaginationType" : "full_numbers",
            "oLanguage" : {
                "sLengthMenu" : "每页显示 _MENU_ 条记录",
                "sZeroRecords" : "抱歉， 没有找到",
                "sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty" : "没有数据",
                "sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
                "sZeroRecords" : "没有检索到数据",
                "sSearch" : "搜索:",
                "oPaginate" : {
                    "sFirst" : "首页",
                    "sPrevious" : "上一页",
                    "sNext" : "下一页",
                    "sLast" : "尾页"
                }
            }
        });
    })
</script>
</html>
