package es.nacho.redeem.controller;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    @RequestMapping("/reg")
    public String redirectToRegistrationCompanyForm(){
        return "redirect:/reg/comp";
    }

    @GetMapping
    @RequestMapping("/reg/admin")
    public String showRegistrationForm(){
        return WebPageNames.ADMIN_REGISTRATION_FORM;
    }

    @GetMapping
    @RequestMapping("/reg/comp")
    public String showCompanyForm(){
        return WebPageNames.COMPANY_REGISTRATION_FORM;
    }

    @PostMapping("/admin")
    public String registerAdminAccount(@ModelAttribute("admin") AdminRegistrationDto adminRegistrationDto, HttpSession session){

        Long nit = (Long) session.getAttribute("nit");

        try{
            userService.registerAdmin(adminRegistrationDto, nit);
            return WebPageNames.ADMIN_DASHBOARD;
        }catch (Exception e){
            return  WebPageNames.ERROR_PAGE;
        }
    }

    @PostMapping("/comp")
    public String registerCompany(@ModelAttribute("company") CompanyRegistrationDto companyRegistrationDto, HttpSession session){
        session.setAttribute("nit", companyRegistrationDto.getId());
        try{
            companyService.registerCompany(companyRegistrationDto);
            return WebPageNames.ADMIN_REGISTRATION_FORM;
        }catch (Exception e){
            return WebPageNames.ERROR_PAGE;
        }
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
