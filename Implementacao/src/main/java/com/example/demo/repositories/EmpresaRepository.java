package com.example.demo.repositories;

import com.example.demo.model.Aluno;
import com.example.demo.model.EmpresaParceira;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmpresaRepository extends CrudRepository<EmpresaParceira, Integer> {
    EmpresaParceira findByLogin(String login);

}