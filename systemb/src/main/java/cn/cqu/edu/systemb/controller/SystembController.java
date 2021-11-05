package cn.cqu.edu.systemb.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.cqu.edu.systemb.util.CookieUtils;

@RestController
public class SystembController {

    @RequestMapping("/systemb")
    public String hello(HttpServletRequest request) {
        return "Welcome to system B, " + CookieUtils.getCookieValue(request, "USER_NAME") + "!";
    }
}