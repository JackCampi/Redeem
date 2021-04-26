package es.nacho.redeem.service;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }

    @Override
    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto) {
        Company company = new Company(
                companyRegistrationDto.getId(),
                companyRegistrationDto.getName(),
                companyRegistrationDto.getBudget(),
                null //TODO
                );
        return companyRepository.save(company);
    }
}
