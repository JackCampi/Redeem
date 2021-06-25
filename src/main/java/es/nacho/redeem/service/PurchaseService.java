package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Purchase;
import es.nacho.redeem.web.dto.transfer.history.AdminDto;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;

import java.util.Collection;

public interface PurchaseService {

    SortedList<EmpDto> getEmployeePurchases(Employee employee, SortedList<EmpDto> sortedList);
    SortedList<AdminDto> getAdminPurchases(long nit, SortedList<AdminDto> sortedList);
    Collection<Purchase> getPurchasesToSend(Long nit);
    void setSentPurchase(Long purchaseId) throws Exception;

}
