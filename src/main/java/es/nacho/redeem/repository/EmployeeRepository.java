package es.nacho.redeem.repository;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Employee;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);
    Collection<Employee> findAllByArea(Area area);
    @Query("SELECT e FROM Employee e WHERE e.area.company.id = ?1 AND e.rol = ?2")
    Collection<Employee> findAllByCompanyAndRol(long nit, String rol);
}
