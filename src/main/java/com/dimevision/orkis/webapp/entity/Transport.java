package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "transport")
@NoArgsConstructor
@Getter
@Setter
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String transportType;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    @JsonBackReference
    private Voucher voucher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transport transport = (Transport) o;

        return id.equals(transport.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
