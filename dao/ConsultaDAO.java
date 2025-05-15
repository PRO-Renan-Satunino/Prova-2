package dao;

import model.Consulta;
import model.Medico;
import model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações CRUD da entidade Consulta.
 */
public class ConsultaDAO {

    public void inserir(Consulta consulta) {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data, hora, observacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setDate(3, Date.valueOf(consulta.getData()));
            stmt.setTime(4, Time.valueOf(consulta.getHora()));
            stmt.setString(5, consulta.getObservacao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir consulta: " + e.getMessage());
        }
    }

    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consulta SET id_paciente=?, id_medico=?, data=?, hora=?, observacao=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setDate(3, Date.valueOf(consulta.getData()));
            stmt.setTime(4, Time.valueOf(consulta.getHora()));
            stmt.setString(5, consulta.getObservacao());
            stmt.setInt(6, consulta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM consulta WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public List<Consulta> listarTodas() {
        List<Consulta> lista = new ArrayList<>();

        String sql = """
            SELECT c.id, c.data, c.hora, c.observacao,
                   p.id as id_paciente, p.nome as nome_paciente, p.cpf, p.telefone,
                   m.id as id_medico, m.nome as nome_medico, m.especialidade, m.crm
            FROM consulta c
            JOIN paciente p ON c.id_paciente = p.id
            JOIN medico m ON c.id_medico = m.id
            ORDER BY c.data, c.hora
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Paciente
                Paciente paciente = new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome_paciente"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );

                // Médico
                Medico medico = new Medico(
                        rs.getInt("id_medico"),
                        rs.getString("nome_medico"),
                        rs.getString("especialidade"),
                        rs.getString("crm")
                );

                // Consulta
                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        paciente,
                        medico,
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("observacao")
                );

                lista.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return lista;
    }

    public List<Consulta> buscarPorData(LocalDate data) {
        List<Consulta> lista = new ArrayList<>();

        String sql = """
            SELECT c.id, c.data, c.hora, c.observacao,
                   p.id as id_paciente, p.nome as nome_paciente, p.cpf, p.telefone,
                   m.id as id_medico, m.nome as nome_medico, m.especialidade, m.crm
            FROM consulta c
            JOIN paciente p ON c.id_paciente = p.id
            JOIN medico m ON c.id_medico = m.id
            WHERE c.data = ?
            ORDER BY c.hora
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome_paciente"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );

                Medico medico = new Medico(
                        rs.getInt("id_medico"),
                        rs.getString("nome_medico"),
                        rs.getString("especialidade"),
                        rs.getString("crm")
                );

                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        paciente,
                        medico,
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("observacao")
                );

                lista.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por data: " + e.getMessage());
        }

        return lista;
    }

    public List<Consulta> buscarPorNomePaciente(String nome) {
        List<Consulta> lista = new ArrayList<>();

        String sql = """
            SELECT c.id, c.data, c.hora, c.observacao,
                   p.id as id_paciente, p.nome as nome_paciente, p.cpf, p.telefone,
                   m.id as id_medico, m.nome as nome_medico, m.especialidade, m.crm
            FROM consulta c
            JOIN paciente p ON c.id_paciente = p.id
            JOIN medico m ON c.id_medico = m.id
            WHERE p.nome LIKE ?
            ORDER BY c.data, c.hora
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getInt("id_paciente"),
                        rs.getString("nome_paciente"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );

                Medico medico = new Medico(
                        rs.getInt("id_medico"),
                        rs.getString("nome_medico"),
                        rs.getString("especialidade"),
                        rs.getString("crm")
                );

                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        paciente,
                        medico,
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getString("observacao")
                );

                lista.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por nome do paciente: " + e.getMessage());
        }

        return lista;
    }
}
