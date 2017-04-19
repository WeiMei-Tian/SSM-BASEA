package com.gmobile.control;

import com.gmobile.util.BaseReturn;
import com.gmobile.util.ErrorCode;
import com.gmobile.util.ImageUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by admin on 2017/3/20.
 */
@Controller
@RequestMapping("/app")
public class MainController {

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
        return "fileupload";
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
