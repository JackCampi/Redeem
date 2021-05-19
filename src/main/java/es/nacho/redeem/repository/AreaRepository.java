package es.nacho.redeem.repository;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AreaRepository extends JpaRepository<Area, AreaKey> {//AreaKey es la llave compuesta de Area
    Collection<Area> findByCompany(Company comp);
    Area findByName(String areaName);
}
