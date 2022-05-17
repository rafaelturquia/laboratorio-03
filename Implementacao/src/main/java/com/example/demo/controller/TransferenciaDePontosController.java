package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Professor;
import com.example.demo.model.TransferenciaDePontos;
import com.example.demo.repositories.AlunoRepository;
import com.example.demo.repositories.ProfessorRepository;
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
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping(path="/{aluno_id}/{professor_id}") // Map ONLY POST Requests
    public @ResponseBody String addNew (@PathVariable("aluno_id") Integer aluno_id,
                                        @PathVariable("professor_id") Integer professor_id,
                                        @RequestBody TransferenciaDePontos transferenciaDePontos) throws URISyntaxException {
        Aluno aluno = alunoRepository.findById(aluno_id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Professor professor = professorRepository.findById(professor_id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        transferenciaDePontosRepository.save(new TransferenciaDePontos(aluno, professor, transferenciaDePontos));
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
