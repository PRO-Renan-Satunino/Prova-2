package controller;

import dao.PacienteDAO;
import model.Paciente;

import java.util.List;

/**
 * Controlador de paciente: conecta a view com o DAO.
 */
public class PacienteController {

    private PacienteDAO dao = new PacienteDAO();

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
