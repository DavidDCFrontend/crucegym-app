package com.crucegym.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name", unique = true, nullable = false, length = 40)
    private String name;

    @Column(name = "type", length = 40)
    private String type;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "idExercise")      // Relación con 'Set'
    private List<Set> setList;

}
