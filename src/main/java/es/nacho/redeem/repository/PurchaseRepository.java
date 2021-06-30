package es.nacho.redeem.repository;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Purchase;
import es.nacho.redeem.web.dto.report.PendingShipmentsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    Collection<Purchase> findAllByEmployee(Employee employee);

    //@Query(value = "select E.name, P.id, P.value from Purchase P join P.employee E where E.area.company.id = ?1 and Purchase .isSent = false", nativeQuery = true)
    @Query(value = "select employee.emp_name, purchase.pur_id, pur_value from purchase join employee on purchase.emp_id = employee.emp_id where employee.area_company_comp_id = ?1 and purchase.pur_sent = 0 limit ?2", nativeQuery = true)
    Collection<Object[]> findPendingShipments(long nit, int limit);


}
