package com.dimevision.orkis.webapp.entity;

import com.dimevision.orkis.webapp.entity.management.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "agent")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.AGENT;

    @Column(name = "email", updatable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    @JsonBackReference
    private Organization organization;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        return id.equals(agent.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
