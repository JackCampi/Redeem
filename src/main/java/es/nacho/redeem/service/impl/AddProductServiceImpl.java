package es.nacho.redeem.service.impl;

import es.nacho.redeem.mapper.ProductMapper;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.AddProductService;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class AddProductServiceImpl implements AddProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void invoke(ProductWithDetailsDto productWithDetailsDto, Long companyNIT) throws Exception {
        if(validate(productWithDetailsDto)) {
            Product product = ProductMapper.toProduct(productWithDetailsDto);
            productRepository.save(product);
        }else throw new Exception("Null params");
    }

    private boolean validate(ProductWithDetailsDto product){
        return Objects.nonNull(product.getId()) &&
                Objects.nonNull(product.getName()) &&
                Objects.nonNull(product.getStock()) &&
                Objects.nonNull(product.getImageUrl()) &&
                Objects.nonNull(product.getType()) &&
                Objects.nonNull(product.getDetails());
    }

}
