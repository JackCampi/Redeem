package es.nacho.redeem.service;

import es.nacho.redeem.model.Allocation;
import es.nacho.redeem.model.Employee;

public interface AllocationService {
    
    Allocation saveAllocation(String company, long amount, String description, Employee admin, Employee employee);
    
}
