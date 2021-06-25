package es.nacho.redeem.repository;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    Collection<Purchase> findAllByEmployee(Employee employee);

}
