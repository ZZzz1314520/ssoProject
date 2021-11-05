package cn.cqu.edu.systema.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.cqu.edu.systema.util.CookieUtils;

@RestController
public class SystemaController {

    @RequestMapping("/systema")
    public String hello(HttpServletRequest request) {
        return "Welcome to system A, " + CookieUtils.getCookieValue(request, "USER_NAME") + "!";
    }
}