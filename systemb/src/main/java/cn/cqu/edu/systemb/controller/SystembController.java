package cn.cqu.edu.systemb.controller;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.cqu.edu.systemb.service.RequestLogin;
import cn.cqu.edu.systemb.util.CookieUtils;

@Controller
public class SystembController {
    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        return "redirect:http://" + request.getServerName() + ":8080/index";
    }

    @GetMapping("/systemb")
    public String sysB(HttpServletRequest request, Model model) throws Exception {
        String tk = CookieUtils.getCookieValue(request, "USER_TOKEN");
        String name = CookieUtils.getCookieValue(request, "USER_NAME");
        String pwd = CookieUtils.getCookieValue(request, "USER_PASSWORD");
        System.out.println("NAME: " + name);
        JSONObject usr = new JSONObject();
        usr.put("name", name);
        usr.put("pwd", pwd);
        usr.put("token", tk);
        JSONObject rst = RequestLogin.sendHttpPost("http://localhost:8080/user/checkToken",
                JSONObject.toJSONString(usr));
        System.out.println((boolean) rst.get("state") ? "登录成功B" : "未登录");
        if ((boolean) rst.get("state")) {
            model.addAttribute("str", "Welcome to system B, " + CookieUtils.getCookieValue(request, "USER_NAME") + "!");
            return "sysb";
        } else {
            return "redirect:http://" + request.getServerName() + ":8080/login?redirect=" + request.getRequestURL();
        }
    }
}