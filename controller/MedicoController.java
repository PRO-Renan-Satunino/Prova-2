package controller;

import dao.MedicoDAO;
import model.Medico;

import java.util.List;

public class MedicoController {

   private MedicoDAO dao = new MedicoDAO();



    // Salva um médico: insere se for novo (id=0), ou atualiza se já existir
    public void salvar(Medico medico) {
        if (medico.getId() == 0) {
            dao.inserir(medico);
        } else {
            dao.atualizar(medico);
        }
    }

    // Exclui um médico pelo id
    public void excluir(int id) {
        dao.deletar(id);
    }

    // Retorna uma lista com todos os médicos cadastrados
    public List<Medico> listarTodos() {
        return dao.listarTodos();
    }

    // Busca um médico pelo id
    public Medico buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
