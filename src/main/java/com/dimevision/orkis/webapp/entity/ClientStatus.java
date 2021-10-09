package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
