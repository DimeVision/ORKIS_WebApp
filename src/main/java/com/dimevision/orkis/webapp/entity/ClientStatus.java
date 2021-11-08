package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * JavaBean object representing status of {@link Client}
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "status")
@NoArgsConstructor
@Getter
@Setter
public class ClientStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> client;

    @Override
    public String toString() {
        return name;
    }
}
