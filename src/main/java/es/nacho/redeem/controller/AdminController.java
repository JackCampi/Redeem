package es.nacho.redeem.controller;

import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
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
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String dashboard(Model model){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = "welcome";
        try{
             name = userService.getCompleteUserName(email);
        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("name", name);

        /*User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserEmail = currentUser.getUsername();
        String email = userDetails.getUsername();

        try{
            Long nit = companyService.getCompanyNitByUser(currentUserEmail);
            session.setAttribute("nit", nit);


        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }*/


        return WebPageNames.ADMIN_DASHBOARD;
    }

    @GetMapping(value = "/addemp")
    public String addEmployee(HttpSession session, Model model){

        Long nit = (long) session.getAttribute("nit");
        try{
            Collection<String> areaNames = companyService.getAreasNames(nit);

            model.addAttribute("areaNames", areaNames);


        }catch (Exception e){
            return WebPageNames.ERROR_PAGE;
        }

        return WebPageNames.EMPLOYEE_REGISTRATION_FORM;
    }

    /*@PostMapping(value = "/addemp")
    public String registerEmployee(@ModelAttribute("employee") EmployeeRegistrationDto employeeRegistrationDto, HttpSession session){

        Long nit = (Long) session.getAttribute("nit");

        try {
            userService.registerEmployee(employeeRegistrationDto, nit);
            return WebPageNames.EMPLOYEE_REGISTRATION_FORM;
        }catch (Exception e){
            return WebPageNames.ERROR_PAGE;
        }
    }*/

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {return new EmployeeRegistrationDto();}

}
