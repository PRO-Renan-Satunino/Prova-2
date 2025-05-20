package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela inicial do sistema com menu de navegação.
 */
public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Consultas Médicas");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botões principais
        JButton btnPaciente = new JButton("Pacientes");
        JButton btnMedico = new JButton("Médicos");
        JButton btnConsulta = new JButton("Consultas");

        // Ações
        btnPaciente.addActionListener(_ -> new PacienteView().setVisible(true));
        btnMedico.addActionListener(_ -> new MedicoView().setVisible(true));
        btnConsulta.addActionListener(_ -> new ConsultaView().setVisible(true));

        // Layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(btnPaciente);
        panel.add(btnMedico);
        panel.add(btnConsulta);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
