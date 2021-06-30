package es.nacho.redeem.controller;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.InsufficientStockException;
import es.nacho.redeem.exception.ProductNotFoundException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.service.PurchaseService;
import es.nacho.redeem.service.api.ViewProductWithDetailsService;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.PurchaseDto;
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
@RequestMapping("/emp/marketplace")
public class MarketplaceController {

    @Autowired
    private ViewProductWithDetailsService viewProductWithDetailsService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public String viewProductList(HttpSession session, Model model) {
        long nit = (long)  session.getAttribute("nit");
        Collection<ProductWithDetailsDto> productList;
        try{
            productList = viewProductWithDetailsService.getList(nit);
        }catch (Exception e){
            return WebPageNames.MARKETPLACE + "?empty";
        }
        model.addAttribute("productList", productList);
        return WebPageNames.MARKETPLACE;
    }

    @GetMapping(value = "/cart")
    public String viewCart(){return WebPageNames.EMPLOYEE_CART;}

    @PostMapping(value = "/cart")
    public String buyCart(@ModelAttribute("purchase") PurchaseDto purchaseDto){

        try {
            purchaseService.accomplishPurchase(purchaseDto.getEmployeeId(),purchaseDto.getProductsAndQuantities(),purchaseDto.getValue());
        } catch (UserNotFoundException e) {
            return "redirect:/emp/marketplace/cart?userNotFound";
        } catch (ProductNotFoundException e) {
            return "redirect:/emp/marketplace/cart?productNotFound";
        } catch (InsufficientStockException e) {
            return "redirect:/emp/marketplace/cart?insufficientStock";
        } catch (InsufficientBalanceException e) {
            return "redirect:/emp/marketplace/cart?insufficientBalance";
        }

        return "redirect:/emp/marketplace/cart?successfulPurchase";
    }

    @ModelAttribute("purchase")
    public PurchaseDto purchaseDto(){
        return new PurchaseDto();
    }

}
