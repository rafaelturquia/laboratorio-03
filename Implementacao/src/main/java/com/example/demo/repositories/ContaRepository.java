package com.example.demo.repositories;

import com.example.demo.model.Conta;

import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    Conta findById(int id);
}
