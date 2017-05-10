package com.gmobile.control;

import com.gmobile.domain.User;
import com.gmobile.domain.UserRole;
import com.gmobile.service.UserService;
import com.gmobile.util.BaseReturn;
import com.gmobile.util.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liwei on 2017/4/24.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 处理上传的
    @RequestMapping("/imgInfo")
    public String upload2() {
        return "user/fileupload";
    }

    @RequestMapping(value = "roles",method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public  String getAllRoles(){
        try {
            List<UserRole> roleList = userService.selectAllRoles();
            if(roleList != null && roleList.size() > 0){
                return BaseReturn.response(roleList);
            }else {
                return BaseReturn.response(ErrorCode.FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseReturn.response(ErrorCode.FAILURE);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable String id) {

        int deleteCount = 0;
        try {
            deleteCount = userService.deleteUser(Integer.parseInt(id));
            if (deleteCount > 0) {
                return BaseReturn.response(ErrorCode.SUCCESS, deleteCount);
            } else {
                return BaseReturn.response(ErrorCode.FAILURE, "删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseReturn.response(ErrorCode.FAILURE, "删除用户失败");
        }
    }

    @RequestMapping(value = "/getusers/page", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getUsersByPage(@RequestParam int pageIndex, @RequestParam int pageSize){
        List<User> users = null;
        try {
            users = userService.selectUsersByPage(pageIndex,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseReturn.response(ErrorCode.FAILURE,"获取用户列表失败                                    ");
        }
        return BaseReturn.response(ErrorCode.SUCCESS, users);
    }
}
