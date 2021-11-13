package com.dimevision.orkis.webapp.entity;

import com.dimevision.orkis.webapp.entity.management.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * JavaBean object that represents client entity
 *
 * @author Dimevision
 * @version 0.1
 */

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String fullName;

    @Column(name = "role")
    @Enumerated(STRING)
    private Role role;

    @Column(name = "sex")
    private Character sex;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(targetEntity = ClientStatus.class, fetch = LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", insertable = false)
    @JsonBackReference
    private ClientStatus status;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "passport_id")
    @JsonBackReference
    private Passport passport;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private Set<Agreement> agreements;

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
