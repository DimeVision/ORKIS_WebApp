package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "contract")
@NoArgsConstructor
@Getter
@Setter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "num")
    private String contractNumber;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_client_participate")
    private Boolean isClientParticipating;

    @OneToOne
    @JoinColumn(name = "agreement_id", referencedColumnName = "id")
    @JsonBackReference
    private Agreement agreement;

    @OneToMany(mappedBy = "contract")
    @JsonManagedReference
    private Set<Participant> participants;

    @OneToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @JsonBackReference
    private Currency currency;

    @OneToOne(mappedBy = "contract")
    @JsonBackReference
    private Payment payment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id.equals(contract.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
