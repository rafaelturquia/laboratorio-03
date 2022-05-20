package com.example.demo.repositories;

import com.example.demo.model.TransferenciaDePontos;

import org.springframework.data.repository.CrudRepository;


public interface TransferenciaDePontosRepository extends CrudRepository<TransferenciaDePontos, Integer> {

    TransferenciaDePontos findById(int id);

    Iterable<TransferenciaDePontos> findAllByProfessorId(Integer id);

    Iterable<TransferenciaDePontos> findAllByAlunoId(Integer id);
}
