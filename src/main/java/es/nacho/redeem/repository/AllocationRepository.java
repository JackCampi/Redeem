package es.nacho.redeem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.nacho.redeem.model.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
    
}
