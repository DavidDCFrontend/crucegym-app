package com.crucegym.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "training_details")
public class TrainingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                                           // Relación con 'Training'
    @JoinColumn(name = "id_training", nullable = false)
    private Training idTraining;

    @Column(name = "exercise_order", nullable = false)
    private Byte exerciseOrder;

    @ManyToOne                                      // Relación con 'Set'
    @JoinColumn(name = "id_set", nullable = false)
    private Set idSet;

    public TrainingDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(Training idTraining) {
        this.idTraining = idTraining;
    }

    public Byte getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(Byte exerciseOrder) {
        this.exerciseOrder = exerciseOrder;
    }

    public Set getIdSet() {
        return idSet;
    }

    public void setIdSet(Set idSet) {
        this.idSet = idSet;
    }

    @Override
    public String toString() {
        return "TrainingDetails{" +
                "id=" + id +
                ", order=" + exerciseOrder +
                '}';
    }
}

