package com.crucegym.dtos;


import java.time.LocalDate;

public class RequestedTrainingDTO {

    private LocalDate date;
    private String description;
    private String comments;
    private Byte exerciseOrder;
    private String exerciseName;
    private Byte setOrder;
    private Short reps;
    private Short weight;

    public RequestedTrainingDTO(LocalDate date, String description, String comments, Byte exerciseOrder, String exerciseName, Byte setOrder, Short reps, Short weight) {
        this.date = date;
        this.description = description;
        this.comments = comments;
        this.exerciseOrder = exerciseOrder;
        this.exerciseName = exerciseName;
        this.setOrder = setOrder;
        this.reps = reps;
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Byte getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(Byte exerciseOrder) {
        this.exerciseOrder = exerciseOrder;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Byte getSetOrder() {
        return setOrder;
    }

    public void setSetOrder(Byte setOrder) {
        this.setOrder = setOrder;
    }

    public Short getReps() {
        return reps;
    }

    public void setReps(Short reps) {
        this.reps = reps;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RequestedTrainingDTO{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", comments='" + comments + '\'' +
                ", exerciseOrder=" + exerciseOrder +
                ", exerciseName='" + exerciseName + '\'' +
                ", setOrder=" + setOrder +
                ", reps=" + reps +
                ", weight=" + weight +
                '}';
    }
}
