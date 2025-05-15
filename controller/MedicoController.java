package controller;

import dao.MedicoDAO;
import model.Medico;

import java.util.List;


public class MedicoController {

    private MedicoDAO dao = new MedicoDAO(null);

    public void salvar(Medico medico) {
        if (medico.getId() == 0) {
            dao.inserir(medico);
        } else {
            dao.atualizar(medico);
        }
    }

    public void excluir(int id) {
        dao.deletar(id);
    }

    public List<Medico> listarTodos() {
        return dao.listarTodos();
    }

    public Medico buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
