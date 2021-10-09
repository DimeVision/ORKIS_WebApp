package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 *
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



    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
