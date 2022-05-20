package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repositories.ContaRepository;
import com.example.demo.repositories.ProfessorRepository;
import com.example.demo.repositories.TransferenciaDePontosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransferenciaDePontosRepository trasnferenciaRepository;

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody Professor professor) throws URISyntaxException {
        Conta conta = new Conta(1000);
        contaRepository.save(conta);
        professor.setConta(conta);
        professorRepository.save(professor);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Professor> getAll() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Professor getOne(@PathVariable Integer id) {
        return professorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/extrato/{id}")
    public @ResponseBody Iterable<TransferenciaDePontos> getAllTransf(@PathVariable Integer id){
        return trasnferenciaRepository.findAllByProfessorId(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        professorRepository.deleteById(id);
        return "Removed";
    }

//    @PutMapping("/{id}")
//    public @ResponseBody String updateOne (@PathVariable Integer id, @RequestBody Professor professorAtualizado) {
//        professorRepository.findById(id).map(professor -> {
//            professor.update(professorAtualizado);
//            return professorRepository.save(professor);
//        }).orElseThrow(RuntimeException::new);
//
//        return "Updated";
//    }

    @PutMapping("/login")
    public  @ResponseBody Map<String, String> logarUsuario (@RequestBody Usuario usuario) {
        Map<String, String> res = new HashMap<String, String>();
        Professor professor = professorRepository.findByLogin(usuario.getLogin());
        if(professor != null){
            res.put("usuario_id", professor.getId().toString());
            professor.logar(usuario.getSenha());
            professorRepository.save(professor);
            if (professor.getEstadoLogin()) {
                res.put("login", "true");
                res.put("saldo", String.valueOf(professor.getConta().getSaldo()));
            }
            else res.put("login", "false");
        } else res.put("usuario_id", "-1");
        return res;
    }

    @PutMapping("/logout/{id}")
    public @ResponseBody String logoutUsuario(@PathVariable Integer id) {
        Professor professor = professorRepository.findById(id).orElseThrow(RuntimeException::new);
//        Professor professor = new Professor("professor", "nome", "CPF", "senha", "departamento");
//        professorRepository.save(professor);
        if (professor != null) {
            professor.deslogar();
            professorRepository.save(professor);
            return "Deslogado";
        }
        return "Erro no logout";
    }
}