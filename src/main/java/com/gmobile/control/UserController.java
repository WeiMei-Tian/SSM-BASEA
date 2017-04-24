package com.gmobile.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liwei on 2017/4/24.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    // 处理上传的
    @RequestMapping("/imgInfo")
    public String upload2() {
        return "fileupload";
    }
}
