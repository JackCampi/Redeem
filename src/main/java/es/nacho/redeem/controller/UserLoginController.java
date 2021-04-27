package es.nacho.redeem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserLoginController {
    
    @GetMapping
    public String redirectToLogin(){
        return "redirect:/login";
    }

    @GetMapping
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /*@PostMapping
    @RequestMapping("/login")
    public String dashboard(){
        return WebPageNames.EMPLOYEE_DASHBOARD;
    }

    /*@GetMapping
    @RequestMapping("/admin")
    public String homeAdmin(){
        return "index";
    }

    @GetMapping
    @RequestMapping("/emp")
    public String homeEmployee(){
        return "index";
    }*/


}
