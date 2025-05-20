package controller;

import dao.ConsultaDAO;
import model.Consulta;

import java.time.LocalDate;
import java.util.List;

public class ConsultaController {

    private ConsultaDAO dao = new ConsultaDAO();

    // Salva uma consulta: insere se for nova (id=0), ou atualiza se já existir
    public void salvar(Consulta consulta) {
        if (consulta.getId() == 0) {
            dao.inserir(consulta);
        } else {
            dao.atualizar(consulta);
        }
    }

    // Exclui uma consulta pelo id
    public void excluir(int id) {
        dao.deletar(id);
    }

    // Retorna uma lista com todas as consultas cadastradas
    public List<Consulta> listarTodas() {
        return dao.listarTodas();
    }

    // Busca consultas por uma data específica
    public List<Consulta> buscarPorData(LocalDate data) {
        return dao.buscarPorData(data);
    }

    // Busca consultas pelo nome do paciente
    public List<Consulta> buscarPorNomePaciente(String nome) {
        return dao.buscarPorNomePaciente(nome);
    }
}



