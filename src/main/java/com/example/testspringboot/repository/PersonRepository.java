package com.example.testspringboot.repository;

import com.example.testspringboot.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Iterable<Person> findAllByOrderByCreateDateAsc();

    Iterable<Person> findAllByOrderByCreateDateDesc();

    Iterable<Person> findByNameContainingIgnoreCase(String name);
}
