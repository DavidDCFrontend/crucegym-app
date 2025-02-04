package com.crucegym.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "one_repetition_maximum")
public class OneRepetitionMaximum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne                                        // Relación con 'User'
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne                                        // Relación con 'Exercise'
    @JoinColumn(name = "exercise", nullable = false)
    private Exercise exercise;

    @Column(name = "repetition_maximum", nullable = false)
    private Short oneRepetitionMaximum;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public OneRepetitionMaximum() {
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Short getOneRepetitionMaximum() {
        return oneRepetitionMaximum;
    }

    public void setOneRepetitionMaximum(Short oneRepetitionMaximum) {
        this.oneRepetitionMaximum = oneRepetitionMaximum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OneRepetitionMaximum{" +
                "id=" + id +
                ", oneRepetitionMaximum=" + oneRepetitionMaximum +
                ", date=" + date +
                '}';
    }
}
