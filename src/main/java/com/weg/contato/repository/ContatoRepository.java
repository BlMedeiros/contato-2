package com.weg.contato.repository;

import com.weg.contato.ContatoApplication;
import com.weg.contato.model.Contato;
import com.weg.contato.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {

    public Contato salvarContato(Contato contato) throws SQLException {

        String insertQuery = """
                INSERT INTO contato
                (nome,numero)
                VALUES(?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1,contato.getNome());
            stmt.setString(2, contato.getNumero());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                contato.setId(rs.getInt(1));
                return contato;
            }
        }
        throw new RuntimeException("Não foi possivel adicionar o contato");
    }

    public List<Contato> buscarContatos() throws SQLException{

        List<Contato> contatoList = new ArrayList<>();

        String selectQuery = """
                SELECT * FROM contato
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                contatoList.add(new Contato(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }

            return contatoList;
        }
    }

    public Contato buscarContatoPorId(int id) throws SQLException {
        String selectQuery = """
                SELECT * FROM contato
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(selectQuery)) {

            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Contato não encontrado");
            }

            return new Contato(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            );
        }
    }

    public Contato atualizarContato(Contato contato) throws SQLException {
        String updateQuery = """
            UPDATE contato
            SET nome = ?, numero = ?
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.setInt(3, contato.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Não foi possível atualizar: Contato não encontrado.");
            }

            return contato;
        }
    }

    public void deletarContato(int id) throws SQLException {
        String deleteQuery = "DELETE FROM contato WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Não foi possível deletar: Contato não encontrado.");
            }
        }
    }
}
