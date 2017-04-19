package com.gmobile.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by liwei on 2017/4/19.
 */
public class ImageUtil {

    public static String saveImage(HttpServletRequest request, MultipartFile file, String uploadPath) {
        // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\uploadPath\\文件夹中
        String ext = file.getContentType().split("\\/")[1];
        String newFileName = file.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath(uploadPath);
        realPath = "C:/github/SSM/src/main/webapp/static/images";
        String filePathAndName = null;
        if (realPath.endsWith(File.separator)) {
            filePathAndName = realPath + newFileName;
        } else {
            filePathAndName = realPath + File.separator + newFileName;
        }
        try {
            // 先把文件保存到本地
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFileName));
        } catch (IOException e1) {
            return null;
        }
        return newFileName;
    }

}


