package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConexãoBancoDados;
import modelos.ModeloLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		
		connection = ConexãoBancoDados.getConnection();
		
	}
	
	public ModeloLogin gravarUsuario(ModeloLogin objeto) throws Exception {
		if(objeto.isNOVO()) {
			
			/*INSERIR USUÁRIO*/
			
		String sql = "INSERT INTO model_login(login,senha,nome,email) VALUES (?,?,?,?)";
		
		PreparedStatement preparSql = connection.prepareStatement(sql);
		preparSql.setString(1, objeto.getLogin());
		preparSql.setString(2, objeto.getSenha());
		preparSql.setString(3, objeto.getNome());
		preparSql.setString(4, objeto.getEmail());
		preparSql.execute();
		
		connection.commit();
		}else {
			
			/*ATUALIZAR USUÁRIO*/
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?WHERE id = "+objeto.getId() +";";
			
			PreparedStatement preparSql = connection.prepareStatement(sql);
			preparSql.setString(1, objeto.getLogin());
			preparSql.setString(2, objeto.getSenha());
			preparSql.setString(3, objeto.getNome());
			preparSql.setString(4, objeto.getEmail());
			preparSql.executeUpdate();
			
			connection.commit();
		}
		
		return this.consultarUsuario(objeto.getLogin()); 
	}
	
	public List<ModeloLogin> listaDeConsultarUsuario (String nomeBusca) throws Exception{
		
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		String sql = "select * from model_login where upper(nome) like upper(?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nomeBusca + "%");
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			
			retorno.add(modeloLogin);
			
		}
		
		
		return retorno;
	}
	
	public ModeloLogin consultarUsuario(String login) throws Exception{
		
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			
		}
		
		return modeloLogin;
	}
	
	/* Metodo de Validação de login*/
	
	public boolean  validarLogin(String login) throws Exception{
		
		String sql = "select count(1)>0 as existe from model_login where upper(login) = upper('"+login+"')";
		
        PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
		
		
	}
	
	public void excluirUsuario(String idUser) throws Exception{
		
		String sql = "DELETE FROM model_login WHERE id =?;";
		
		PreparedStatement preparSql = connection.prepareStatement(sql);
		
		preparSql.setLong(1, Long.parseLong(idUser));
		
		preparSql.executeUpdate();
		
		connection.commit();
	}
}
