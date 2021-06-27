package es.nacho.redeem.service.api;

import es.nacho.redeem.web.dto.report.*;

import java.util.Collection;

public interface ReportService {

    Collection<PendingShipmentsDto> getPendingShipments(long nit);
    Collection<ProductDto> getCompanyMostPurchasedProducts(long nit);
    Collection<Collection> getDailyPurchases(long nig);
    Collection<EmployeeDto> getBestBuyers(long nit);
    int getOutgoingBudgetMean(long nit);
    int getIncomingBudgetMean(long nit);
    Collection<CategoryDto> getMostPurchasedCategory(long nit);
    AllocationByAdminDto getAllocationByAdmin(long nit);
    EmpCountByAreasDto getEmployeeAmountByAreas(long nit);


}
