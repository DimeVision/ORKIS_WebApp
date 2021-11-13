package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "country")
@NoArgsConstructor
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "countries")
    @JsonManagedReference
    private Set<Route> routes;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    private Set<Agreement> agreement;

    @Override
    public String toString() {
        return name;
    }
}
