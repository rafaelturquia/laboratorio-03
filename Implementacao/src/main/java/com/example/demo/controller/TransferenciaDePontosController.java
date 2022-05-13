package com.example.demo.controller;

import com.example.demo.model.TransferenciaDePontos;
import com.example.demo.repositories.TransferenciaDePontosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequestMapping(path="/transferenciaDePontos")
public class TransferenciaDePontosController {
    @Autowired
    private TransferenciaDePontosRepository transferenciaDePontosRepository;

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody TransferenciaDePontos transferenciaDePontos) throws URISyntaxException {
        transferenciaDePontosRepository.save(transferenciaDePontos);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<TransferenciaDePontos> getAll() {
        return transferenciaDePontosRepository.findAll();
    }

    @GetMapping("/{id}")
    public TransferenciaDePontos getOne(@PathVariable Integer id) {
        return transferenciaDePontosRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        transferenciaDePontosRepository.deleteById(id);
        return "Removed";
    }
}
