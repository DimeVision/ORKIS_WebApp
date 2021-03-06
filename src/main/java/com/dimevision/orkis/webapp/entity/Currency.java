package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "currency")
@NoArgsConstructor
@Getter
@Setter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "date")
    private Date date;

    @OneToOne(mappedBy = "currency")
    @JsonBackReference
    private Contract contract;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return id.equals(currency.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
