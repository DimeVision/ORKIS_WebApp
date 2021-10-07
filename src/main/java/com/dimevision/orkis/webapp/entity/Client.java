package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * JavaBean object that represents client entity
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "fullname")
    private String name;

    @Column(name = "sex")
    private Character sex;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    // TODO: 10/7/2021 Add foreign key for passport_id and status_id fields (Many To One)
}
