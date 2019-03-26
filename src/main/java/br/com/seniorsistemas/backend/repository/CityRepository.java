package br.com.seniorsistemas.backend.repository;

import br.com.seniorsistemas.backend.domains.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findCitiesByCapitalTrueOrderByName();

    @Query(nativeQuery = true,
            value = "select uf as state, count(*) as quantity from city group by uf having count(*) = (select max(a.quantidade) from (\n" +
                    "select uf, count(*) as quantidade from city group by uf ) as a)\n" +
                    "union\n" +
                    "select uf, count(*) from city group by uf having count(*) = (select min(a.quantidade) from (\n" +
                    "select uf, count(*) as quantidade from city group by uf ) as a)")
    List<Object[]> getMaxAndMinState();

    @Query(nativeQuery = true,
            value = "select uf, count(*) as quantidade from city group by uf"
    )
    List<Object[]> getCitysForState();

    List<City> findCitiesByUf(String uf);

    @Query(nativeQuery = true,
            value = "select count(*) from city"
    )
    Long countAll();

}
