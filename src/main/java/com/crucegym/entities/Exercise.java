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

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Set> getSetList() {
        return setList;
    }

    public void setSetList(List<Set> setList) {
        this.setList = setList;
    }
}
