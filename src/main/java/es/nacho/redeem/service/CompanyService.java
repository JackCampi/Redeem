package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;

import java.util.ArrayList;
import java.util.Collection;

public interface CompanyService {

    Company registerCompany(CompanyRegistrationDto companyRegistrationDto);
    Area registerArea(String areaName, Company company);

    Collection<String> getAreasNames(Long companyNIT) throws Exception;

    Long getCompanyNitByUser(String userEmail) throws Exception;

}
