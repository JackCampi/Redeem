package es.nacho.redeem.controller;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.service.AreaService;
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
    private AreaService areaService;

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

    @GetMapping(value = "/allocation/emp")
    public String getEmployeeAllocationView(){return "allocation";}

    @GetMapping(value = "/allocation/comp")
    public String getCompanyAllocationView(){return "allocation";}

    @GetMapping(value = "/allocation/area")
    public String getAreaAllocationView(HttpSession session, Model model){

        Long nit = (long) session.getAttribute("nit");

        Collection<String> areaNames = new ArrayList<>();

        try{
            areaNames = companyService.getAreasNames(nit);

        }catch (Exception e){
            areaNames.add("areas not found");
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("areaNames", areaNames);

        return "allocation";
    }

    @PostMapping(value = "/allocation/emp")
    public String processEmployeeAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");

        try {
            balanceTransaction.userToUserBalanceTransaction(true, id, allocationDto.getEmployee(), allocationDto.getAmount());
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/adm/allocation/emp?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation/emp?insufficient";
        }
        return WebPageNames.ADMIN_ALLOCATION_EMPLOYEE;
    }

    @PostMapping(value = "/allocation/comp")
    public String processCompanyAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");
        
        Long nit = (long) httpSession.getAttribute("nit");

        Collection<String> areasNames = new ArrayList<>();

        try {
            areasNames = companyService.getAreasNames(nit);
        } catch (Exception e) {
            areasNames.add("areas not found");
        }

        Collection<Long> employeesIdentifiers = new ArrayList<>();

        try {
            employeesIdentifiers = areaService.getAllEmployees(areasNames, nit);
        } catch (UserNotFoundException userNotFoundException) {
            return "redirect:/adm/allocation/comp?userNotFound";
        }

        try {
            balanceTransaction.userToUsersBalanceTransaction(id, employeesIdentifiers, allocationDto.getAmount());
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/adm/allocation/area?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation/area?insufficient";
        }
        return WebPageNames.ADMIN_ALLOCATION_COMPANY;
    }

    @PostMapping(value = "/allocation/area")
    public String processAreaAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");
        Long nit = (long) httpSession.getAttribute("nit");
        
        Collection<Long> employeesIdentifiers = areaService.getAllEmployees(allocationDto.getAreas(), nit);

        try {
            balanceTransaction.userToUsersBalanceTransaction(id, employeesIdentifiers, allocationDto.getAmount());
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/adm/allocation/area?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation/area?insufficient";
        }
        
        return WebPageNames.ADMIN_ALLOCATION_AREA;
    }

    @GetMapping(value = "/history")
    public String getHistoryView(HttpSession httpSession, Model model){

        long id = (long) httpSession.getAttribute("id");

        Collection<TransferHistoryMessageDto> transferMessages = transferService.getTransferMessages(id);
        model.addAttribute("transferMessages", transferMessages);

        return WebPageNames.HISTORY;
    }

    @ModelAttribute("allocation")
    public AllocationDto allocationDto(){
        return new AllocationDto();
    }

    @ModelAttribute("employee")
    public EmployeeRegistrationDto employeeRegistrationDto() {return new EmployeeRegistrationDto();}

    @ModelAttribute("employeeRow")
    private Employee getEmployee(){
        return new Employee();
    }
}
