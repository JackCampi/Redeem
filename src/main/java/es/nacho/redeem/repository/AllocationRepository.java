package es.nacho.redeem.repository;

import es.nacho.redeem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.nacho.redeem.model.Allocation;

import java.util.Collection;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    Collection<Allocation> findAllByEmployee(Employee employee);

    @Query(value = "select sum(A.al_amount)\n" +
            "from allocation A\n" +
            "join employee E on A.adm_id = E.emp_id\n" +
            "where month(A.al_datetime) = month(now())\n" +
            "and E.area_company_comp_id = ?1", nativeQuery = true)
    long findIncomingBudget(long nit);



}
