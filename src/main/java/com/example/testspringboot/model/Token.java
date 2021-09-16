package com.example.testspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Token {

    @Id
    @JsonIgnore
    private String username;

    private String token;

    public Token(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public Token() {
    }
}
