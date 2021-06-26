package es.nacho.redeem.controller;

import es.nacho.redeem.service.api.*;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ViewProductList viewProductList;
    @Autowired
    private AddProductService addProductService;
    @Autowired
    private UpdateProductService updateProductService;
    @Autowired
    private DisableProductService disableProductService;
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
            productList = new ArrayList<>();
        }
        model.addAttribute("productList", productList);

        return WebPageNames.PRODUCTS_LIST;
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

    @PostMapping(value = "/details/edit")
    public String editProduct(HttpSession session, @ModelAttribute("productWithDetails") ProductWithDetailsDto productWithDetailsDto) {
        Long nit = (long) session.getAttribute("nit");
        try{
            updateProductService.invoke(productWithDetailsDto, nit);
            return "redirect:/admin/products/details?success&id="+productWithDetailsDto.getId();
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products/details?error&id="+productWithDetailsDto.getId();
        }
    }

    @PostMapping(value = "/details/disable")
    public String disableProduct(@RequestParam Long productId) {
        try{
            disableProductService.invoke(productId);
            return "redirect:/admin/products?successDelete";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products?errorDelete";
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
