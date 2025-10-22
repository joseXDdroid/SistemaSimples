package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.util.conexao;

public class clienteDAO {
	// AUTENTICAR USUARIO E SENHA
	public boolean autenticar(String usuario, String senha) {
		try {
			String sql = "select *from clientes where BINARY usuario=? and BINARY senha=?";
			Connection conn = conexao.getConnection();
			PreparedStatement query = conn.prepareStatement(sql);

			query.setString(1, usuario);
			query.setString(2, senha);

			ResultSet resultado = query.executeQuery();
			return resultado.next();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
