package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repositories.AlunoRepository;
import com.example.demo.repositories.EmpresaRepository;
import com.example.demo.repositories.ResgateRepository;
import com.example.demo.repositories.VantagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequestMapping(path = "/resgate")
public class ResgateController {
    @Autowired
    private ResgateRepository resgateRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private VantagemRepository vantagemRepository;

    @PostMapping(path = "/{aluno_id}/{vantagem_id}") // Map ONLY POST Requests
    public @ResponseBody String addNew(@PathVariable("aluno_id") Integer aluno_id,
                                       @PathVariable("vantagem_id") Integer vantagem_id) throws URISyntaxException {
        Aluno aluno = alunoRepository.findById(aluno_id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Vantagem vantagem = vantagemRepository.findById(vantagem_id).orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
        if (aluno.getConta().getSaldo() < vantagem.getValor()) return "Saldo insuficiente";
        aluno.getConta().setSaldo(aluno.getConta().getSaldo() - vantagem.getValor());
        alunoRepository.save(aluno);
        Resgate resgate = new Resgate(aluno, vantagem);
        resgateRepository.save(resgate);
        return "Resgate realizado";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Resgate> getAll() {
        return resgateRepository.findAll();
    }

    @GetMapping(path = "/aluno/{aluno_id}")
    public @ResponseBody Iterable<Resgate> getAllByAluno(@PathVariable("aluno_id") Integer aluno_id) {
        return resgateRepository.findAllByAlunoId(aluno_id);
    }

    @GetMapping("/{id}")
    public Resgate getOne(@PathVariable Integer id) {
        return resgateRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteOne(@PathVariable Integer id) {
        resgateRepository.deleteById(id);
        return "Removed";
    }

}
