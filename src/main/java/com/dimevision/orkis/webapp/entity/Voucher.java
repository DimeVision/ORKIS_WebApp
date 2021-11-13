package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "voucher")
@NoArgsConstructor
@Getter
@Setter
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "tourist")
    private String touristName;

    @Column(name = "transfer")
    private Boolean isTransferEnabled;

    @Column(name = "accomodation")
    private String accommodation;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonBackReference
    private Payment payment;

    @OneToMany(mappedBy = "voucher")
    @JsonManagedReference
    private Set<Transport> transports;

    @OneToOne
    @JoinColumn(name = "travel_document_id", referencedColumnName = "id")
    @JsonBackReference
    private TravelDocument document;
}
