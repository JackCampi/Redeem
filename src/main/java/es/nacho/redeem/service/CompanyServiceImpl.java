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
        processAllAreas(companyRegistrationDto.getAreas(), companyRegistrationDto.getId());
        return companyRepository.save(company);
    }

    /**
     * receives the text string with the areas of the company and processes this information by sending the data of
     * all areas to {@link #registerArea(String, Long)}
     *
     * @param allAreas  a text string with all the areas of the company that it receives from the frontend separated
     *                  by commas.
     * @param companyId id Long type of the company that owns this area
     */
    public void processAllAreas(String allAreas, Long companyId){
        String[] areas = allAreas.split(",");
        Area defaultArea = new Area("gerencia", companyId);
        areaRepository.save(defaultArea);
        for (String areaName : areas) {
            registerArea(areaName, companyId);
        }
    }

    @Override
    public Area registerArea(String areaName, Long companyId) {
        return areaRepository.save(new Area(areaName, companyId));
    }
}
