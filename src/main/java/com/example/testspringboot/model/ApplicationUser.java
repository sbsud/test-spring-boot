package com.example.testspringboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ApplicationUser {

    @Id
    private String username;

    private String password;
}
