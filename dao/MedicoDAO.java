package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Medico;



public class MedicoDAO {

    private Connection connection;

    public MedicoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um médico
    public void inserirMedico(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome, especialidade, crm) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar um médico
    public void atualizarMedico(Medico medico) throws SQLException {
        String sql = "UPDATE medico SET nome = ?, especialidade = ?, crm = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setInt(4, medico.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um médico
    public void excluirMedico(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar médicos por especialidade
    public List<Medico> buscarMedicosPorEspecialidade(String especialidade) throws SQLException {
        String sql = "SELECT * FROM medico WHERE especialidade = ?";
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, especialidade);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medico medico = new Medico();
                    medico.setId(rs.getInt("id"));
                    medico.setNome(rs.getString("nome"));
                    medico.setEspecialidade(rs.getString("especialidade"));
                    medico.setCrm(rs.getString("crm"));
                    medicos.add(medico);
                }
            }
        }
        return medicos;
    }
}