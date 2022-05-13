package com.example.demo.repositories;

import com.example.demo.model.Aluno;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

    Aluno findByLogin(String login);
}