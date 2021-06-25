package es.nacho.redeem.controller;

import es.nacho.redeem.model.Purchase;
import es.nacho.redeem.service.ProductService;
import es.nacho.redeem.service.PurchaseService;
import es.nacho.redeem.web.dto.AllocationDto;
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

@Controller
@RequestMapping("/admin/shipping")

public class ShippingController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public String getShippingView(Model model, HttpSession session){

        Long nit = (Long) session.getAttribute("nit");

        Collection<Purchase> purchasesToSend= purchaseService.getPurchasesToSend(nit);

        model.addAttribute("purchasesToSend", purchasesToSend);
        return WebPageNames.ADMIN_SHIPPING;
    }

    @PostMapping
    public String SendPurchase(@ModelAttribute PurchaseShippingDto purchaseShippingDto){

        try{
            purchaseService.setSentPurchase(purchaseShippingDto.getPurchaseId());
        }catch (Exception e){
            return "redirect:/admin/shipping?error";
        }
        return "redirect:/admin/shipping?success";
    }
}
