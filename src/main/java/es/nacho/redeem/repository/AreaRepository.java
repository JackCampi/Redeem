package es.nacho.redeem.repository;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AreaRepository extends JpaRepository<Area, AreaKey> {//AreaKey es la llave compuesta de Area
    Collection<Area> findByCompany(Company comp);

    @Query(value = "select A.area_name, count(E.emp_id)\n" +
            "from area A\n" +
            "join employee E on A.area_name = E.area_area_id \n" +
            "and A.company_comp_id = E.area_company_comp_id\n" +
            "where E.area_company_comp_id = ?1\n" +
            "group by A.area_name", nativeQuery = true)
    Collection<Object[]> findEmpCountByAreas(long nit);
}
