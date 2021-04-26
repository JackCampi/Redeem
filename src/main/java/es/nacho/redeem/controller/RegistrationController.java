package es.nacho.redeem.controller;

import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    @RequestMapping("/admin")
    public String showRegistrationForm(){
        return WebPageNames.ADMIN_REGISTRATION_FORM;
    }

    @GetMapping
    @RequestMapping("/comp")
    public String showCompanyForm(){
        return WebPageNames.COMPANY_REGISTRATION_FORM;
    }

    @PostMapping("/admin")
    public String registerAdminAccount(@ModelAttribute("admin") AdminRegistrationDto adminRegistrationDto, HttpSession session){
        //TODO: web page for exception
        Long nit = (Long) session.getAttribute("nit");

        try{
            userService.registerAdmin(adminRegistrationDto, nit);
            return WebPageNames.ADMIN_DASHBOARD;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/comp")
    public String registerCompany(@ModelAttribute("company") CompanyRegistrationDto companyRegistrationDto, HttpSession session){
        session.setAttribute("nit", companyRegistrationDto.getId());
        return WebPageNames.ADMIN_REGISTRATION_FORM;
    }

    @ModelAttribute("admin")
    public AdminRegistrationDto employeeRegistrationDto(){
        return new AdminRegistrationDto();
    }

    @ModelAttribute("company")
    public CompanyRegistrationDto companyRegistrationDto(){
        return new CompanyRegistrationDto();
    }

}
