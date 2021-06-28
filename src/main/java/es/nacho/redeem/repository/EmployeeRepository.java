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

    @Query(value = "select PR.prod_image_url, PR.prod_name, sum(PHP.php_quantity)\n" +
            "from employee E\n" +
            "join purchase P on E.emp_id = P.emp_id\n" +
            "join purchase_has_product PHP on P.pur_id = PHP.purchase_pur_id\n" +
            "join product PR on PHP.product_prod_id = PR.prod_id\n" +
            "where E.emp_id = ?1\n" +
            "group by PR.prod_id\n" +
            "order by sum(PHP.php_quantity) desc\n" +
            "limit 1", nativeQuery = true)
    Object[] findMostPurchasedProductByMe(long id);

    @Query(value = "select PR.prod_image_url, PR.prod_name, sum(PHP.php_quantity)\n" +
            "from employee E\n" +
            "join purchase P on E.emp_id = P.emp_id\n" +
            "join purchase_has_product PHP on P.pur_id = PHP.purchase_pur_id\n" +
            "join product PR on PHP.product_prod_id = PR.prod_id\n" +
            "where E.emp_id = ?1\n" +
            "group by PR.prod_id\n" +
            "order by sum(P.pur_datetime) desc\n" +
            "limit 4", nativeQuery = true)
    Collection<Object[]> findLastFourPurchases(long id);

}
