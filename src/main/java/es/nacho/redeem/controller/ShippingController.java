package es.nacho.redeem.controller;

import es.nacho.redeem.model.Purchase;
import es.nacho.redeem.service.PurchaseService;
import es.nacho.redeem.web.dto.PurchaseShippingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/shipping")

public class ShippingController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public String getShippingView(Model model, HttpSession session){

        Long nit = (Long) session.getAttribute("nit");

        Collection<Purchase> purchasesToSend= purchaseService.getPurchasesToSend(nit);

        Collection<Purchase> purchasesToSendSorted = purchasesToSend.stream().
                sorted(Comparator.comparing(Purchase::getDateTime)).collect(Collectors.toList());

        model.addAttribute("purchasesToSend", purchasesToSendSorted);
        return WebPageNames.ADMIN_SHIPPING;
    }

    @PostMapping
    public String SendPurchase(@ModelAttribute("purchaseShippingDto") PurchaseShippingDto purchaseShippingDto){

        try{
            purchaseService.setSentPurchase(purchaseShippingDto.getPurchaseId());
        }catch (Exception e){
            return "redirect:/admin/shipping?error";
        }
        return "redirect:/admin/shipping?success";
    }

    @ModelAttribute("purchaseShippingDto")
    public PurchaseShippingDto purchaseShippingDto(){
        return new PurchaseShippingDto();
    }
}
