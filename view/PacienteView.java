package view;

import controller.PacienteController;
import model.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de cadastro e listagem de pacientes.
 */
public class PacienteView extends JFrame {

    private JTextField txtNome = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JTextField txtTelefone = new JTextField();
    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();

    private PacienteController controller = new PacienteController();
    private int pacienteSelecionado = 0; // ID do paciente selecionado

    public PacienteView() {
        setTitle("Gerenciar Pacientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel form = new JPanel(new GridLayout(4, 2));
        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("CPF:"));
        form.add(txtCpf);
        form.add(new JLabel("Telefone:"));
        form.add(txtTelefone);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");

        form.add(btnSalvar);
        form.add(btnExcluir);

        // Tabela
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        tabela.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Ações
        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int row = tabela.getSelectedRow();
                pacienteSelecionado = (int) modelo.getValueAt(row, 0);
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtCpf.setText(modelo.getValueAt(row, 2).toString());
                txtTelefone.setText(modelo.getValueAt(row, 3).toString());
            }
        });

        carregarPacientes();
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Paciente p = new Paciente(pacienteSelecionado, nome, cpf, telefone);
        controller.salvar(p);

        limparCampos();
        carregarPacientes();
    }

    private void excluir() {
        if (pacienteSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para excluir.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?");
        if (opcao == JOptionPane.YES_OPTION) {
            controller.excluir(pacienteSelecionado);
            limparCampos();
            carregarPacientes();
        }
    }

    private void carregarPacientes() {
        modelo.setRowCount(0); // limpa a tabela
        List<Paciente> lista = controller.listarTodos();

        for (Paciente p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNome(), p.getCpf(), p.getTelefone()
            });
        }
    }

    private void limparCampos() {
        pacienteSelecionado = 0;
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        tabela.clearSelection();
    }
}
