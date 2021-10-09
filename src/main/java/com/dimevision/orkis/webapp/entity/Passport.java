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
@Table(name = "passport")
@NoArgsConstructor
@Getter
@Setter
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "series")
    private String serialNumber;

    @Column(name = "num")
    private String passportNumber;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "expired_date")
    private Date expiredDate;

    @Column(name = "department")
    private String department;

    @OneToOne(mappedBy = "passport")
    private Client client;
}
