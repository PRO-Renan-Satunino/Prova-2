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

    // Construtor da tela de médicos
    public MedicoView() {
        setTitle("Gerenciar Médicos"); // Define o título da janela
        setSize(500, 400); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout()); // Define o layout principal

        // Painel de formulário para cadastro/edição
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

        // Configuração da tabela de médicos
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Especialidade");
        modelo.addColumn("CRM");
        tabela.setModel(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        add(form, BorderLayout.NORTH); // Adiciona o formulário na parte superior
        add(scroll, BorderLayout.CENTER); // Adiciona a tabela no centro

        // Ação do botão Salvar: chama o método salvar()
        btnSalvar.addActionListener(_ -> salvar());
        // Ação do botão Excluir: chama o método excluir()
        btnExcluir.addActionListener(_ -> excluir());

        // Seleção de linha na tabela: preenche os campos com os dados do médico selecionado
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int row = tabela.getSelectedRow();
                medicoSelecionado = (int) modelo.getValueAt(row, 0);
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtEspecialidade.setText(modelo.getValueAt(row, 2).toString());
                txtCrm.setText(modelo.getValueAt(row, 3).toString());
            }
        });

        carregarMedicos(); // Carrega os médicos ao abrir a tela
    }

    // Salva ou atualiza um médico
    private void salvar() {
        String nome = txtNome.getText().trim();
        String esp = txtEspecialidade.getText().trim();
        String crm = txtCrm.getText().trim();

        // Validação dos campos
        if (nome.isEmpty() || esp.isEmpty() || crm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Medico m = new Medico(medicoSelecionado, nome, esp, crm);
        controller.salvar(m); // Salva ou atualiza o médico
        limparCampos(); // Limpa os campos após salvar
        carregarMedicos(); // Atualiza a tabela
    }

    // Exclui o médico selecionado
    private void excluir() {
        if (medicoSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um médico para excluir.");
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?");
        if (op == JOptionPane.YES_OPTION) {
            controller.excluir(medicoSelecionado); // Exclui o médico
            limparCampos(); // Limpa os campos após excluir
            carregarMedicos(); // Atualiza a tabela
        }
    }

    // Carrega todos os médicos na tabela
    private void carregarMedicos() {
        modelo.setRowCount(0); // Limpa a tabela
        List<Medico> lista = controller.listarTodos();
        for (Medico m : lista) {
            modelo.addRow(new Object[]{
                m.getId(), m.getNome(), m.getEspecialidade(), m.getCrm()
            });
        }
    }

    // Limpa os campos do formulário e reseta a seleção
    private void limparCampos() {
        medicoSelecionado = 0;
        txtNome.setText("");
        txtEspecialidade.setText("");
        txtCrm.setText("");
        tabela.clearSelection();
    }
}
