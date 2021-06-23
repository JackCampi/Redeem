package es.nacho.redeem.service.impl;

import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.mapper.ProductListMapper;
import es.nacho.redeem.mapper.ProductMapper;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.service.api.ViewProductList;
import es.nacho.redeem.web.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class ViewProductListImpl implements ViewProductList {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Collection<ProductDto> get(long companyNIT) throws CompanyNotFoundException {
        Optional<Company> company =  companyRepository.findById(companyNIT);
        if(!company.isPresent()) throw new CompanyNotFoundException();
        Collection<Product> products = productRepository.findAllByCompany(company.get());
        return ProductListMapper.toProductListDto(products);
    }
}
