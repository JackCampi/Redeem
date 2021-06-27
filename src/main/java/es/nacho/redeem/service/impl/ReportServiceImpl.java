package es.nacho.redeem.service.impl;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.mapper.CategoryDtoMapper;
import es.nacho.redeem.mapper.EmployeeDtoMapper;
import es.nacho.redeem.mapper.PendingShipmentDtoMapper;
import es.nacho.redeem.mapper.ProductDtoMapper;
import es.nacho.redeem.model.*;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.repository.ProductRepository;
import es.nacho.redeem.repository.PurchaseRepository;
import es.nacho.redeem.service.api.ReportService;
import es.nacho.redeem.web.dto.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private GetCompanyByNitServiceImpl getCompanyByNitService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Collection<PendingShipmentsDto> getPendingShipments(long nit) {

        Collection<PendingShipmentsDto> pendingShipmentsDtos = new ArrayList<>();

        Collection<Object[]> query = purchaseRepository.findPendingShipments(nit, 4);

        query.forEach(objects -> {
            pendingShipmentsDtos.add(PendingShipmentDtoMapper.toPendingShipmentDto(objects));
        });

        /*for(Area area: company.getAreas()){
            for(Employee employee: area.getEmployees()){
                for(Purchase purchase: employee.getPurchases()){

                    pendingShipmentsDtos.add(new PendingShipmentsDto(
                            employee.getName(),
                            purchase.getId(),
                            purchase.getValue(),
                            CalendarFormat.formatLocalDateTime(purchase.getDateTime())
                    ));

                    if(pendingShipmentsDtos.size() > 3) break;
                }
            }
        }*/

        return pendingShipmentsDtos;

    }

    @Override
    public Collection<ProductDto> getCompanyMostPurchasedProducts(long nit) {
        Collection<ProductDto> productDtos = new ArrayList<>();

        Collection<Object[]> query = productRepository.findMostPurchasedProducts(nit, 4);

        query.forEach(objects -> {
            productDtos.add(ProductDtoMapper.toProductDto(objects));
        });

        return productDtos;
    }

    @Override
    public Collection<Collection> getDailyPurchases(long nig) {
        return null;
    }

    @Override
    public Collection<EmployeeDto> getBestBuyers(long nit) {

        Collection<EmployeeDto> employeeDtos = new ArrayList<>();
        Collection<Object[]> properties = employeeRepository.getBestByers(nit, 4);

        properties.forEach(objects -> {
            employeeDtos.add(EmployeeDtoMapper.toEmployeeDto(objects));
        });

        return employeeDtos;
    }

    @Override
    public int getOutgoingBudgetMean(long nit) {
        return 0;
    }

    @Override
    public int getIncomingBudgetMean(long nit) {
        return 0;
    }

    @Override
    public Collection<CategoryDto> getMostPurchasedCategory(long nit) {

        Collection<CategoryDto> categoryDtos = new ArrayList<>();
        Collection<Object[]> properties = productRepository.findMostPurchasedCategories(nit, 4);

        properties.forEach(objects -> {
            categoryDtos.add(CategoryDtoMapper.toCategoryDto(objects));
        });

        return categoryDtos;
    }

    @Override
    public AllocationByAdminDto getAllocationByAdmin(long nit) {

        Collection<String> adminNames = new ArrayList<>();
        Collection<Integer> allocationAmounts = new ArrayList<>();

        Collection<Object[]> properties = employeeRepository.findAllocationByAdmin(nit);

        properties.forEach(objects -> {

            adminNames.add((String) objects[0]);
            BigInteger amount = (BigInteger) objects[1];
            allocationAmounts.add(amount.intValue());

        });

        return new AllocationByAdminDto(
                adminNames,
                allocationAmounts
        );
    }

    @Override
    public EmpCountByAreasDto getEmployeeAmountByAreas(long nit) {

        Collection<String> areaNames = new ArrayList<>();
        Collection<Integer> empCounts = new ArrayList<>();

        Collection<Object[]> properties= areaRepository.findEmpCountByAreas(nit);
        properties.forEach(objects -> {

            areaNames.add((String) objects[0]);
            BigInteger count = (BigInteger) objects[1];
            empCounts.add(count.intValue());

        });


        return new EmpCountByAreasDto(areaNames,empCounts);
    }
}
