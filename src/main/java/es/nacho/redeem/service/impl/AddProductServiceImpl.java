package es.nacho.redeem.service.impl;

import es.nacho.redeem.mapper.ProductMapper;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.AddProductService;
import es.nacho.redeem.web.dto.ProductDetailsDto;
import es.nacho.redeem.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProductServiceImpl implements AddProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void invoke(ProductDto productDto, ProductDetailsDto productDetailsDto, Long companyNIT) throws Exception {
        Product product = ProductMapper.toProduct(productDto, productDetailsDto);
        productRepository.save(product);
    }
}
