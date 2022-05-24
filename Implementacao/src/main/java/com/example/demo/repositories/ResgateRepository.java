package com.example.demo.repositories;

import com.example.demo.model.Resgate;
import org.springframework.data.repository.CrudRepository;

public interface ResgateRepository extends CrudRepository<Resgate, Integer> {
    Iterable<Resgate> findAllByAlunoId(Integer id);
}
