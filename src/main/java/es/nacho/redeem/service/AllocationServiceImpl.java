package es.nacho.redeem.service;

import java.time.LocalDateTime;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.transfer.history.AAllocation;
import es.nacho.redeem.web.dto.transfer.history.AdminDto;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveAllocation(String company, long amount, String description, Employee admin, Employee employee) {
        
        description = String.format("El administrador %s %s de la empresa %s abonó $%d al empleado %s %s",
                admin.getName(),admin.getLastName(), company, amount, employee.getName(),employee.getLastName());
        allocationRepository.save(new Allocation(LocalDateTime.now(), amount, description, admin, employee));
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

    @Override
    public SortedList<AdminDto> getAdminAllocations(long nit, SortedList<AdminDto> sortedList) {

        employeeRepository.findAllByCompanyAndRol(nit, "administrador").forEach(admin -> {

            admin.getOutgoinAllocations().forEach(allocation -> {

                sortedList.add(new AAllocation(
                        "AAllocation",
                        allocation.getAmount(),
                        CalendarFormat.formatLocalDateTime(allocation.getDatetime()),
                        allocation.getEmployee().getName(),
                        allocation.getAdmin().getName(),
                        allocation.getDescription()
                ));

            });

        });


        return sortedList;
    }

}
