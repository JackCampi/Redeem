package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto) {
        Company company = new Company(
                companyRegistrationDto.getId(),
                companyRegistrationDto.getName(),
                10000000000L
        );
        companyRepository.save(company);
        processAllAreas(companyRegistrationDto.getAreas(), company);
        return company;
    }

    /**
     * receives the text string with the areas of the company and processes this information by sending the data of
     * all areas to {@link #registerArea(String, Company)}
     *
     * @param allAreas  a text string with all the areas of the company that it receives from the frontend separated
     *                  by commas.
     * @param company id Long type of the company that owns this area
     */
    public void processAllAreas(String allAreas, Company company){
        String[] areas = allAreas.split(",");
        Area defaultArea = new Area("gerencia", company);
        areaRepository.save(defaultArea);
        for (String areaName : areas) {
            registerArea(areaName, company);
        }
    }

    @Override
    public Area registerArea(String areaName, Company company) {
        Area a = new Area(areaName, company);
        return areaRepository.save(a);
    }
}
