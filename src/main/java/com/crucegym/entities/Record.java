package com.crucegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "records",
        uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne                                                     // Relación con 'Set'
    @JoinColumn(name = "id_set", referencedColumnName = "id")
    private Set idSet;

    @Enumerated(EnumType.STRING)
    private RecordType type;

    public Record() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set getIdSet() {
        return idSet;
    }

    public void setIdSet(Set idSet) {
        this.idSet = idSet;
    }

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
