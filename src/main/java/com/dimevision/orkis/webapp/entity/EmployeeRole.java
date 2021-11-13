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
@Table(name = "authority")
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    @ToString.Exclude
    @JsonManagedReference
    private Set<Employee> employees;

    @Override
    public String toString() {
        return name;
    }
}
