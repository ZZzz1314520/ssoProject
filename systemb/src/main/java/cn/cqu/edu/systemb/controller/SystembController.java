package cn.cqu.edu.systemb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystembController {

    @RequestMapping("/systemb")
    public String hello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("name"));
        return "Welcome to system B, " + session.getAttribute("name") + "!";
    }
}