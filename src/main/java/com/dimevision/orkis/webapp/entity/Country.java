package com.dimevision.orkis.webapp.entity;

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

    @OneToMany
    @JoinColumn(name = "id", nullable = false)
    private Set<Route> routes;

    @OneToMany(mappedBy = "country")
    private Set<Agreement> agreement;
}
