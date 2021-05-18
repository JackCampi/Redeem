package es.nacho.redeem.controller;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.employee.EditedEmployeeInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/admin/members")
public class MembersController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping
    public  String getMembersView(HttpSession session, Model model){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        long nit = (long)  session.getAttribute("nit");

        AdminDashboardInfoDto adminDashboardInfoDto = new AdminDashboardInfoDto();
        Collection<Employee> employees = new ArrayList<>();
        Collection<String> areaNames = new ArrayList<>();


        try{
            adminDashboardInfoDto = userService.fillAdminDashboardInfoDto(email, adminDashboardInfoDto);
            adminDashboardInfoDto = companyService.fillAdminDashboardInfoDto(nit, adminDashboardInfoDto);
            employees = companyService.getEmployees(nit);
            areaNames = companyService.getAreasNames(nit);

        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("adminDashboardInfo", adminDashboardInfoDto);
        model.addAttribute("employeeList", employees);
        model.addAttribute("areaNames", areaNames);

        return WebPageNames.MEMBERS;

    }

    @PostMapping(value = "/disable")
    public String disableUser(@RequestParam String email){

        companyService.disableEmployee(email);

        return "redirect:/admin/members";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("editedEmployeeInfo") EditedEmployeeInfoDto editedEmployeeInfoDto, HttpSession session){

        long nit = (long) session.getAttribute("nit");
        userService.editUserInformation(nit, editedEmployeeInfoDto);
        return "redirect:/admin/members";

    }

    @ModelAttribute("editedEmployeeInfo")
    public EditedEmployeeInfoDto editedEmployeeInfoDto(){
        return new EditedEmployeeInfoDto();
    }

    @ModelAttribute("employeeRow")
    public Employee getEmployee(){
        return new Employee();
    }

}
