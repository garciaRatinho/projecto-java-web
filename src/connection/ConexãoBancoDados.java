package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexãoBancoDados {
	
    private static String url_banco = "jdbc:postgresql://localhost:5432/curso-JSP";
    private static String user = "postgres";
    private static String senha = "Admin";
    private static Connection connection = null;
     
    public static Connection getConnection() {
		return connection;
    	
    }
    static {
    	conectar();
    }
    public ConexãoBancoDados() {
		conectar();/*quando estiver uma instância vai conectar*/
	}
    private static void conectar() {
    	
    	try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");/*carrega a driver da conexão*/
				connection = DriverManager.getConnection(url_banco, user, senha);
				connection.setAutoCommit(false);/*para não alterar nada no banco sem nosso comando*/
			}
    		
		} catch (Exception e) {
			e.printStackTrace();/*mostrar qualquer erro no momento de conectar*/
		}
    }
	
}
