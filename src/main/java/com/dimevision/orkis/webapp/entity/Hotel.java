package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Simple JavaBean object representing all hotels of {@link City}
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "hotel")
@NoArgsConstructor
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stars")
    private String starsNumber;

    @Column(name = "address")
    @JsonBackReference
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return id.equals(hotel.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
