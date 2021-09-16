package com.example.testspringboot.repository;

import com.example.testspringboot.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, String> {
    ApplicationUser findAllByUsername(String username);
}
