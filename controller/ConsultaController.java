package controller;

import dao.ConsultaDAO;
import model.Consulta;

import java.time.LocalDate;
import java.util.List;

public class ConsultaController {

    private ConsultaDAO dao = new ConsultaDAO();

    public void salvar(Consulta consulta) {
        if (consulta.getId() == 0) {
            dao.inserir(consulta);
        } else {
            dao.atualizar(consulta);
        }
    }

    public void excluir(int id) {
        dao.deletar(id);
    }

    public List<Consulta> listarTodas() {
        return dao.listarTodas();
    }

    public List<Consulta> buscarPorData(LocalDate data) {
        return dao.buscarPorData(data);
    }

    public List<Consulta> buscarPorNomePaciente(String nome) {
        return dao.buscarPorNomePaciente(nome);
    }
}
 
    

