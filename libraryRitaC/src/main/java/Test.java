import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

        public static void main(String[] args) {
            // Declaração de variáveis para conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3306/books?useSSL=false&serverTimezone=UTC";    // Substitua "books" pelo nome do seu banco de dados
            String username = "root";  // Substitua "seu_usuario" pelo seu nome de usuário do MySQL
            String password = "RITA1007lolinho!";  // Substitua "sua_senha" pela sua senha do MySQL

            // Tentativa de conectar ao banco de dados
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                System.out.println("Conexão bem-sucedida!");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados:");
                e.printStackTrace();
            }
        }
    }


