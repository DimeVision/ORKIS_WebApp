package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "agreement")
@NoArgsConstructor
@Getter
@Setter
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "num")
    private String agreementNumber;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "participants_num")
    private Short participantsNumber;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(mappedBy = "agreement")
    private Contract contract;
}
