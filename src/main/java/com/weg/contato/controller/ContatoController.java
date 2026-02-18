package com.weg.contato.controller;

import com.weg.contato.model.Contato;
import com.weg.contato.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public Contato salvarContato(@RequestBody Contato contato) {
        try {
            return contatoService.salvarContato(contato);
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Contato> buscarContatos() {
        try {
            return contatoService.buscarContatos();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Contato buscarContatoPorId(@PathVariable int id) {
        try {
            return contatoService.buscarContatoPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@PathVariable int id, @RequestBody Contato contato) {
        try {
            return contatoService.atualizarContato(id,contato);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable int id) {
        try {
            contatoService.deletarContato(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getecho "# contato-2" >> README.md
                    git init
                    git add README.md
                    git commit -m "first commit"
                    git branch -M main
                    git remote add origin https://github.com/BlMedeiros/contato-2.git
            git push -u origin mainMessage());
        }
    }
}
