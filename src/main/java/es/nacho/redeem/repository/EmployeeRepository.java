package es.nacho.redeem.repository;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);
    Collection<Employee> findAllByArea(Area area);
}
