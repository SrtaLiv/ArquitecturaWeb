package com.microservicio_user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Cuenta {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Column
    private LocalDate date;
    @Getter
    @Column
    private boolean anulada;

    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "cuentas" )
    private Set<User> users;

    public Cuenta(Long id, LocalDate date, boolean anulada) {
        this.id = id;
        this.date = date;
        this.anulada = anulada;
    }
    public Cuenta(LocalDate date, boolean anulada) {
        this.date = date;
        this.anulada = anulada;
    }

    public Cuenta() {}

}
