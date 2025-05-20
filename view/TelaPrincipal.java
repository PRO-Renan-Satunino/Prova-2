package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela inicial do sistema com menu de navegação.
 */
public class TelaPrincipal extends JFrame {

    // Construtor da tela principal
    public TelaPrincipal() {
        setTitle("Sistema de Consultas Médicas"); // Define o título da janela
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o programa ao clicar no X
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Botões principais
        JButton btnPaciente = new JButton("Pacientes"); // Botão para acessar pacientes
        JButton btnMedico = new JButton("Médicos"); // Botão para acessar médicos
        JButton btnConsulta = new JButton("Consultas"); // Botão para acessar consultas

        // Ações dos botões: abrem as respectivas telas ao serem clicados
        btnPaciente.addActionListener(_ -> new PacienteView().setVisible(true));
        btnMedico.addActionListener(_ -> new MedicoView().setVisible(true));
        btnConsulta.addActionListener(_ -> new ConsultaView().setVisible(true));

        // Layout: organiza os botões em uma grade de 3 linhas e 1 coluna
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(btnPaciente); // Adiciona o botão de pacientes ao painel
        panel.add(btnMedico); // Adiciona o botão de médicos ao painel
        panel.add(btnConsulta); // Adiciona o botão de consultas ao painel

        add(panel, BorderLayout.CENTER); // Adiciona o painel ao centro da janela
    }

    // Método principal: inicia a aplicação e exibe a tela principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
