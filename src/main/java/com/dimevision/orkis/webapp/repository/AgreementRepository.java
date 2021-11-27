package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Agreement;
import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    List<Agreement> findAllByIssueDateAndParticipantsNumberAndOrganizationAndClient(Date issueDate, Short participantsNumber, Organization organization, Client client);

    @Override
    Page<Agreement> findAll(Pageable pageable);
}
