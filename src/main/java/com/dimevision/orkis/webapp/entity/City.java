package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "city_has_route",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    @JsonManagedReference
    private Set<Route> routes;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private Set<Hotel> hotels;
}
