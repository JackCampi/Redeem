package es.nacho.redeem.repository;

import es.nacho.redeem.model.PurchaseHasProduct;
import es.nacho.redeem.model.compositeKeys.PurchaseHasProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHasProductRepository extends JpaRepository<PurchaseHasProduct, PurchaseHasProductKey> {
}
