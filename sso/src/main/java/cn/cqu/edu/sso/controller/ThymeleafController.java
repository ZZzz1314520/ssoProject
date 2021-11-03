package cn.cqu.edu.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cn.cqu.edu.sso.util.FormData;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("formdata", new FormData());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("formdata") FormData formdata) {
        System.out.println(formdata.getUsername());
        System.out.println(formdata.getPassword());
        return "result";
    }
}
