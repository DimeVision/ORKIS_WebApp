package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "countries", fetch = LAZY)
    @JsonManagedReference
    private Set<Route> routes;

    @OneToMany(mappedBy = "country", fetch = LAZY)
    @JsonManagedReference
    private Set<City> cities;

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    private Set<Agreement> agreement;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Country country = (Country) object;
        return id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
