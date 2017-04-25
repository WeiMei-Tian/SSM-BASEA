package com.gmobile.control;

import com.gmobile.domain.User;
import com.gmobile.util.BaseReturn;
import com.gmobile.util.ErrorCode;
import com.gmobile.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2017/3/20.
 */
@Controller
@RequestMapping("/app")
public class MainController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login(){
        return "login";
    }

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    private String loginAction(@Valid @ModelAttribute("user") User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello(){
        return "hello";
    }

    @RequestMapping(value = "/forMain", method = RequestMethod.GET)
    private String froMain(){
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
