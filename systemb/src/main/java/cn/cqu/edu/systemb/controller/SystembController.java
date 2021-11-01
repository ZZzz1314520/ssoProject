package cn.cqu.edu.systemb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class SystembController{

    @RequestMapping("/systemb")
    public String hello() {
        return "This is system B!";
    }

    public static void main(String[] args) {
        
    }
}