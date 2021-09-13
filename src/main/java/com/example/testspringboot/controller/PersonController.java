package com.example.testspringboot.controller;

import com.example.testspringboot.model.Person;
import com.example.testspringboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> findAllUsers(@RequestParam(name = "sort", required = false) String sortColumn,
                                     @RequestParam(required = false) String order,
                                     @RequestParam(required = false) String name) {
        if (name != null) {
            return (List<Person>) personRepository.findByNameContainingIgnoreCase(name);
        }

        if (order != null && "asc".equalsIgnoreCase(order)) {
            return (List<Person>) personRepository.findAllByOrderByCreateDateAsc();
        } else if (order != null && "desc".equalsIgnoreCase(order)) {
            return (List<Person>) personRepository.findAllByOrderByCreateDateDesc();
        }
        return (List<Person>) personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findUserById(@PathVariable(value = "id") long id) {
        Optional<Person> user = personRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Person saveUser(@Validated @RequestBody Person user) {
        // Implement
        return personRepository.save(user);
    }
}
