package es.nacho.redeem.service.api;

import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.model.Company;

public interface GetCompanyByNitService {
    Company invoke(Long companyNIT) throws CompanyNotFoundException;
}
