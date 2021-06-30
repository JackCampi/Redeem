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
            if(purchaseDto.getEmployeeId()==null) {
                System.out.println("ID NULO");
            ///    throw new UserNotFoundException();
            }else{
                System.out.println(purchaseDto.getEmployeeId());
            }
            if(purchaseDto.getProductsAndQuantities()==null) {
                System.out.println("P&Q NULO");
            }else{
                System.out.println(purchaseDto.getProductsAndQuantities());
            }
            if(purchaseDto.getValue()==null) {
                System.out.println("valor NULO");
            }else{
                System.out.println(purchaseDto.getValue());
            }

            purchaseService.accomplishPurchase(purchaseDto.getEmployeeId(),purchaseDto.getProductsAndQuantities(),purchaseDto.getValue());
        } catch (UserNotFoundException e) {
            return WebPageNames.EMPLOYEE_CART + "?userNotFound";
        } catch (ProductNotFoundException e) {
            return WebPageNames.EMPLOYEE_CART + "?productNotFound";
        } catch (InsufficientStockException e) {
            return WebPageNames.EMPLOYEE_CART + "?insufficientStock";
        } catch (InsufficientBalanceException e) {
            return WebPageNames.EMPLOYEE_CART + "?insufficientBalance";
        }

        return WebPageNames.EMPLOYEE_CART + "?successfulPurchase";
    }

    @ModelAttribute("purchase")
    public PurchaseDto purchaseDto(){
        return new PurchaseDto();
    }

}
