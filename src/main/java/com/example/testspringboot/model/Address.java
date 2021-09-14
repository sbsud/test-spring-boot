package com.example.testspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Address {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String addressString;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
}
