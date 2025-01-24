package com.crucegym.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;


    @Column(nullable = false, length = 20)
    private String roleName;

    @OneToMany(mappedBy = "role")     // Relación con 'users'
    private List<User> users;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolName='" + roleName + '\'' +
                '}';
    }
}
