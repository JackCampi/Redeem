package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;

import java.util.Collection;

public interface CompanyService {

    Company registerCompany(CompanyRegistrationDto companyRegistrationDto) throws Exception;
    Area registerArea(String areaName, Company company);

    Collection<String> getAreasNames(Long companyNIT) throws Exception;

    Long getCompanyNitByUser(String userEmail) throws Exception;

    AdminDashboardInfoDto fillAdminDashboardInfoDto(long nit, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception;

    EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(long nit, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception;

}
