package es.nacho.redeem.controller;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.service.AreaService;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.transaction.BalanceTransaction;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AllocationDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import es.nacho.redeem.web.dto.employee.ChangePasswordDto;
import es.nacho.redeem.web.dto.employee.MemberDto;
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

    @Autowired
    private EmployeeRepository employeeRepository;

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
            areaNames = companyService.getAreasNames(true, nit);

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
    public String getAllocationView(Model model, HttpSession session){

        Long nit = (long) session.getAttribute("nit");

        Collection<String> areaNames = new ArrayList<>();

        try{
            areaNames = companyService.getAreasNames(true, nit);

        }catch (Exception e){
            areaNames.add("areas not found");
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("areaNames", areaNames);
        return WebPageNames.ADMIN_ALLOCATION;
    }

    @GetMapping(value = "/allocation/emp")
    public String getEmployeeAllocationView(){return "redirect:/allocation";}

    @GetMapping(value = "/allocation/comp")
    public String getCompanyAllocationView(){return "redirect:/allocation";}

    @GetMapping(value = "/allocation/area")
    public String getAreaAllocationView(HttpSession session, Model model){

        Long nit = (long) session.getAttribute("nit");

        Collection<String> areaNames = new ArrayList<>();

        try{
            areaNames = companyService.getAreasNames(false, nit);

        }catch (Exception e){
            areaNames.add("areas not found");
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("areaNames", areaNames);

        return WebPageNames.ADMIN_ALLOCATION_AREA;
    }

    @PostMapping(value = "/allocation/emp")
    public String processEmployeeAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");
        long nit = (long) httpSession.getAttribute("nit");

        Collection<Long> employeesIdentifiers = new ArrayList<>();
        Employee employee = employeeRepository.findByEmail(allocationDto.getEmployee());

        try {
            if(employee == null || employee.getRol().equals("administrador")) throw new UserNotFoundException();
            employeesIdentifiers.add(employee.getId());

            balanceTransaction.userToUsersBalanceTransaction(nit, id, employeesIdentifiers, allocationDto.getAmount());
            
            return "redirect:/admin/allocation?success";
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/admin/allocation?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation?insufficient";
        }
    }

    @PostMapping(value = "/allocation/comp")
    public String processCompanyAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");
        
        Long nit = (long) httpSession.getAttribute("nit");

        Collection<String> areasNames = new ArrayList<>();

        try {
            areasNames = companyService.getAreasNames(false, nit);
        } catch (Exception e) {
            areasNames.add("company not found");
        }

        Collection<Long> employeesIdentifiers;

        try {
            employeesIdentifiers = areaService.getAllEmployees(areasNames, nit);
        } catch (UserNotFoundException userNotFoundException) {
            return "redirect:/admin/allocation?companyNotFound";
        }

        try {
            balanceTransaction.userToUsersBalanceTransaction(nit, id, employeesIdentifiers, allocationDto.getAmount());
            return "redirect:/admin/allocation?success";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation?insufficient";
        }
    }

    @PostMapping(value = "/allocation/area")
    public String processAreaAllocation(@ModelAttribute("allocation") AllocationDto allocationDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");
        long nit = (long) httpSession.getAttribute("nit");

        Collection<String> areaNames = areaService.lowercaseAreaNames(allocationDto.getAreas());

        try {
            Collection<Long> employeesIdentifiers = areaService.getAllEmployees(areaNames, nit);
            balanceTransaction.userToUsersBalanceTransaction(nit, id, employeesIdentifiers, allocationDto.getAmount());
            return "redirect:/admin/allocation?success";
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/admin/allocation?areaNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/admin/allocation?insufficient";
        }
    }

    @GetMapping(value = "/history")
    public String getHistoryView(HttpSession httpSession, Model model){

        long id = (long) httpSession.getAttribute("id");

        Collection<TransferHistoryMessageDto> transferMessages = transferService.getTransferMessages(id);
        model.addAttribute("transferMessages", transferMessages);

        return WebPageNames.ADMIN_HISTORY;
    }
    
    @GetMapping(value = "/profile")
    public String getProfileView(Model model, HttpSession session){

        long id = (long) session.getAttribute("id");

        MemberDto memberDto = userService.getProfileInfo(id);
        model.addAttribute("user",memberDto);

        return WebPageNames.ADMIN_PROFILE;

    }

    @PostMapping(value = "/profile")
    public String changePassword(@ModelAttribute ChangePasswordDto changePasswordDto, HttpSession session){

        if(!changePasswordDto.getNewPassord().equals(changePasswordDto.getConfirmPassword())) return "redirect:/admin/profile?error";

        long id = (long) session.getAttribute("id");

        try {
            userService.changePassword(id,changePasswordDto.getOldPassword(),changePasswordDto.getNewPassord());
        } catch (Exception e) {
            return "redirect:/admin/profile?error";
        }

        return "redirect:/admin/profile";

    }

    @ModelAttribute("changePassword")
    public ChangePasswordDto changePasswordDto(){
        return new ChangePasswordDto();
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
