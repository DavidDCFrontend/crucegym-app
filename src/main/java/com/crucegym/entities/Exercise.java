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

    @Column(name = "image", nullable = false, unique = true, length = 30)
    private String image;

    @Column(name = "machine_number", nullable = true, length = 5)
    private String machineNumber;

    @OneToMany(mappedBy = "idExercise")     // Relación con 'Set'
    private List<Set> setList;

    @OneToMany(mappedBy = "exercise")       // Relación con 'OneRepetitionMaximum'
    private List<OneRepetitionMaximum> oneRepetitionMaximumList;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public List<Set> getSetList() {
        return setList;
    }

    public void setSetList(List<Set> setList) {
        this.setList = setList;
    }

    public List<OneRepetitionMaximum> getOneRepetitionMaximumList() {
        return oneRepetitionMaximumList;
    }

    public void setOneRepetitionMaximumList(List<OneRepetitionMaximum> oneRepetitionMaximumList) {
        this.oneRepetitionMaximumList = oneRepetitionMaximumList;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                '}';
    }
}
