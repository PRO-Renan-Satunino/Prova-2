package view;

import controller.ConsultaController;
import controller.MedicoController;
import controller.PacienteController;
import model.Consulta;
import model.Medico;
import model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Tela para agendar e listar consultas.
 */
public class ConsultaView extends JFrame {

    private JComboBox<Paciente> cbPaciente = new JComboBox<>();
    private JComboBox<Medico> cbMedico = new JComboBox<>();
    private JTextField txtData = new JTextField("2025-01-01");
    private JTextField txtHora = new JTextField("14:00");
    private JTextField txtObs = new JTextField();
    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();

    private ConsultaController controller = new ConsultaController();
    private PacienteController pacienteController = new PacienteController();
    private MedicoController medicoController = new MedicoController();

    private int consultaSelecionada = 0;

    public ConsultaView() {
        setTitle("Gerenciar Consultas");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(6, 2));
        form.add(new JLabel("Paciente:"));
        form.add(cbPaciente);
        form.add(new JLabel("Médico:"));
        form.add(cbMedico);
        form.add(new JLabel("Data (YYYY-MM-DD):"));
        form.add(txtData);
        form.add(new JLabel("Hora (HH:MM):"));
        form.add(txtHora);
        form.add(new JLabel("Observação:"));
        form.add(txtObs);

        JButton btnSalvar = new JButton("Agendar");
        JButton btnExcluir = new JButton("Excluir");
        form.add(btnSalvar);
        form.add(btnExcluir);

        modelo.addColumn("ID");
        modelo.addColumn("Paciente");
        modelo.addColumn("Médico");
        modelo.addColumn("Data");
        modelo.addColumn("Hora");
        modelo.addColumn("Observação");
        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int row = tabela.getSelectedRow();
                consultaSelecionada = (int) modelo.getValueAt(row, 0);
                txtData.setText(modelo.getValueAt(row, 3).toString());
                txtHora.setText(modelo.getValueAt(row, 4).toString());
                txtObs.setText(modelo.getValueAt(row, 5).toString());
                cbPaciente.setSelectedItem(modelo.getValueAt(row, 1));
                cbMedico.setSelectedItem(modelo.getValueAt(row, 2));
            }
        });

        carregarCombos();
        carregarConsultas();
    }

    private void salvar() {
        try {
            LocalDate data = LocalDate.parse(txtData.getText().trim());
            LocalTime hora = LocalTime.parse(txtHora.getText().trim());
            Paciente paciente = (Paciente) cbPaciente.getSelectedItem();
            Medico medico = (Medico) cbMedico.getSelectedItem();
            String obs = txtObs.getText();

            if (paciente == null || medico == null) {
                JOptionPane.showMessageDialog(this, "Selecione paciente e médico.");
                return;
            }

            Consulta c = new Consulta(consultaSelecionada, paciente, medico, data, hora, obs);
            controller.salvar(c);
            limparCampos();
            carregarConsultas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro nos campos: " + e.getMessage());
        }
    }

    private void excluir() {
        if (consultaSelecionada == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para excluir.");
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?");
        if (op == JOptionPane.YES_OPTION) {
            controller.excluir(consultaSelecionada);
            limparCampos();
            carregarConsultas();
        }
    }

    private void carregarConsultas() {
        modelo.setRowCount(0);
        List<Consulta> lista = controller.listarTodas();
        for (Consulta c : lista) {
            modelo.addRow(new Object[]{
                c.getId(), c.getPaciente(), c.getMedico(),
                c.getData(), c.getHora(), c.getObservacao()
            });
        }
    }

    private void carregarCombos() {
        cbPaciente.removeAllItems();
        cbMedico.removeAllItems();
        for (Paciente p : pacienteController.listarTodos()) cbPaciente.addItem(p);
        for (Medico m : medicoController.listarTodos()) cbMedico.addItem(m);
    }

    private void limparCampos() {
        consultaSelecionada = 0;
        txtData.setText("");
        txtHora.setText("");
        txtObs.setText("");
        cbPaciente.setSelectedIndex(-1);
        cbMedico.setSelectedIndex(-1);
        tabela.clearSelection();
    }
}
