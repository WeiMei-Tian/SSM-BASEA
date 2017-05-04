package com.gmobile.control;

import com.gmobile.domain.User;
import com.gmobile.service.UserService;
import com.gmobile.util.Constant;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/regisiterAction", method = RequestMethod.POST)
    private String regisiterAction(@Valid @ModelAttribute("user") User user ,
                                   BindingResult bindingResult,
                                   Model model){

        if(bindingResult.hasErrors()){
            return "redirect:regisiter";
        }

        Date current_date = new Date();
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setCreateTime(SimpleDateFormat.format(current_date.getTime()));
        user.setStatus("enable");
        user.setPassword(MD5Util.getMD5Str(user.getPassword()));

        try{
            int statue = userService.addUser(user);
            if(statue > 0){
                return "redirect:login";
            }else {
                model.addAttribute("error","注册失败");
                return "redirect:regisiter";
            }
        }catch (Exception e){
            Throwable cause = e.getCause();
            model.addAttribute("error","注册失败");
            if(cause instanceof MySQLIntegrityConstraintViolationException){
                model.addAttribute("error","账号已存在");
            }
            logger.error("############################");
            logger.error(cause);
            logger.error(e.getMessage());
            return "redirect:regisiter";
        }
    }

    @RequestMapping(value = "/login")
    private String login(Model model, HttpServletRequest request, HttpSession session){
//        if(session.getAttribute(Constant.SESSION_LOGIN_DES_KEY) != null){
//            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//            logger.info(session.getAttribute(Constant.SESSION_LOGIN_DES_KEY));
//            return "redirect:forMain";
//        }
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
