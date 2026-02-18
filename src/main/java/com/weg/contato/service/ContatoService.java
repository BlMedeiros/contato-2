package com.weg.contato.service;

import com.weg.contato.model.Contato;
import com.weg.contato.repository.ContatoRepository;

import java.sql.SQLException;
import java.util.List;

public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Contato salvarContato(Contato contato) throws SQLException {
        return contatoRepository.salvarContato(contato);
    }

    public List<Contato> buscarContatos() throws SQLException {
        return contatoRepository.buscarContatos();
    }

    public Contato buscarContatoPorId(int id) throws SQLException {
        return contatoRepository.buscarContatoPorId(id);
    }

    public Contato atualizarContato(int id, Contato contato) throws SQLException {
        contato.setId(id);

        return contatoRepository.atualizarContato(contato);
    }

    public void deletarContato(int id) throws SQLException {
        contatoRepository.deletarContato(id);
    }
}
