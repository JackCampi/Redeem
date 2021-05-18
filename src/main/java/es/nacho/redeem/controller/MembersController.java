package es.nacho.redeem.controller;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/members")
public class MembersController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/disable")
    private String disableUser(@RequestParam String email){

        companyService.disableEmployee(email);

        return "redirect:/admin/members";
    }

    @ModelAttribute("employeeRow")
    private Employee getEmployee(){
        return new Employee();
    }

}
