package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.EmpresaParceira;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repositories.EmpresaRepository;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/empresa")
public class EmpresaParceiraController {
    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping(path = "/") // Map ONLY POST Requests
    public @ResponseBody String addNew(@RequestBody EmpresaParceira empresa) throws URISyntaxException {
        empresaRepository.save(empresa);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<EmpresaParceira> getAll() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public EmpresaParceira getOne(@PathVariable Integer id) {
        return empresaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteOne(@PathVariable Integer id) {
        empresaRepository.deleteById(id);
        return "Removed";
    }

    @PutMapping("/{id}")
    public @ResponseBody String updateOne(@PathVariable Integer id, @RequestBody EmpresaParceira empresaAtualizada) {
        EmpresaParceira currentCompany = empresaRepository.findById(id).map(empresa -> {
            empresa.setNome(empresaAtualizada.getNome());
            empresa.setCnpj(empresaAtualizada.getCnpj());
            return empresaRepository.save(empresa);
        }).orElseThrow(RuntimeException::new);

        return "Updated";
    }

    @PutMapping("/login")
    public @ResponseBody Map<String, String> logarUsuario(@RequestBody Usuario usuario) {
        Map<String, String> res = new HashMap<String, String>();
        EmpresaParceira empresa = empresaRepository.findByLogin(usuario.getLogin());
        if (empresa != null) {
            res.put("usuario_id", empresa.getId().toString());
            empresa.logar(usuario.getSenha());
            empresaRepository.save(empresa);
            if (empresa.getEstadoLogin()) res.put("login", "true");
            else res.put("login", "false");
        } else res.put("usuario_id", "-1");
        return res;
    }

    @PutMapping("/logout/{id}")
    public @ResponseBody String logoutUsuario(@PathVariable Integer id) {
        EmpresaParceira empresa = empresaRepository.findById(id).orElseThrow(RuntimeException::new);
        if (empresa != null) {
            empresa.deslogar();
            empresaRepository.save(empresa);
            return "Deslogado";
        }
        return "Erro no logout";
    }
}