package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByCountryId(Long country_id);
}
