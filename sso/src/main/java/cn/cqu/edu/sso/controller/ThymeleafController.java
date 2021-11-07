package cn.cqu.edu.sso.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import cn.cqu.edu.sso.service.RequestLogin;
import cn.cqu.edu.sso.util.CookieUtils;
import cn.cqu.edu.sso.util.FormData;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("name", CookieUtils.getCookieValue(request, "USER_NAME"));
        return "index";
    }

    @GetMapping("/login")
    public String login(String redirect, Model model, HttpServletRequest request) {
        model.addAttribute("formdata", new FormData());
        HttpSession session = request.getSession();
        session.setAttribute("redirect", redirect);
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("formdata") FormData formdata, Map<String, Object> map,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject nameAndPwd = new JSONObject();
        nameAndPwd.put("name", formdata.getUsername());
        nameAndPwd.put("pwd", formdata.getPassword());
        JSONObject tk = RequestLogin.sendHttpPost("http://localhost:8080/user/login",
                JSONObject.toJSONString(nameAndPwd));
        if (tk.get("token") == null) {
            map.put("msg", "用户名或密码错误");
            return "login";
        } else {
            CookieUtils.setCookie(request, response, "USER_TOKEN", (String) tk.get("token"));
            CookieUtils.setCookie(request, response, "USER_NAME", formdata.getUsername());
            CookieUtils.setCookie(request, response, "USER_PASSWORD", formdata.getPassword());
            map.put("msg", "登录成功");
            HttpSession session = request.getSession();
            String str = (String) session.getAttribute("redirect");
            System.out.println(str);
            return "redirect:" + (str != null ? str : "/index");
        }
    }

    @GetMapping("/index/A")
    public String sysA(HttpServletRequest request) {
        return "redirect:http://" + request.getServerName() + ":8081/systema";
    }

    @GetMapping("/index/B")
    public String sysB(HttpServletRequest request) {
        return "redirect:http://" + request.getServerName() + ":8082/systemb";
    }
}
