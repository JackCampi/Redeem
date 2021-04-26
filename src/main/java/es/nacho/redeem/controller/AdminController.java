package es.nacho.redeem.controller;

import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String dashboard(){
        return "adminDashboard";
    }

    @GetMapping(value = "/addemp")
    public String addEmployee(){return WebPageNames.EMPLOYEE_REGISTRATION_FORM;}

    @PostMapping(value = "/addemp")
    public String registerEmployee(){



        return WebPageNames.EMPLOYEE_REGISTRATION_FORM;
    }

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {return new EmployeeRegistrationDto();}

}
