package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.report.CategoryDto;
import es.nacho.redeem.web.dto.report.EmployeeDto;
import es.nacho.redeem.web.dto.report.PendingShipmentsDto;
import es.nacho.redeem.web.dto.report.ProductDto;

import java.util.Collection;

public interface ReportService {

    Collection<PendingShipmentsDto> getPendingShipments(long nit);
    Collection<ProductDto> getCompanyMostPurchasedProducts(long nit);
    Collection<Collection> getDailyPurchases(long nig);
    Collection<EmployeeDto> getBestBuyers(long nit);
    int getOutgoingBudgetMean(long nit);
    int getIncomingBudgetMean(long nit);
    Collection<CategoryDto> getMostPurchasedCategory(long nit);
    Collection<Collection> getAllocationByAdmin(long nit);
    Collection<Collection> getEmployeeAmountByAreas(long nit);


}
