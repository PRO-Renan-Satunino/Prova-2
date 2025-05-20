package dao;

import model.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO em memória que simula um banco de dados para testes.
 */
public class PacienteDAO {

    private List<Paciente> pacientes;

    public PacienteDAO() {
        this.pacientes = new ArrayList<>(); // nunca é null
    }

    public void inserir(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void atualizar(Paciente paciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId() == paciente.getId()) {
                pacientes.set(i, paciente);
                return;
            }
        }
    }

    public void deletar(int id) {
        pacientes.removeIf(paciente -> paciente.getId() == id);
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes); // sempre seguro
    }

    public Paciente buscarPorId(int id) {
        return pacientes.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
