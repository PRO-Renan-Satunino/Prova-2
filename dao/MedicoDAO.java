package dao;

import model.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO em memória para médicos.
 */
public class MedicoDAO {

    private List<Medico> medicos;

    public MedicoDAO() {
        this.medicos = new ArrayList<>(); // inicializado para evitar null
    }

    public void inserir(Medico medico) {
        medicos.add(medico);
    }

    public void atualizar(Medico medico) {
        for (int i = 0; i < medicos.size(); i++) {
            if (medicos.get(i).getId() == medico.getId()) {
                medicos.set(i, medico);
                return;
            }
        }
    }

    public void deletar(int id) {
        medicos.removeIf(medico -> medico.getId() == id);
    }

    public List<Medico> listarTodos() {
        return new ArrayList<>(medicos); // sempre retorna lista válida
    }

    public Medico buscarPorId(int id) {
        return medicos.stream()
                .filter(medico -> medico.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
