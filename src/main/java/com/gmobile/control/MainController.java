package com.gmobile.control;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        System.out.println("处理文件上传");
        return "hello";
    }

    @RequestMapping(value = "/forMain", method = RequestMethod.GET)
    private String froMain(){
        return "index";
    }

    // 处理上传的
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("file") CommonsMultipartFile file) throws IOException {
        System.out.println("处理文件上传");
        PrintWriter out;
        boolean flag = false;
        if (file.getSize() > 0) {
            String path = "/assets/upload/files/";
            String uploadPath = request.getSession().getServletContext().getRealPath(path);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),
                        new File(uploadPath, file.getOriginalFilename()));
                flag = true;
            } catch (Exception e) {
            }

        }
        out = response.getWriter();
        if (flag == true) {
            out.print("1");
        } else {
            out.print("2");
        }
    }

    // 处理上传的
    @RequestMapping("/upload2")
    public String upload2() {
      return "upload";
    }

}
