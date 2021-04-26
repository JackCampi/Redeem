package es.nacho.redeem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginController {

    @GetMapping
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping
    @RequestMapping("/admin")
    public String homeAdmin(){
        return "index";
    }

    @GetMapping
    @RequestMapping("/emp")
    public String homeEmployee(){
        return "index";
    }

}
