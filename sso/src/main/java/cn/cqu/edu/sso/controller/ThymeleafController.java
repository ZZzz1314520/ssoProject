package cn.cqu.edu.sso.controller;

import java.util.Map;
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
    public String index(Model model, HttpServletRequest request, HttpSession session) {
        session = request.getSession();
        System.out.println(session.getAttribute("name"));
        model.addAttribute("name", session.getAttribute("name"));
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("formdata", new FormData());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("formdata") FormData formdata, Map<String, Object> map,
            HttpSession session) throws Exception {
        JSONObject nameAndPwd = new JSONObject();
        nameAndPwd.put("name", formdata.getUsername());
        nameAndPwd.put("pwd", formdata.getPassword());
        JSONObject tk = RequestLogin.sendHttpPost("http://localhost:8080/user/login",
                JSONObject.toJSONString(nameAndPwd));
        System.out.println(formdata.getUsername());
        System.out.println(formdata.getPassword());
        if (tk.get("token") == null) {
            map.put("msg", "用户名或密码错误");
            return "login";
        } else {
            session.setAttribute("token", tk.get("token"));
            session.setAttribute("name", formdata.getUsername());
            session.setAttribute("pwd", formdata.getPassword());
            map.put("msg", "登录成功");
            return "redirect:/index";
        }
    }

    @GetMapping("/index/A")
    public String sysA(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String tk = (String) session.getAttribute("token");
        String name = (String) session.getAttribute("name");
        String pwd = (String) session.getAttribute("pwd");
        JSONObject usr = new JSONObject();
        usr.put("name", name);
        usr.put("pwd", pwd);
        usr.put("token", tk);
        JSONObject rst = RequestLogin.sendHttpPost("http://localhost:8080/user/checkToken",
                JSONObject.toJSONString(usr));
        System.out.println((boolean) rst.get("state") ? "登录成功A" : "未登录");
        if ((boolean) rst.get("state"))
            return "redirect:http://" + request.getServerName() + ":8081/systema";
        else {
            model.addAttribute("formdata", new FormData());
            return "login";
        }
    }

    @GetMapping("/index/B")
    public String sysB(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String tk = (String) session.getAttribute("token");
        String name = (String) session.getAttribute("name");
        String pwd = (String) session.getAttribute("pwd");
        JSONObject usr = new JSONObject();
        usr.put("name", name);
        usr.put("pwd", pwd);
        usr.put("token", tk);
        JSONObject rst = RequestLogin.sendHttpPost("http://localhost:8080/user/checkToken",
                JSONObject.toJSONString(usr));
        System.out.println((boolean) rst.get("state") ? "登录成功B" : "未登录");
        if ((boolean) rst.get("state"))
            return "redirect:http://" + request.getServerName() + ":8082/systemb";
        else {
            model.addAttribute("formdata", new FormData());
            return "login";
        }
    }
}
