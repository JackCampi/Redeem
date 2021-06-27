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

    @Query(value = "select E.emp_name, sum(P.pur_value), SQ.quantity from employee E join purchase P on E.emp_id = P.emp_id, (select E.emp_id as id,sum(PHP.php_quantity) as quantity from employee E join purchase P on E.emp_id = P.emp_id join purchase_has_product PHP on P.pur_id = PHP.purchase_pur_id where E.area_company_comp_id = ?1 group by E.emp_id) as SQ where E.area_company_comp_id = ?1 and SQ.id = E.emp_id group by E.emp_id order by sum(P.pur_value) desc limit ?2", nativeQuery = true)
    Collection<Object[]> getBestByers(long nit, int limit);

    @Query(value = "select E.emp_name, count(A.al_id)\n" +
            "from employee E\n" +
            "join allocation A on E.emp_id = A.adm_id\n" +
            "where E.area_company_comp_id = ?1\n" +
            "group by E.emp_id", nativeQuery = true)
    Collection<Object[]> findAllocationByAdmin(long nit);

}
