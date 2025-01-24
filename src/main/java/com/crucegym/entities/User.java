package com.crucegym.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de usuario no puede estar vacío ni ser solo espacios")
    @Size(min = 4, max = 15)
    @Column(name="username", nullable = false, length = 15)
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía ni ser solo espacios")
    @Column(name="password", nullable = false, length = 255)
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "El correo electrónico no es válido")
    @Column(name="email", unique = true, nullable = false, length = 40)
    private String email;

    @OneToMany(mappedBy = "idUser")       // Relación con 'Training'
    private List<Training> trainings;

    @OneToMany(mappedBy = "idUser")       // Relación con 'Set'
    private List<Set> sets;

    @ManyToOne                            // Relación con 'Rol'
    private Role role;

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", trainings=" + trainings +
                ", sets=" + sets +
                ", role=" + role +
                '}';
    }
}

// equals() y hashCode() son importantes para las operaciones de comparación y almacenamiento en colecciones como HashSet o HashMap.????????

// Para una validación más estricta del password, podrías usar una expresión regular más compleja o utilizar un validador de correo electrónico proporcionado por librerías especializadas.