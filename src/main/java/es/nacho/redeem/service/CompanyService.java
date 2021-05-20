package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.employee.MemberDto;

import java.util.Collection;

public interface CompanyService {

    Company registerCompany(CompanyRegistrationDto companyRegistrationDto) throws Exception;
    Area registerArea(String areaName, Company company);

    Collection<String> getAreasNames(Long companyNIT) throws Exception;

    Long getCompanyNitByUser(String userEmail) throws Exception;

    Company discountCompanyBudget(long nit, long amount) throws InsufficientBalanceException;

    AdminDashboardInfoDto fillAdminDashboardInfoDto(long nit, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception;

    EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(long nit, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception;

    Collection<MemberDto> getEmployees(Long companyNIT) throws Exception;

    void disableEmployee(long id);

    void enableEmployee(String mail);

}
