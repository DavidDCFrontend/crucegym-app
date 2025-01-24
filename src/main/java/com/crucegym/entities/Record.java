package com.crucegym.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne                                                     // Relación con 'Set'
    @JoinColumn(name = "id_set", referencedColumnName = "id")
    private Set idSet;
}
