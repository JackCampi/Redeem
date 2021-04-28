package es.nacho.redeem.controller;

import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String dashboard(Model model, HttpSession session){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        long nit = (long)  session.getAttribute("nit");

        AdminDashboardInfoDto adminDashboardInfoDto = new AdminDashboardInfoDto();

        try{
             adminDashboardInfoDto = userService.fillAdminDashboardInfoDto(email, adminDashboardInfoDto);
             adminDashboardInfoDto = companyService.fillAdminDashboardInfoDto(nit, adminDashboardInfoDto);

        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("adminDashboardInfo", adminDashboardInfoDto);



        return WebPageNames.ADMIN_DASHBOARD;
    }

    @GetMapping(value = "/addemp")
    public String addEmployee(HttpSession session, Model model){

        Long nit = (long) session.getAttribute("nit");

        Collection<String> areaNames = new ArrayList<>();

        try{
            areaNames = companyService.getAreasNames(nit);

        }catch (Exception e){
            areaNames.add("areas not found");
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("areaNames", areaNames);

        return WebPageNames.EMPLOYEE_REGISTRATION_FORM;
    }

    @PostMapping(value = "/addemp")
    public String registerEmployee(@ModelAttribute("employee") EmployeeRegistrationDto employeeRegistrationDto, HttpSession session){

        Long nit = (Long) session.getAttribute("nit");

        try {
            userService.registerEmployee(employeeRegistrationDto, nit);
            return WebPageNames.EMPLOYEE_REGISTRATION_FORM;
        }catch (Exception e){
            return WebPageNames.ERROR_PAGE;
        }
    }

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {return new EmployeeRegistrationDto();}

}
