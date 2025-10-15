package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import application.model.fucionarioModel;
import application.util.conexao;

public class usuarioDAO {

	// LISTA

	public List<fucionarioModel> listarFuncionarios(String desc) {
		Connection conn = null;
		PreparedStatement query = null;
		ResultSet resultado = null;

		List<fucionarioModel> fucionarios = new ArrayList<fucionarioModel>();
		try {
			conn = conexao.getConnection();
			if (conn == null)
				return fucionarios;
			String sql = "select *from fucionario";

			if (desc != null && !desc.isEmpty()) {
				sql = "select *from fucionario where nome like ?";
				query = conn.prepareStatement(sql);
				query.setString(1, "%" + sql + "%");
			} else {
				query = conn.prepareStatement(sql);
			}

			resultado = query.executeQuery();

			while (resultado.next()) {
				fucionarioModel f = new fucionarioModel(resultado.getInt("id_fucionario"), resultado.getString("nome"),
						resultado.getString("cpf"), resultado.getString("rg"), resultado.getString("cargo"),
						resultado.getString("salario"), resultado.getString("usuario"), resultado.getString("senha"),
						resultado.getDate("data_cadastro"), resultado.getDate("data_alteracao"));
				f.setID(resultado.getInt("id_funcionario"));
				f.setNome(resultado.getString("nome"));
				f.setCpf(resultado.getString("cpf"));
				f.setRg(resultado.getString("rg"));
				f.setCargo(resultado.getString("cargo"));
				f.setSalario(resultado.getString("salario"));
				f.setUsuario(resultado.getString("usuario"));
				f.setSenha(resultado.getString("senha"));
				f.setData_cadastro(resultado.getDate("data_cadastro"));
				f.setData_alteracao(resultado.getDate("data_alteracao"));
				fucionarios.add(f);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fucionarios;
	}

	// INSERT

	// UPDATE

	// DELETE

}
