
package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    private Connection conn;

    public MedicamentoDAO(Connection conn) {
        this.conn = conn;
    }

    // Inserir novo medicamento
    public void inserir(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO medicamentos (nome, fabricante, preco, quantidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getNome());
            stmt.setString(2, medicamento.getFabricante());
            stmt.setDouble(3, medicamento.getPreco());
            stmt.setInt(4, medicamento.getQuantidade());
            stmt.executeUpdate();
        }
    }

    // Listar todos os medicamentos
    public List<Medicamento> listarTodos() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamentos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medicamento med = new Medicamento(
                        rs.getString("nome"),
                        rs.getString("fabricante"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                lista.add(med);
            }
        }
        return lista;
    }

    // Buscar medicamentos por nome
    public List<Medicamento> buscarPorNome(String nome) throws SQLException {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicamentos WHERE nome ILIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medicamento med = new Medicamento(
                            rs.getString("nome"),
                            rs.getString("fabricante"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade")
                    );
                    lista.add(med);
                }
            }
        }
        return lista;
    }

    // Atualizar medicamento
    public void atualizar(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE medicamentos SET fabricante=?, preco=?, quantidade=? WHERE nome=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getFabricante());
            stmt.setDouble(2, medicamento.getPreco());
            stmt.setInt(3, medicamento.getQuantidade());
            stmt.setString(4, medicamento.getNome());
            stmt.executeUpdate();
        }
    }

    // Remover medicamento
    public void remover(String nome) throws SQLException {
        String sql = "DELETE FROM medicamentos WHERE nome=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }
}