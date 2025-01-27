package com.crucegym.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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

    @NotNull(message = "Debe indicar el peso/carga. Si es peso libre marque 0")
    @PositiveOrZero(message = "El peso no puede ser negativo")
    @Column(name = "weight", nullable = false)
    private Short weight;

    @NotNull(message = "Debe indicar el número de repeticiones")
    @PositiveOrZero(message = "El número de repeticiones no puede ser negativo")
    @Column(name = "reps", nullable = false)
    private Short reps;

    @Column(name = "set_order")
    private Byte setOrder;

    @NotNull(message = "Debe indicar una fecha")
    @Column(name = "set_date", nullable = false)
    private LocalDate setDate;

    @OneToOne(mappedBy = "idSet", cascade = CascadeType.ALL)      // Relación con 'Record'
    private Record record;

    public Set() {
    }

    public Set(Short idExercise, Integer idUser, LocalDate date, Byte order, Short weight) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Exercise getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Exercise idExercise) {
        this.idExercise = idExercise;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Short getReps() {
        return reps;
    }

    public void setReps(Short reps) {
        this.reps = reps;
    }

    public Byte getSetOrder() {
        return setOrder;
    }

    public void setSetOrder(Byte setOrder) {
        this.setOrder = setOrder;
    }

    public LocalDate getSetDate() {
        return setDate;
    }

    public void setSetDate(LocalDate setDate) {
        this.setDate = setDate;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
