/**
 * Created by liwei on 2017/5/3.
 */
$(document).ready(function () {

    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2];
    console.info('base path is --' + basePath)

    $("#js").click(function () {
        alert("提示信息！");
        console.info("safwfwefwef")
    });

    $('#regisiter').click(function(){
        // 添加修改的模态框
        var addEditModal = $("#addEditModal");
        $("#password").parent().parent().show();
        $("#addEditModalTitle").text("注册用户");
        $("#btnRegisiter").text("确定添加");
        $("#username").val("");
        $("#username").attr("disabled",false);
        // 显示模态框
        addEditModal.modal({
            show : true,
        });
    });

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
});
