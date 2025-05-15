package view;

import controller.MedicoController;
import model.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Tela de cadastro e listagem de médicos.
 */
public class MedicoView extends JFrame {

    private JTextField txtNome = new JTextField();
    private JTextField txtEspecialidade = new JTextField();
    private JTextField txtCrm = new JTextField();
    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();

    private MedicoController controller = new MedicoController();
    private int medicoSelecionado = 0;

    public MedicoView() {
        setTitle("Gerenciar Médicos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2));
        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Especialidade:"));
        form.add(txtEspecialidade);
        form.add(new JLabel("CRM:"));
        form.add(txtCrm);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        form.add(btnSalvar);
        form.add(btnExcluir);

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Especialidade");
        modelo.addColumn("CRM");
        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int row = tabela.getSelectedRow();
                medicoSelecionado = (int) modelo.getValueAt(row, 0);
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtEspecialidade.setText(modelo.getValueAt(row, 2).toString());
                txtCrm.setText(modelo.getValueAt(row, 3).toString());
            }
        });

        carregarMedicos();
    }

    private void salvar() {
        String nome = txtNome.getText().trim();
        String esp = txtEspecialidade.getText().trim();
        String crm = txtCrm.getText().trim();

        if (nome.isEmpty() || esp.isEmpty() || crm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Medico m = new Medico(medicoSelecionado, nome, esp, crm);
        controller.salvar(m);
        limparCampos();
        carregarMedicos();
    }

    private void excluir() {
        if (medicoSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um médico para excluir.");
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?");
        if (op == JOptionPane.YES_OPTION) {
            controller.excluir(medicoSelecionado);
            limparCampos();
            carregarMedicos();
        }
    }

    private void carregarMedicos() {
        modelo.setRowCount(0);
        List<Medico> lista = controller.listarTodos();
        for (Medico m : lista) {
            modelo.addRow(new Object[]{
                m.getId(), m.getNome(), m.getEspecialidade(), m.getCrm()
            });
        }
    }

    private void limparCampos() {
        medicoSelecionado = 0;
        txtNome.setText("");
        txtEspecialidade.setText("");
        txtCrm.setText("");
        tabela.clearSelection();
    }
}
