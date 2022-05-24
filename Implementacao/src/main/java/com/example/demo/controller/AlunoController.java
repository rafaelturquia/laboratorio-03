package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Conta;
import com.example.demo.model.TransferenciaDePontos;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.AlunoRepository;
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
@RequestMapping(path="/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransferenciaDePontosRepository trasnferenciaRepository;

    @PostMapping(path="/")
    public @ResponseBody String addNew (@RequestBody Aluno aluno) throws URISyntaxException {
        Conta conta = new Conta();
        contaRepository.save(conta);
        aluno.setConta(conta);
        alunoRepository.save(aluno);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Aluno getOne(@PathVariable Integer id) {
        return alunoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/extrato/{id}")
    public @ResponseBody Iterable<TransferenciaDePontos> getAllTransf(@PathVariable Integer id){
        return trasnferenciaRepository.findAllByAlunoId(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        alunoRepository.deleteById(id);
        return "Removed";
    }

    @PutMapping("/{id}")
    public @ResponseBody String updateOne (@PathVariable Integer id, @RequestBody Aluno alunoAtualizado) {
        alunoRepository.findById(id).map(aluno -> {
            aluno.update(alunoAtualizado);
            return alunoRepository.save(aluno);
        }).orElseThrow(RuntimeException::new);

        return "Updated";
    }

    @PutMapping("/login")
    public @ResponseBody Map<String, String> logarUsuario (@RequestBody Usuario usuario) {
        Map<String, String> res = new HashMap<String, String>();
        Aluno aluno = alunoRepository.findByLogin(usuario.getLogin());
        if(aluno != null){
            res.put("usuario_id", aluno.getId().toString());
            aluno.logar(usuario.getSenha());
            alunoRepository.save(aluno);
            if (aluno.getEstadoLogin()) {
                res.put("login", "true");
                res.put("saldo", String.valueOf(aluno.getConta().getSaldo()));
            }
            else res.put("login", "false");
        } else res.put("usuario_id", "-1");
        return res;
    }

    @PutMapping("/logout/{id}")
    public @ResponseBody String logoutUsuario(@PathVariable Integer id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(RuntimeException::new);
//        Professor professor = new Professor();
//        professor.setLogin("professor");
//        professor.setNome("nome");
//        professor.setCPF("CPF");
//        professor.setDepartamento("departamento");
//        professor.setSenha("senha");
//        professorRepository.save(professor);
        if (aluno != null) {
            aluno.deslogar();
            alunoRepository.save(aluno);
            return "Deslogado";
        }
        return "Erro no logout";
    }
}