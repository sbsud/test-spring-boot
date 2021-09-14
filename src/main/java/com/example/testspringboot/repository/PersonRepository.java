package com.example.testspringboot.repository;

import com.example.testspringboot.model.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
