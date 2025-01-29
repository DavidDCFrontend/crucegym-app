package com.crucegym.entities;

import jakarta.persistence.*;
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
    @JoinColumn (name = "id_exercise", nullable = false)
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

    public Set(User idUser, Exercise idExercise, LocalDate setDate, Short reps, Byte setOrder, Short weight) {
        this.idUser = idUser;
        this.idExercise = idExercise;
        this.weight = weight;
        this.reps = reps;
        this.setOrder = setOrder;
        this.setDate = setDate;
    }

    /*public Set(User idUser, Exercise idExercise, Short weight, Short reps, Byte setOrder, LocalDate setDate, Record record) {
        this.idUser = idUser;
        this.idExercise = idExercise;
        this.weight = weight;
        this.reps = reps;
        this.setOrder = setOrder;
        this.setDate = setDate;
        this.record = record;
    }*/

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


    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", weight=" + weight +
                ", reps=" + reps +
                ", setOrder=" + setOrder +
                ", setDate=" + setDate +
                '}';
    }
}
