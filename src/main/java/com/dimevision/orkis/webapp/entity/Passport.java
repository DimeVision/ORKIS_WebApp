package com.dimevision.orkis.webapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "passport")
@NoArgsConstructor
@Getter
@Setter
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "series")
    private String serialNumber;

    @Column(name = "num")
    private String passportNumber;

    @Column(name = "series_number")
    private String seriesWithNumber;

    @Column(name = "issue_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @Column(name = "expired_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiredDate;

    @Column(name = "department")
    private String department;

    @OneToOne(mappedBy = "passport")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @Override
    public String toString() {
        return serialNumber + " " + passportNumber;
    }
}
