package com.example.testspringboot.controller;

import com.example.testspringboot.model.Address;
import com.example.testspringboot.model.Person;
import com.example.testspringboot.repository.AddressRepository;
import com.example.testspringboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/{id}/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Address>> findAddressesForPerson(@PathVariable long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok().body(addressRepository.findByPerson(person.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
