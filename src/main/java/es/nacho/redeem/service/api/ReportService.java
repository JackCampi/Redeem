package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.report.*;

import java.util.Collection;

public interface ReportService {

    Collection<PendingShipmentsDto> getPendingShipments(long nit);
    Collection<ProductDto> getCompanyMostPurchasedProducts(long nit);
    ReportGraphValuesDto getDailyPurchases(long nit);
    Collection<EmployeeDto> getBestBuyers(long nit);
    double getOutgoingBudgetMean(long nit);
    double getIncomingBudgetMean(long nit);
    Collection<CategoryDto> getMostPurchasedCategory(long nit);
    ReportGraphValuesDto getAllocationByAdmin(long nit);
    ReportGraphValuesDto getEmployeeAmountByAreas(long nit);


}
