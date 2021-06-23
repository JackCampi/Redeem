package es.nacho.redeem.service.impl;

import es.nacho.redeem.mapper.ProductDetailsMapper;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.ViewProductDetailsService;
import es.nacho.redeem.web.dto.ProductDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViewProductDetailsServiceImpl implements ViewProductDetailsService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDetailsDto get(Long id) throws Exception{
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()) throw new Exception("Product no exists");
        return ProductDetailsMapper.toProductDetailsDto(product.get());
    }
}
