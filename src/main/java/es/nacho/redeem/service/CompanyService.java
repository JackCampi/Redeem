package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;

public interface CompanyService {

    Company registerCompany(CompanyRegistrationDto companyRegistrationDto);
    Area registerArea(String areaName, Company company);

}
