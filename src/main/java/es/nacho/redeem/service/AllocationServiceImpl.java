package es.nacho.redeem.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.web.dto.transfer.history.EAllocation;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;
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
    public Allocation saveAllocation(String company, long amount, String description, Employee admin, Employee employee) {
        
        description = String.format("El administrador %s %s de la empresa %s abonó $%d al empleado %s %s", admin.getName(),admin.getLastName(), company, amount, employee.getName(),employee.getLastName());
        return allocationRepository.save(new Allocation(LocalDateTime.now(), amount, description, admin, employee));
    }

    @Override
    public SortedList<EmpDto> getEmployeeAllocations(Employee employee, SortedList<EmpDto> sortedList) {

        allocationRepository.findAllByEmployee(employee).forEach(allocation -> {
            sortedList.add(new EAllocation(
                    "EAllocation",
                    allocation.getAmount(),
                    CalendarFormat.formatLocalDateTime(allocation.getDatetime()),
                    allocation.getAdmin().getName()
            ));
        });

        return sortedList;
    }

}
