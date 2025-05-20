package controller;

import dao.PacienteDAO;
import model.Paciente;

import java.util.List;

public class PacienteController {

    // Construtor: inicializa o DAO de Paciente
    private PacienteDAO dao = new PacienteDAO();


    // Salva um paciente: insere se for novo (id=0), ou atualiza se já existir
    public void salvar(Paciente paciente) {
        if (paciente.getId() == 0) {
            dao.inserir(paciente);
        } else {
            dao.atualizar(paciente);
        }
    }

    // Exclui um paciente pelo id
    public void excluir(int id) {
        dao.deletar(id);
    }

    // Retorna uma lista com todos os pacientes cadastrados
    public List<Paciente> listarTodos() {
        return dao.listarTodos();
    }

    // Busca um paciente pelo id
    public Paciente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}