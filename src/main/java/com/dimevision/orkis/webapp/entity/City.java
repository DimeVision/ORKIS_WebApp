package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "city_has_route",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    @JsonManagedReference
    private Set<Route> routes;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "country_id")
    @JsonBackReference
    private Country country;

    @OneToMany(mappedBy = "city", fetch = LAZY)
    @JsonManagedReference
    private Set<Hotel> hotels;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
