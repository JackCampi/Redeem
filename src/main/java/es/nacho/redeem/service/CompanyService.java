package es.nacho.redeem.service;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;

public interface CompanyService {

    Company save(CompanyRegistrationDto companyRegistrationDto);

}
