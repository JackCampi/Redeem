package es.nacho.redeem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @GetMapping
    public String dashboard(){
        return WebPageNames.EMPLOYEE_DASHBOARD;
    }

}
