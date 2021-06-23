package es.nacho.redeem.repository;

import es.nacho.redeem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.nacho.redeem.model.Allocation;

import java.util.Collection;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    Collection<Allocation> findAllByEmployee(Employee employee);

}
