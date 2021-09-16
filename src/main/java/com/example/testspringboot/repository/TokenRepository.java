package com.example.testspringboot.repository;

import com.example.testspringboot.model.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {}
