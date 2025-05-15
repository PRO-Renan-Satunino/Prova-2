package dao;

import model.Consulta;
import model.Medico;
import model.Paciente;
import util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por operações CRUD da entidade Consulta no banco de dados.
 */
public class ConsultaDAO {

    private PacienteDAO pacienteDAO = new PacienteDAO();
    private MedicoDAO medicoDAO = new MedicoDAO();

    public void inserir(Consulta consulta) {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data, hora, observacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
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

        try (Connection conn = ConnectionFactory.getConnection();
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

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public List<Consulta> listarTodas() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                Paciente p = pacienteDAO.buscarPorId(rs.getInt("id_paciente"));
                Medico m = medicoDAO.buscarPorId(rs.getInt("id_medico"));

                c.setPaciente(p);
                c.setMedico(m);
                c.setData(rs.getDate("data").toLocalDate());
                c.setHora(rs.getTime("hora").toLocalTime());
                c.setObservacao(rs.getString("observacao"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return lista;
    }

    public List<Consulta> buscarPorData(LocalDate data) {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE data = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                Paciente p = pacienteDAO.buscarPorId(rs.getInt("id_paciente"));
                Medico m = medicoDAO.buscarPorId(rs.getInt("id_medico"));

                c.setPaciente(p);
                c.setMedico(m);
                c.setData(rs.getDate("data").toLocalDate());
                c.setHora(rs.getTime("hora").toLocalTime());
                c.setObservacao(rs.getString("observacao"));

                lista.add(c);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por data: " + e.getMessage());
        }

        return lista;
    }

    public List<Consulta> buscarPorNomePaciente(String nome) {
        List<Consulta> lista = new ArrayList<>();
        String sql = """
            SELECT c.* FROM consulta c
            JOIN paciente p ON c.id_paciente = p.id
            WHERE p.nome LIKE ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));

                Paciente p = pacienteDAO.buscarPorId(rs.getInt("id_paciente"));
                Medico m = medicoDAO.buscarPorId(rs.getInt("id_medico"));

                c.setPaciente(p);
                c.setMedico(m);
                c.setData(rs.getDate("data").toLocalDate());
                c.setHora(rs.getTime("hora").toLocalTime());
                c.setObservacao(rs.getString("observacao"));

                lista.add(c);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por nome do paciente: " + e.getMessage());
        }

        return lista;
    }
}
