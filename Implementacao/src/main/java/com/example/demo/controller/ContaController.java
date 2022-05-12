package com.example.demo.controller;

import java.net.URISyntaxException;

import com.example.demo.model.Conta;
import com.example.demo.repositories.ContaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path="/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody Conta conta) throws URISyntaxException {
        contaRepository.save(conta);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Conta> getAll() {
        return contaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Conta getOne(@PathVariable Integer id) {
        return contaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        contaRepository.deleteById(id);
        return "Removed";
    }
    
}
