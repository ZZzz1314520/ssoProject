package cn.cqu.edu.sso.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.cqu.edu.sso.domain.User;
import com.alibaba.fastjson.JSONObject;
import cn.cqu.edu.sso.service.LoginService;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController
public class LoginController {
    // 用户登录获取令牌
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody User user) {
        LoginService loginService = new LoginService();
        return loginService.loginPasswordCheck(user);
    }

    // 检验令牌
    @RequestMapping(value = "/user/checkToken", method = RequestMethod.POST)
    public JSONObject checkToken(@RequestBody User user) {
        System.out.println("校验");
        LoginService loginService = new LoginService();
        JSONObject json = new JSONObject();
        json.put("state", loginService.tokenCheck(user.getToken(), user.getName(), user.getPwd()));
        return json;
    }
}
