package es.nacho.redeem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.nacho.redeem.model.Allocation;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AllocationRepository;

@Service
public class AllocationServiceImpl implements AllocationService{

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public Allocation saveAllocation(long amount, String description, Employee admin, Employee employee) {
        
        description = String.format("El administrador %s %s abonó $%o al empleado %s %s", admin.getName(),admin.getLastName(),amount, employee.getName(),employee.getLastName());
        return allocationRepository.save(new Allocation(LocalDateTime.now(), amount, description, admin, employee));
    }
    
}