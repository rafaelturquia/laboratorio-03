package com.example.demo.repositories;
import com.example.demo.model.EmpresaParceira;
import com.example.demo.model.Vantagem;
import org.springframework.data.repository.CrudRepository;


public interface VantagemRepository extends CrudRepository<Vantagem, Integer>{
    Iterable<Vantagem> findByEmpresa(EmpresaParceira empresa);
}
