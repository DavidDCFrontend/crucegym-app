package com.crucegym.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "sets")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                                              // Relación con 'User'
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @ManyToOne                                               // Relación con 'Exercise'
    @JoinColumn (name = "id_exersice", nullable = false)
    private Exercise idExercise;

    @NotBlank(message = "Debe indicar el peso/carga. Si es peso libre marque 0")
    @Column(name = "weight", nullable = false)
    private Short weight;

    @NotBlank(message = "Debe indicar el número de repeticiones")
    @Column(name = "reps", nullable = false)
    private Short reps;

    @Column(name = "set_order")
    private Byte setOrder;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Column(name = "set_date", nullable = false)
    private LocalDate setDate;

    @OneToOne(mappedBy = "idSet", cascade = CascadeType.ALL)      // Relación con 'Record'
    private Record record;

}
