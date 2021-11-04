package cn.cqu.edu.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cn.cqu.edu.sso.service.RequestLogin;
import cn.cqu.edu.sso.util.FormData;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("token", 0);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("formdata", new FormData());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("formdata") FormData formdata) throws Exception {
        JSONObject nameAndPwd = new JSONObject();
        nameAndPwd.put("admin", formdata.getUsername());
        nameAndPwd.put("password", formdata.getPassword());
        // String tk = RequestLogin.sendPost("http://42.193.21.190:8080/user/login",
        // nameAndPwd);
        String tk = RequestLogin.sendHttpPost("http://42.193.21.190:8080/user/login",
                JSONObject.toJSONString(nameAndPwd));
        System.out.println(formdata.getUsername());
        System.out.println(formdata.getPassword());
        System.out.println(tk);
        return "result";
    }
}
