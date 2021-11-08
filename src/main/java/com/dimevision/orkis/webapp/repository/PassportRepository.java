package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    Optional<Passport> findBySeriesWithNumber(String seriesWithNumber);
}
