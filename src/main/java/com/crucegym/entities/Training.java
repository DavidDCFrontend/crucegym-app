package com.crucegym.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne                                           // Relación con 'User'
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    @NotBlank(message = "La fecha no puede estar vacía")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(min = 0, max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    @Column(name = "description")
    private String description;

    @Size(min = 0, max = 150, message = "Las observaciones no pueden exceder los 30 caracteres.")
    @Column(name = "comments", length = 150)
    private String comments;

    public Training(Integer id) {
    }

    public Training(User user, LocalDate date, String description, String comments) {
        this.idUser = user;
        this.date = date;
        this.description = description;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
}
