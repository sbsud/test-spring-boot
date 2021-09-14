package com.example.testspringboot.repository;

import com.example.testspringboot.model.Address;
import com.example.testspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    public List<Address> findByPerson(Person person);
}
