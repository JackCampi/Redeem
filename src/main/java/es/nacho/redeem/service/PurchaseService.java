package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.transfer.history.AdminDto;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;

public interface PurchaseService {

    SortedList<EmpDto> getEmployeePurchases(Employee employee, SortedList<EmpDto> sortedList);
    SortedList<AdminDto> getAdminPurchases(long nit, SortedList<AdminDto> sortedList);

}
