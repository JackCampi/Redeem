package es.nacho.redeem.service.impl;

import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.service.api.GetCompanyByNitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetCompanyByNitServiceImpl implements GetCompanyByNitService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company invoke(Long companyNIT) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(companyNIT);
        if(company.isPresent()){
            return company.get();
        }else throw new CompanyNotFoundException();
    }
}
