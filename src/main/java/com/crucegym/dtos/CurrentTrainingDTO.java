package com.crucegym.dtos;

public class CurrentTrainingDTO {

    private String exercise;
    private Short reps;
    private Short weight;

    public CurrentTrainingDTO() {
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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
        return "CurrentTrainingDTO{" +
                "exercise='" + exercise + '\'' +
                ", reps=" + reps +
                ", weight=" + weight +
                '}';
    }
}
