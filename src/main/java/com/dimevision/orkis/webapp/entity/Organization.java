package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "organization")
@NoArgsConstructor
@Getter
@Setter
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "organization")
    @ToString.Exclude
    @JsonManagedReference
    private Set<Agent> agents;

    @OneToMany(mappedBy = "organization")
    @ToString.Exclude
    @JsonManagedReference
    private Set<Employee> employees;

    @OneToMany(mappedBy = "organization")
    @ToString.Exclude
    @JsonManagedReference
    private Set<Agreement> agreements;

    @Override
    public String toString() {
        return name;
    }
}
