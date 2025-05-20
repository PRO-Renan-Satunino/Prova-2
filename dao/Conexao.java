package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.beans.Statement;
import java.sql.Connection;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/consultas_medicas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //Metodo que retorna a conexão com o banco de dados
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
