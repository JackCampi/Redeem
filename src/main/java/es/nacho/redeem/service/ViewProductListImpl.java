package es.nacho.redeem.service;

import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ViewProductListImpl implements ViewProductList{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Collection<Product> get(long companyNIT) throws CompanyNotFoundException {
        Optional<Company> company =  companyRepository.findById(companyNIT);
        if(!company.isPresent()) throw new CompanyNotFoundException();
        return productRepository.findAllByCompany(company.get());
    }
}
