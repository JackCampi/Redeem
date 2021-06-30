package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.transfer.history.AdminDto;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;

public interface AllocationService {
    
    void saveAllocation(String company, long amount, String description, Employee admin, Employee employee);
    SortedList<EmpDto> getEmployeeAllocations(Employee employee, SortedList<EmpDto> sortedList);
    SortedList<AdminDto> getAdminAllocations(long nit, SortedList<AdminDto> sortedList);
}
