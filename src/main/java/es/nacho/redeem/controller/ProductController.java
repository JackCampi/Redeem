package es.nacho.redeem.controller;

import es.nacho.redeem.service.api.AddProductService;
import es.nacho.redeem.service.api.ViewProductWithDetailsService;
import es.nacho.redeem.service.api.ViewProductList;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ViewProductList viewProductList;
    @Autowired
    private AddProductService addProductService;
    @Autowired
    private ViewProductWithDetailsService viewProductWithDetailsService;

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

        return WebPageNames.PRODUCTS_LIST; //TODO
    }

    @GetMapping(value = "/details")
    public String viewProductWithDetails(@RequestParam Long id, Model model){
        ProductWithDetailsDto productWithDetailsDto;
        try{
            productWithDetailsDto = viewProductWithDetailsService.get(id);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products?error";
        }
        model.addAttribute("productWithDetails", productWithDetailsDto);
        return WebPageNames.PRODUCT_DETAILS;
    }

    @PostMapping(value = "/addprod")
    public String addProduct(HttpSession session, @ModelAttribute("productWithDetails") ProductWithDetailsDto productWithDetailsDto){
        Long nit = (long) session.getAttribute("nit");
        try{
            addProductService.invoke(productWithDetailsDto, nit);
            return "redirect:/admin/products?success";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products?error";
        }
    }

    @ModelAttribute("product")
    public ProductDto productDto(){
        return new ProductDto();
    }

    @ModelAttribute("productWithDetails")
    public ProductWithDetailsDto productDetailsDto(){
        return new ProductWithDetailsDto();
    }

}
