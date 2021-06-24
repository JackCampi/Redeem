package es.nacho.redeem.service.impl;

import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.DisableProductService;
import es.nacho.redeem.service.api.GetCompanyByNitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisableProductServiceImpl implements DisableProductService {

    @Autowired
    GetCompanyByNitService getCompanyByNitService;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void invoke(Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            Product p = product.get();
            p.setAvailable(false);
            productRepository.save(p);
        }else throw new Exception("Invalid product id : " + productId);
    }
}
