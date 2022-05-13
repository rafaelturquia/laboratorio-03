package com.example.demo.repositories;

import com.example.demo.model.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

    Professor findByLogin(String login);
}