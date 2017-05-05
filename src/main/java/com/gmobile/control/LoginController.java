package com.gmobile.control;

import com.gmobile.domain.User;
import com.gmobile.service.UserService;
import com.gmobile.util.BaseReturn;
import com.gmobile.util.Constant;
import com.gmobile.util.ErrorCode;
import com.gmobile.util.MD5Util;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liwei on 2017/5/4.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(MainController.class);

    @RequestMapping(value = "/regisiter", method = RequestMethod.GET)
    private String regisiter(Model model, HttpServletRequest request){
        User user = new User();
        model.addAttribute("user",user);
        String error = request.getParameter("error");
        model.addAttribute("error",error);
        return "regisiter";
    }

    @RequestMapping(value = "/regisiterAction", method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    @ResponseBody
    private String regisiterAction(@RequestBody User user ,
                                   Model model){
        Date current_date = new Date();
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setCreateTime(SimpleDateFormat.format(current_date.getTime()));
        user.setStatus("enable");
        user.setPassword(MD5Util.getMD5Str(user.getPassword()));

        try{
            int statue = userService.addUser(user);
            if(statue > 0){
                return BaseReturn.response(ErrorCode.SUCCESS);
            }else {
                return BaseReturn.response(ErrorCode.FAILURE);
            }
        }catch (Exception e){
            Throwable cause = e.getCause();
            if(cause instanceof MySQLIntegrityConstraintViolationException){
                return BaseReturn.response(ErrorCode.USER_ACCOUNT_EXISTS);
            }
            return BaseReturn.response(ErrorCode.FAILURE);
        }
    }

    @RequestMapping(value = "/login")
    private String login(Model model, HttpServletRequest request,
                         HttpServletResponse response){
        User user = new User();
        model.addAttribute("user",user);
        String error = request.getParameter("error");
        model.addAttribute("error",error);
        return "login";
    }

    @RequestMapping(value = "/loginAction")
    private String loginAction(@Valid @ModelAttribute("user") User user ,
                               BindingResult bindingResult,
                               Model model, HttpSession session){
        logger.error("*****************************************************");
        if(bindingResult.hasErrors()){
            return "redirect:login";
        }
        user.setPassword(MD5Util.getMD5Str(user.getPassword()));
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录
            subject.login(token);
            if(subject.isAuthenticated()){
                session.setAttribute(Constant.USER_INFO, user);
                session.setAttribute(Constant.SESSION_LOGIN_DES_KEY, "123456789");
                return "redirect:app/forMain";
            }else {
                model.addAttribute("error","登录失败");
                return "redirect:login";
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            model.addAttribute("error","登录失败");
            return "redirect:login";
        }
    }
}
