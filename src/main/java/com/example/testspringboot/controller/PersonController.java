package com.example.testspringboot.controller;

import com.example.testspringboot.model.Person;
import com.example.testspringboot.repository.PersonRepository;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

//    @GetMapping
//    public List<Person> findAllUsers(@RequestParam(name = "sort", required = false) String sortColumn,
//                                     @RequestParam(required = false) String order,
//                                     @RequestParam(required = false) String name) {
//        if (name != null) {
//            return (List<Person>) personRepository.findByNameContainingIgnoreCase(name);
//        }
//
//        if (order != null && "asc".equalsIgnoreCase(order)) {
//            return (List<Person>) personRepository.findAllByOrderByCreateDateAsc();
//        } else if (order != null && "desc".equalsIgnoreCase(order)) {
//            return (List<Person>) personRepository.findAllByOrderByCreateDateDesc();
//        }
//        return (List<Person>) personRepository.findAll();
//    }
//
//    @GetMapping
//    public List<Person> findAllUsers(@RequestParam(name = "sort", required = false) String sortColumn,
//                                     @RequestParam(required = false) String order,
//                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
//        if ("asc".equalsIgnoreCase(order)) {
//            return (List<Person>) personRepository.findAllWithDueDateBeforeOrderByDueDate(endDate);
//        }
//        return (List<Person>) personRepository.findAllWithDueDateBefore(endDate);
//
//    }

    @GetMapping
    public List<Person> findUsers(@And({
            @Spec(path = "name", params="name", spec = LikeIgnoreCase.class),
            @Spec(path = "dueDate", params = "dueDate", spec = LessThanOrEqual.class),
            @Spec(path = "createDate", params = "createDate", spec = LessThanOrEqual.class)
    }) Specification<Person> spec, Sort sort) {
        System.out.println(spec.toString());
        return personRepository.findAll(spec, sort);
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
