package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConexãoBancoDados;
import modelos.ModeloLogin;

public class DAOlogin {
	
	private static Connection connection;
	
	public DAOlogin() {
		connection = ConexãoBancoDados.getConnection();
	}
	public static boolean validarAutenticar(ModeloLogin modeloLogin) throws SQLException {
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modeloLogin.getLogin());
		statement.setString(2, modeloLogin.getSenha());
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()) {
			return true;
		}
		return false;
	}
}
