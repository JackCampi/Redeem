package es.nacho.redeem.controller;

import es.nacho.redeem.service.api.ViewProductList;
import es.nacho.redeem.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
@RequestMapping("/emp/marketplace")
public class MarketplaceController {

    @Autowired
    private ViewProductList viewProductList;

    @GetMapping()
    public String viewProductList(HttpSession session, Model model) {
        long nit = (long)  session.getAttribute("nit");
        Collection<ProductDto> productList;
        try{
            productList = viewProductList.get(nit);
        }catch (Exception e){
            e.printStackTrace();
            return WebPageNames.ERROR_PAGE;
        }

        model.addAttribute("productList", productList);

        return WebPageNames.ERROR_PAGE; //TODO
    }
}
