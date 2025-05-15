package controller;

import dao.PacienteDAO;
import model.Paciente;

import java.util.List;

public class PacienteController {

    private PacienteDAO dao;

    public PacienteController() {
        this.dao = new PacienteDAO(null);
    }

    public void salvar(Paciente paciente) {
        if (paciente.getId() == 0) {
            dao.inserir(paciente);
        } else {
            dao.atualizar(paciente);
        }
    }

    public void excluir(int id) {
        dao.deletar(id);
    }

    public List<Paciente> listarTodos() {
        return dao.listarTodos();
    }

    public Paciente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
