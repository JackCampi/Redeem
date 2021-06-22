package es.nacho.redeem.repository;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findAllByCompany(Company company);
}
