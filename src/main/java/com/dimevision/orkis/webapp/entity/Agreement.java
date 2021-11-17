package com.dimevision.orkis.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "agreement")
@NoArgsConstructor
@Getter
@Setter
public class Agreement {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "num")
    private String agreementNumber;

    @Column(name = "issue_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date issueDate;

    @Column(name = "participants_num")
    private Short participantsNumber;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference
    private Country country;

    @Column(name = "trip_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tripStartDate;

    @Column(name = "trip_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tripEndDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @JsonBackReference
    private Organization organization;

    @OneToOne(mappedBy = "agreement")
    @JsonBackReference
    private Contract contract;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agreement agreement = (Agreement) o;

        return id.equals(agreement.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
