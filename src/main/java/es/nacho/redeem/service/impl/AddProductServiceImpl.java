package es.nacho.redeem.service.impl;

import es.nacho.redeem.mapper.ProductMapper;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.AddProductService;
import es.nacho.redeem.service.api.GetCompanyByNitService;
import es.nacho.redeem.web.dto.ProductWithDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static es.nacho.redeem.mapper.ProductMapper.validateProductWithDetailsDto;

@Service
public class AddProductServiceImpl implements AddProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    GetCompanyByNitService getCompanyByNitService;

    @Override
    public void invoke(ProductWithDetailsDto productWithDetailsDto, Long companyNIT) throws Exception{
        if(validateProductWithDetailsDto(productWithDetailsDto)) {
            Company company = getCompanyByNitService.invoke(companyNIT);
            Product product = ProductMapper.toProduct(productWithDetailsDto, company);
            productRepository.save(product);
        }else throw new Exception("Null params");
    }

}
