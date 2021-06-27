package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.InsufficientStockException;
import es.nacho.redeem.exception.ProductNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.model.Purchase;
import es.nacho.redeem.web.dto.transfer.history.AdminDto;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;

import java.util.ArrayList;
import java.util.Collection;

public interface PurchaseService {

    void accomplishPurchase(Employee employee, Collection<ArrayList<Long>> productsAndQuantities, Long purchaseValue)
            throws ProductNotFoundException, InsufficientStockException, InsufficientBalanceException;
    SortedList<EmpDto> getEmployeePurchases(Employee employee, SortedList<EmpDto> sortedList);
    SortedList<AdminDto> getAdminPurchases(long nit, SortedList<AdminDto> sortedList);
    Collection<Purchase> getPurchasesToSend(Long nit);
    void setSentPurchase(Long purchaseId) throws Exception;

}
