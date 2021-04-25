package es.nacho.redeem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/admin")
    public String homeAdmin(){
        return "index";
    }

    @GetMapping("/emp")
    public String homeEmployee(){
        return "index";
    }

}
