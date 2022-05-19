package com.example.demo.controller;

import com.example.demo.model.EmpresaParceira;
import com.example.demo.model.Vantagem;
import com.example.demo.repositories.EmpresaRepository;
import com.example.demo.repositories.VantagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;

@Controller
@RequestMapping(path="/vantagem")
public class VantagemController {
    @Autowired
    private VantagemRepository vantagemRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping(path="/{empresa_id}") // Map ONLY POST Requests
    public @ResponseBody String addNew (@PathVariable("empresa_id") Integer empresa_id,
                                        @RequestBody Vantagem vantagem) throws URISyntaxException {
        EmpresaParceira empresa = empresaRepository.findById(empresa_id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrado"));
        vantagemRepository.save(new Vantagem(empresa, vantagem));
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Vantagem> getAll() {
        return vantagemRepository.findAll();
    }

    @GetMapping(path="/all/{empresa_id}")
    public @ResponseBody Iterable<Vantagem> getAllByEmpresaId(@PathVariable("empresa_id") Integer empresa_id)
    {
        EmpresaParceira empresa = empresaRepository.findById(empresa_id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrado"));
        return vantagemRepository.findByEmpresa(empresa);
    }

    @GetMapping("/{id}")
    public Vantagem getOne(@PathVariable Integer id) {
        return vantagemRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String  deleteOne (@PathVariable Integer id) {
        vantagemRepository.deleteById(id);
        return "Removed";
    }

    @PutMapping("/{id}")
    public @ResponseBody String updateOne (@PathVariable Integer id, @RequestBody Vantagem vantagemAtualizado) {
        vantagemRepository.findById(id).map(vantagem -> {
            vantagem.update(vantagemAtualizado);
            return vantagemRepository.save(vantagem);
        }).orElseThrow(RuntimeException::new);

        return "Updated";
    }

}
