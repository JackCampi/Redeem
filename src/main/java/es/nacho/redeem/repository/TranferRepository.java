package es.nacho.redeem.repository;

import es.nacho.redeem.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranferRepository extends JpaRepository<Transfer, Long> {

}
