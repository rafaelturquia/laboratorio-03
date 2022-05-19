package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.EmpresaParceira;
import com.example.demo.model.Professor;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.ProfessorRepository;
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

    @PostMapping(path="/") // Map ONLY POST Requests
    public @ResponseBody String addNew (@RequestBody Professor professor) throws URISyntaxException {
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
            if (professor.getEstadoLogin()) res.put("login", "true");
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