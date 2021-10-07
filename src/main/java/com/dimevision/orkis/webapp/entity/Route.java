package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "route")
@NoArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure")
    private Date departureDate;

    @Column(name = "arrival")
    private Date arrivalDate;

    // TODO: 10/7/2021 Add foreign key for country_id field (Many To One)
}
