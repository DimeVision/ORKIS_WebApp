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
    private String number;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "participants_num")
    private Short participantsNumber;

    // TODO: 10/7/2021 Add foreign keys

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
