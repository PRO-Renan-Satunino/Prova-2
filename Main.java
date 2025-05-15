import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import view.TelaPrincipal;

/**
 * Classe principal para iniciar o sistema de consultas médicas.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Define o visual do sistema para o nativo da plataforma (opcional)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível aplicar o tema nativo.");
        }

        // Inicializa a interface gráfica
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
