package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *
 *
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "departure")
    private Date departureDate;

    @Column(name = "arrival")
    private Date arrivalDate;

    @ManyToMany(mappedBy = "routes")
    private Set<City> cities;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country countries;
}
