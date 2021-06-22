package es.nacho.redeem.service;

import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.employee.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AreaService areaService;

    @Override
    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto) throws Exception {
        Company company = new Company(
                companyRegistrationDto.getId(),
                companyRegistrationDto.getName(),
                10000000000L
        );
        checkIfNitExists(company);
        companyRepository.save(company);
        processAllAreas(companyRegistrationDto.getAreas(), company);
        return company;
    }

    public void checkIfNitExists(Company company) throws Exception{
        Optional<Company> savedCompany =  companyRepository.findById(company.getId());
        if(savedCompany.isPresent()) throw new Exception();
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
        String[] areas = areaService.lowercaseAreaNames(allAreas.split(","));
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

    @Override
    public Collection<String> getAreasNames(Boolean capitalized,Long companyNIT) throws Exception {

        Optional<Company> company =  companyRepository.findById(companyNIT);
        if( !company.isPresent()) throw new Exception("Company not found");

        Collection<Area> area = areaRepository.findByCompany(company.get());

        Collection<String> areaNames = new ArrayList<>();
        if(capitalized){
            area.forEach(areaObject -> areaNames.add(areaService.capitalizeAreaName(areaObject.getId().getName())));
        }else{
            area.forEach(areaObject -> areaNames.add(areaObject.getId().getName()));
        }

        return areaNames;
    }

    @Override
    public Long getCompanyNitByUser(String userEmail) throws Exception {

        Employee employee = employeeRepository.findByEmail(userEmail);

        if(employee == null) throw new Exception("Employee not found");

        Company company = employee.getArea().getCompany();

        return company.getId();
    }

    @Override
    public AdminDashboardInfoDto fillAdminDashboardInfoDto(long nit, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception {

        Optional<Company> optionalCompany = companyRepository.findById(nit);
        if(!optionalCompany.isPresent()) throw new Exception("Company not registered");

        Company company = optionalCompany.get();

        adminDashboardInfoDto.setCompanyName(company.getName());
        adminDashboardInfoDto.setCompanyNit(company.getId());
        adminDashboardInfoDto.setCompanyBudget(company.getBudget());


        return adminDashboardInfoDto;
    }

    @Override
    public EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(long nit, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception {

        Optional<Company> optionalCompany = companyRepository.findById(nit);
        if(!optionalCompany.isPresent()) throw new Exception("Company not registered");

        Company company = optionalCompany.get();

        employeeDashboardInfoDto.setCompanyName(company.getName());
        employeeDashboardInfoDto.setCompanyNit(company.getId());

        return employeeDashboardInfoDto;
    }

    @Override
    public Collection<MemberDto> getEmployees(Long companyNIT) throws Exception{
        Collection<MemberDto> employees = new ArrayList<>();
        Optional<Company> company =  companyRepository.findById(companyNIT);
        if(!company.isPresent()) throw new Exception("Company not found");
        Collection<Area> areas = areaRepository.findByCompany(company.get());
        //areas.forEach(area -> employees.addAll(employeeRepository.findAllByArea(area)));
        areas.forEach(area -> {
            Collection<Employee> employeesInArea = employeeRepository.findAllByArea(area);
            employeesInArea.forEach(employee -> {
                if(employee.getActive()){
                    employees.add(new MemberDto(
                            employee.getId(),
                            employee.getName(),
                            employee.getLastName(),
                            employee.getEmail(),
                            employee.getEmail(),
                            employee.getCellphone(),
                            areaService.capitalizeAreaName(employee.getArea().getId().getName()),
                            getStringFromCalendar(employee.getBirthday()),
                            employee.getBalance()
                    ));
                }
            });
        });
        return employees;
    }

    @Override
    public long disableEmployee(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeObject = employee.get();
        employeeObject.setActive(false);
        employeeRepository.save(employeeObject);
        return employeeObject.getBalance();
    }

    @Override
    public void enableEmployee(String mail) {
        Employee employee = employeeRepository.findByEmail(mail);
        employee.setActive(true);
        employeeRepository.save(employee);
    }

    private String getStringFromCalendar(Calendar calendar){

        String month = "" + calendar.get(Calendar.MONTH);
        if(month.length() < 2) month = "0" + month;

        return calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public Company discountCompanyBudget(long nit, long amount) throws InsufficientBalanceException {
        
        Optional<Company> company = companyRepository.findById(nit);
        if(!company.isPresent()) throw new CompanyNotFoundException();

        Company companyObject = company.get();

        long budget= companyObject.getBudget();
        if(amount > budget) throw new InsufficientBalanceException();

        companyObject.setBudget(budget-amount);
        return companyRepository.save(companyObject);

    }

    @Override
    public void incrementCompanyBudget(long nit, long amount) {
        Optional<Company> company = companyRepository.findById(nit);
        if(!company.isPresent()) throw new CompanyNotFoundException();

        Company companyObject = company.get();
        companyObject.setBudget(companyObject.getBudget() + amount);
        companyRepository.save(companyObject);

    }
}
