package es.nacho.redeem.service.impl;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.mapper.CategoryDtoMapper;
import es.nacho.redeem.mapper.EmployeeDtoMapper;
import es.nacho.redeem.mapper.PendingShipmentDtoMapper;
import es.nacho.redeem.mapper.ProductDtoMapper;
import es.nacho.redeem.model.*;
import es.nacho.redeem.repository.*;
import es.nacho.redeem.service.api.ReportService;
import es.nacho.redeem.web.dto.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private GetCompanyByNitServiceImpl getCompanyByNitService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AllocationRepository allocationRepository;

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

        return pendingShipmentsDtos;

    }

    @Override
    public Collection<ProductDto> getCompanyMostPurchasedProducts(long nit, int limit) {

        Collection<ProductDto> productDtos = new ArrayList<>();
        Collection<Object[]> query = productRepository.findMostPurchasedProducts(nit, limit);

        query.forEach(objects -> {
            productDtos.add(ProductDtoMapper.toProductDto(objects));
        });

        return productDtos;
    }

    @Override
    public ReportGraphValuesDto getDailyPurchases(long nit) {

        Calendar calendar = new GregorianCalendar();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        if(month.length() < 2) month = "0" + month;
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        Map<String, Integer> productCountPerDay = new TreeMap<String, Integer>();

        for(int i = 1; i<= today; i++){

            String todayString = Integer.toString(i);
            if(todayString.length() < 2 ) todayString = "0" + todayString;
            productCountPerDay.put(year+"-"+month+"-"+todayString, 0);

        }

        Collection<Object[]> properties = productRepository.findPurchasedProductsByDay(nit);

        properties.forEach(objects -> {

            String dateKey = CalendarFormat.formatDate((Date) objects[0]);
            BigDecimal productCountValue = (BigDecimal) objects[1];
            productCountPerDay.put(dateKey, productCountValue.intValue());

        });

        Collection<String> dates = productCountPerDay.keySet();
        Collection<Integer> productCount = productCountPerDay.values();


        return new ReportGraphValuesDto(dates, productCount);
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
    public double getOutgoingBudgetMean(long nit) {

        long outgoingBudget = productRepository.findOutgoingBudget(nit);
        Calendar calendar = new GregorianCalendar();
        int today = calendar.get(Calendar.DAY_OF_MONTH);

        return outgoingBudget / today;

    }

    @Override
    public double getIncomingBudgetMean(long nit) {
        long ingoingBudget = allocationRepository.findIncomingBudget(nit);
        Calendar calendar = new GregorianCalendar();
        int today = calendar.get(Calendar.DAY_OF_MONTH);

        return ingoingBudget / today;
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
    public ReportGraphValuesDto getAllocationByAdmin(long nit) {

        Collection<String> adminNames = new ArrayList<>();
        Collection<Integer> allocationAmounts = new ArrayList<>();

        Collection<Object[]> properties = employeeRepository.findAllocationByAdmin(nit);

        properties.forEach(objects -> {

            adminNames.add((String) objects[0]);
            BigInteger amount = (BigInteger) objects[1];
            allocationAmounts.add(amount.intValue());

        });

        return new ReportGraphValuesDto(
                adminNames,
                allocationAmounts
        );
    }

    @Override
    public ReportGraphValuesDto getEmployeeAmountByAreas(long nit) {

        Collection<String> areaNames = new ArrayList<>();
        Collection<Integer> empCounts = new ArrayList<>();

        Collection<Object[]> properties= areaRepository.findEmpCountByAreas(nit);
        properties.forEach(objects -> {

            areaNames.add((String) objects[0]);
            BigInteger count = (BigInteger) objects[1];
            empCounts.add(count.intValue());

        });


        return new ReportGraphValuesDto(areaNames,empCounts);
    }

    @Override
    public ProductDto getMostPurchasedProductByMe(long id) {

        Object[] properties = employeeRepository.findMostPurchasedProductByMe(id);
        return ProductDtoMapper.toProductDto((Object[]) properties[0]);
    }

    @Override
    public Collection<ProductDto> getLastPurchases(long id) {

        Collection<ProductDto> lastPurchases = new ArrayList<>();
        Collection<Object[]> properties = employeeRepository.findLastFourPurchases(id);
        properties.forEach(objects -> {
            lastPurchases.add(ProductDtoMapper.toProductDto(objects));
        });

        return lastPurchases;
    }

    @Override
    public ProductDto getCompanyMostPurchasedProductsLastMonth(long nit) {

        Collection<Object[]> query = productRepository.findMostPurchasedProductLastMonth(nit);
        Object[] properties = (Object[]) query.toArray()[0];

        return ProductDtoMapper.toProductDto(properties);
    }
}
