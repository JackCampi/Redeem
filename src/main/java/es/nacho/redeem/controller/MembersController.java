package es.nacho.redeem.controller;

import es.nacho.redeem.exception.EmailAlreadyRegisteredException;
import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.OnlyAdminRemainingException;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.transaction.BalanceTransaction;
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

    @Autowired
    private BalanceTransaction balanceTransaction;

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
            areaNames = companyService.getAreasNames(true,nit);

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
    public String disableUser(@RequestParam long id, HttpSession session){

        long nit = (long) session.getAttribute("nit");

        long amount = companyService.disableEmployee(id);
        try{
            balanceTransaction.returnBalanceToCompany(nit, id, amount);
        }catch (InsufficientBalanceException e){
            return "redirect:/error";
        }

        return "redirect:/admin/members?disable&success";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("member") MemberDto memberDto, HttpSession session){

        long nit = (long) session.getAttribute("nit");
        try{
            userService.editUserInformation(nit, memberDto);
        }catch (EmailAlreadyRegisteredException e){
            return "redirect:/admin/members?error&emailAlreadyRegistered";
        }catch (OnlyAdminRemainingException onlyAdminRemainingException) {
            return "redirect:/admin/members?error&onlyAdminRemaining";
        }
        return "redirect:/admin/members?edit&success";

    }

    @ModelAttribute("member")
    public MemberDto editedEmployeeInfoDto(){
        return new MemberDto();
    }

}
