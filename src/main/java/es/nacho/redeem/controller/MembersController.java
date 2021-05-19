package es.nacho.redeem.controller;

import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.employee.MemberDto;
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
        Collection<MemberDto> employees = new ArrayList<>();
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

    @GetMapping(value = "/disable")
    public String disableUser(@RequestParam long id){

        companyService.disableEmployee(id);

        return "redirect:/admin/members";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("member") MemberDto memberDto, HttpSession session){

        long nit = (long) session.getAttribute("nit");
        userService.editUserInformation(nit, memberDto);
        return "redirect:/admin/members";

    }

    @ModelAttribute("member")
    public MemberDto editedEmployeeInfoDto(){
        return new MemberDto();
    }

}
