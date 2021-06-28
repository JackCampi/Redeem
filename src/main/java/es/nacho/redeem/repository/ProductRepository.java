package es.nacho.redeem.repository;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findAllByCompany(Company company);

    @Query(value = "select P.prod_image_url, P.prod_name, sum(PHP.php_quantity) from product P join purchase_has_product PHP on P.prod_id = PHP.product_prod_id where P.company_comp_id = ?1 group by P.prod_id order by sum(PHP.php_quantity) desc limit ?2", nativeQuery = true)
    Collection<Object[]> findMostPurchasedProducts(long nit, int limit);

    @Query(value = "select P.prod_type, sum(PHP.php_quantity)\n" +
            "from product P\n" +
            "join purchase_has_product PHP on P.prod_id = PHP.product_prod_id\n" +
            "where P.company_comp_id = ?1\n" +
            "group by P.prod_type\n" +
            "order by sum(PHP.php_quantity) desc\n" +
            "limit ?2", nativeQuery = true)
    Collection<Object[]> findMostPurchasedCategories(long nit, int limit);

    @Query(value = "select date(P.pur_datetime), sum(PHP.php_quantity)\n" +
            "from purchase P \n" +
            "join purchase_has_product PHP on P.pur_id = PHP.purchase_pur_id\n" +
            "join employee E on P.emp_id = E.emp_id\n" +
            "where month(P.pur_datetime) = month(now())\n" +
            "and E.area_company_comp_id = ?1\n" +
            "group by date(P.pur_datetime)", nativeQuery = true)
    Collection<Object[]> findPurchasedProductsByDay(long nit);

    @Query(value = "select sum(P.pur_value)\n" +
            "from purchase P \n" +
            "join employee E on P.emp_id = E.emp_id\n" +
            "where month(P.pur_datetime) = month(now())\n" +
            "and E.area_company_comp_id = ?1", nativeQuery = true)
    long findOutgoingBudget(long nit);

    @Query(value = "select P.prod_image_url, P.prod_name, sum(PHP.php_quantity)\n" +
            "from product P\n" +
            "join purchase_has_product PHP on P.prod_id = PHP.product_prod_id\n" +
            "join purchase PU on PHP.purchase_pur_id = PU.pur_id\n" +
            "where P.company_comp_id = ?1\n" +
            "and month(PU.pur_datetime) = month(now())\n" +
            "group by P.prod_id\n" +
            "order by sum(PHP.php_quantity) desc\n" +
            "limit 1", nativeQuery = true)
    Collection<Object[]> findMostPurchasedProductLastMonth(long nit);

}
