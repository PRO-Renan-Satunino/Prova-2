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

    // Campos de texto para entrada de dados do paciente
    private JTextField txtNome = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JTextField txtTelefone = new JTextField();
    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();

    private PacienteController controller = new PacienteController();
    private int pacienteSelecionado = 0; // ID do paciente selecionado

    // Construtor da tela de pacientes
    public PacienteView() {
        setTitle("Gerenciar Pacientes"); // Define o título da janela
        setSize(500, 400); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout()); // Define o layout principal

        // Painel de formulário para cadastro/edição
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

        // Configuração da tabela de pacientes
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        tabela.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        add(form, BorderLayout.NORTH); // Adiciona o formulário na parte superior
        add(scroll, BorderLayout.CENTER); // Adiciona a tabela no centro

        // Ação do botão Salvar: chama o método salvar()
        btnSalvar.addActionListener(e -> salvar());
        // Ação do botão Excluir: chama o método excluir()
        btnExcluir.addActionListener(e -> excluir());

        // Seleção de linha na tabela: preenche os campos com os dados do paciente selecionado
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int row = tabela.getSelectedRow();
                pacienteSelecionado = (int) modelo.getValueAt(row, 0);
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtCpf.setText(modelo.getValueAt(row, 2).toString());
                txtTelefone.setText(modelo.getValueAt(row, 3).toString());
            }
        });

        carregarPacientes(); // Carrega os pacientes ao abrir a tela
    }

    // Salva ou atualiza um paciente
    private void salvar() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();

        // Validação dos campos
        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        Paciente p = new Paciente(pacienteSelecionado, nome, cpf, telefone);
        controller.salvar(p); // Salva ou atualiza o paciente

        limparCampos(); // Limpa os campos após salvar
        carregarPacientes(); // Atualiza a tabela
    }

    // Exclui o paciente selecionado
    private void excluir() {
        if (pacienteSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para excluir.");
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?");
        if (opcao == JOptionPane.YES_OPTION) {
            controller.excluir(pacienteSelecionado); // Exclui o paciente
            limparCampos(); // Limpa os campos após excluir
            carregarPacientes(); // Atualiza a tabela
        }
    }

    // Carrega todos os pacientes na tabela
    private void carregarPacientes() {
        modelo.setRowCount(0); // limpa a tabela
        List<Paciente> lista = controller.listarTodos();

        for (Paciente p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNome(), p.getCpf(), p.getTelefone()
            });
        }
    }

    // Limpa os campos do formulário e reseta a seleção
    private void limparCampos() {
        pacienteSelecionado = 0;
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        tabela.clearSelection();
    }
}
