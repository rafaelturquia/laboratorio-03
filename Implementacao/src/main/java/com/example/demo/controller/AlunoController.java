package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Professor;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.AlunoRepository;
import com.example.demo.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequestMapping(path="/aluno")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody Aluno aluno) throws URISyntaxException {
        alunoRepository.save(aluno);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getOne(@PathVariable Integer id) {
        return alunoRepository.findById(id).orElseThrow(RuntimeException::new);
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
    public @ResponseBody String logarUsuario (@RequestBody Usuario usuario) {
        Aluno aluno = alunoRepository.findByLogin(usuario.getLogin());
        if(aluno != null){
            aluno.logar(usuario.getSenha());
            alunoRepository.save(aluno);
            if(aluno.getEstadoLogin()) return "Logado";
            else return "Senha errada";
        } else return "Login não encontrado";
    }

    @PutMapping("/logout")
    public @ResponseBody String logoutUsuario (@RequestBody Usuario usuario) {
        Aluno aluno = alunoRepository.findByLogin(usuario.getLogin());
//        Professor professor = new Professor();
//        professor.setLogin("professor");
//        professor.setNome("nome");
//        professor.setCPF("CPF");
//        professor.setDepartamento("departamento");
//        professor.setSenha("senha");
//        professorRepository.save(professor);
        if(aluno != null){
            aluno.deslogar();
            alunoRepository.save(aluno);
        }
        return "Deslogado";
    }
}