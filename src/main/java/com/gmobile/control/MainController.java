package com.gmobile.control;

import com.gmobile.domain.User;
import com.gmobile.service.UserService;
import com.gmobile.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.javafx.sg.prism.NGShape;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by admin on 2017/3/20.
 */
@Controller
@RequestMapping("/app")
public class MainController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello(){
        return "hello";
    }

    @RequestMapping(value = "/forMain", method = RequestMethod.GET)
    private String froMain(){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute(Constant.USER_INFO);
        logger.error("(((((((((((((((((((((((" + user.getUsername() + "-----" + user.getPassword());
        return "index";
    }

    // 处理上传的
    @RequestMapping("/userInfo")
    public String upload2() {
        return "user/fileupload";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String add(String name, @RequestParam(required = false) MultipartFile file, HttpServletRequest request)
            throws IOException {

        String image = null;
        if (null == file || file.isEmpty()) {
            System.out.println("-----没有上传文件-----");
        } else {
            image = ImageUtil.saveImage(request, file, "/img");
            if(image == null){
                return BaseReturn.response(ErrorCode.FAILURE, null);
            }
            return BaseReturn.response(ErrorCode.SUCCESS, image);
        }
        return BaseReturn.response(ErrorCode.SUCCESS, null);
    }


}
