package com.example.demo.controller;

import com.example.demo.model.EmpresaParceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.EmpresaRepository;

import java.net.URISyntaxException;

@Controller
@RequestMapping(path="/empresa")
public class EmpresaParceiraController {
    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody EmpresaParceira empresa) throws URISyntaxException {
        empresaRepository.save(empresa);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<EmpresaParceira> getAll() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public EmpresaParceira getOne(@PathVariable Integer id) {
        return empresaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        empresaRepository.deleteById(id);
        return "Removed";
    }

    @PutMapping("/{id}")
    public @ResponseBody String updateOne (@PathVariable Integer id, @RequestBody EmpresaParceira empresaAtualizada) {
        EmpresaParceira currentCompany = empresaRepository.findById(id).map(empresa -> {
            empresa.setNome(empresaAtualizada.getNome());
            empresa.setCnpj(empresaAtualizada.getCnpj());
            return empresaRepository.save(empresa);
        }).orElseThrow(RuntimeException::new);

        return "Updated";
    }
}