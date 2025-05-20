package dao;

import model.Consulta;
import model.Medico;
import model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * DAO em memória para consultas.
 */
public class ConsultaDAO {

    private List<Consulta> consultas;

    public ConsultaDAO() {
        this.consultas = new ArrayList<>(); // nunca null
    }

    public void inserir(Consulta consulta) {
        consultas.add(consulta);
    }

    public void atualizar(Consulta consulta) {
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getId() == consulta.getId()) {
                consultas.set(i, consulta);
                return;
            }
        }
    }

    public void deletar(int id) {
        consultas.removeIf(c -> c.getId() == id);
    }

    public List<Consulta> listarTodas() {
        return new ArrayList<>(consultas);
    }

    public List<Consulta> buscarPorData(LocalDate data) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getData().equals(data)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Consulta> buscarPorNomePaciente(String nome) {
        List<Consulta> resultado = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getPaciente().getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
