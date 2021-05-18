package es.nacho.redeem.controller;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.transaction.BalanceTransaction;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AllocationDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import es.nacho.redeem.web.dto.transfer.TransferHistoryMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private BalanceTransaction balanceTransaction;

    @Autowired
    private TransferService transferService;

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

        boolean checkPass = !employeeRegistrationDto.getPassword().equals(employeeRegistrationDto.getPasswordConfirm());

        if(checkPass) return WebPageNames.ERROR_PAGE;

        if(userService.checkIfEmailExists(employeeRegistrationDto.getEmail())) return "redirect:/admin/addemp?error";

        Long nit = (Long) session.getAttribute("nit");

        try {
            userService.registerEmployee(employeeRegistrationDto, nit);
            return "redirect:/admin/addemp?success";
        }catch (Exception e){
            return WebPageNames.ERROR_PAGE;
        }
    }

    @GetMapping(value = "/allocation")
    public String getAllocationView(){return "allocation";}

    @PostMapping(value = "/allocation")
    public String processAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");

        try {
            balanceTransaction.userToUserBalanceTransaction(true, id, allocationDto.getEmployee(), allocationDto.getAmount());
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/adm/allocation?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation?insufficient";
        }
        return WebPageNames.ADMIN_ALLOCATION;
    }

    @GetMapping(value = "/history")
    public String getHistoryView(HttpSession httpSession, Model model){

        long id = (long) httpSession.getAttribute("id");

        Collection<TransferHistoryMessageDto> transferMessages = transferService.getTransferMessages(id);
        model.addAttribute("transferMessages", transferMessages);

        return WebPageNames.HISTORY;
    }

    @GetMapping(value = "/members")
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

    @ModelAttribute("allocation")
    public AllocationDto allocationDto(){
        return new AllocationDto();
    }

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {return new EmployeeRegistrationDto();}

}
