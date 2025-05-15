package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDate data;
    private LocalTime hora;
    private String observacao;

    // Constructors
    public Consulta(int id, Paciente paciente, Medico medico, LocalDate data, LocalTime hora, String observacao) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
}
