package es.nacho.redeem.controller;

import es.nacho.redeem.service.api.AddProductService;
import es.nacho.redeem.service.api.ViewProductDetailsService;
import es.nacho.redeem.service.api.ViewProductList;
import es.nacho.redeem.web.dto.ProductDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;
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
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ViewProductList viewProductList;
    @Autowired
    private AddProductService addProductService;
    @Autowired
    private ViewProductDetailsService viewProductDetailsService;

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
    public String viewProductDetails(Long id, Model model){
        ProductDetailsDto productDetailsDto;
        try{
            productDetailsDto = viewProductDetailsService.get(id);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products?error";
        }
        model.addAttribute("productDetails", productDetailsDto);
        return WebPageNames.PRODUCT_DETAILS;
    }

    @PostMapping(value = "/addprod")
    public String addProduct(HttpSession session, @ModelAttribute("product") ProductDto productDto,
                             @ModelAttribute("productDetails")ProductDetailsDto productDetailsDto){
        Long nit = (long) session.getAttribute("nit");
        try{
            addProductService.invoke(productDto, productDetailsDto, nit);
            return "redirect:/admin/products/addprod?success";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/admin/products/addprod?error";
        }
    }

    @ModelAttribute("product")
    public ProductDto productDto(){
        return new ProductDto();
    }

    @ModelAttribute("productDetails")
    public ProductDetailsDto productDetailsDto(){
        return new ProductDetailsDto();
    }

}
