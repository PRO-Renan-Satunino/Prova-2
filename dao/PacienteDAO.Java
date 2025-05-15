package dao;

import model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private List<Paciente> pacientes = new ArrayList<>();

    public PacienteDAO(List<Paciente> pacientes) {
        this.pacientes = pacientes;
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
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPorId(int id) {
        return pacientes.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst()
                .orElse(null);
    }
}