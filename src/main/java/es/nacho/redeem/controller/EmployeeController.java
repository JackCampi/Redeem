package es.nacho.redeem.controller;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.transaction.BalanceTransaction;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.transfer.TransferDto;
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
import java.util.Collection;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

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

        EmployeeDashboardInfoDto employeeDashboardInfoDto = new EmployeeDashboardInfoDto();

        try{
            employeeDashboardInfoDto = userService.fillEmployeeDashboardInfoDto(email, employeeDashboardInfoDto);
            employeeDashboardInfoDto = companyService.fillEmployeeDashboardInfoDto(nit, employeeDashboardInfoDto);

        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("employeeDashboardInfo", employeeDashboardInfoDto);

        return WebPageNames.EMPLOYEE_DASHBOARD;
    }

    @GetMapping(value = "/transfer")
    public String getTransferView(){return WebPageNames.USER_TO_USER_TRANSFER;}

    @PostMapping(value = "/transfer")
    public String processTransaction(@ModelAttribute("transfer") TransferDto transferDto, HttpSession httpSession){

        long id = (long) httpSession.getAttribute("id");

        try {
            balanceTransaction.userToUserBalanceTransaction(false, id, transferDto.getReceiverIdentifier(), transferDto.getAmount());
        }catch (UserNotFoundException userNotFoundException){
            return "redirect:/emp/transfer?userNotFound";
        }catch (InsufficientBalanceException insufficientBalanceException){
            return "redirect:/emp/transfer?insufficient";
        }
        return WebPageNames.USER_TO_USER_TRANSFER;
    }

    @GetMapping(value = "/history")
    public String getHistoryView(HttpSession httpSession, Model model){

        long id = (long) httpSession.getAttribute("id");

        Collection<TransferHistoryMessageDto> transferMessages = transferService.getTransferMessages(id);
        model.addAttribute("transferMessages", transferMessages);

        return WebPageNames.EMP_HISTORY;
    }

    @ModelAttribute("transfer")
    public TransferDto transferDto(){
        return new TransferDto();
    }


}
