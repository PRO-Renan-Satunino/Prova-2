package dao;
import java.sql.Connection; 

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = Conexao.conectar()) {
            System.out.println("✅ Conectado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar: " + e.getMessage());
        }
    }
}
