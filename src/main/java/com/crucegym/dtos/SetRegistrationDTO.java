package com.crucegym.dtos;

import java.time.LocalDate;

public class SetRegistrationDTO {

    private Short idExercise;
    private Integer idUser;
    private Short reps;
    private LocalDate date;
    private Byte order;
    private Short weight;

    public Short getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Short idExercise) {
        this.idExercise = idExercise;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Short getReps() {
        return reps;
    }

    public void setReps(Short reps) {
        this.reps = reps;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }
}
