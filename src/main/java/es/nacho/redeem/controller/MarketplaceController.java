package es.nacho.redeem.controller;

import es.nacho.redeem.service.api.ViewProductWithDetailsService;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/emp/marketplace")
public class MarketplaceController {

    @Autowired
    private ViewProductWithDetailsService viewProductWithDetailsService;

    @GetMapping()
    public String viewProductList(HttpSession session, Model model) {
        long nit = (long)  session.getAttribute("nit");
        Collection<ProductWithDetailsDto> productList;
        try{
            productList = viewProductWithDetailsService.getList(nit);
        }catch (Exception e){
            e.printStackTrace();
            productList = new ArrayList<>();
            return WebPageNames.MARKETPLACE + "?empty";
        }
        model.addAttribute("productList", productList);
        return WebPageNames.MARKETPLACE;
    }
}
