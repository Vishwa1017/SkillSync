package com.project.skillsync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

  //No argument constructor for JPA to call this class when instantiated-
    public Role() {
    }
// parameterised constructor when we want to create a object using this class
    public Role(String name) {
        this.name = name;
    }
}
