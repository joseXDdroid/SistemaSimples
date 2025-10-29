package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.model.clienteModel;
import application.model.funcionarioModel;
import application.util.conexao;

public class clienteDAO {
	// LISTA

		public List<clienteModel> listarCliente(String desc) {
			Connection conn = null;
			PreparedStatement query = null;
			ResultSet resultado = null;

			List<clienteModel> clientes = new ArrayList<clienteModel>();
			try {
				conn = conexao.getConnection();
				if (conn == null)
					return clientes;
				String sql = "select *from clientes";

				if (desc != null && !desc.isEmpty()) {
					sql = "select *from clientes where nome like ?";
					query = conn.prepareStatement(sql);
					query.setString(1, "%" + desc + "%");
				} else {
					query = conn.prepareStatement(sql);
				}

				resultado = query.executeQuery();

				while (resultado.next()) {
					clienteModel f = new clienteModel(resultado.getInt("id_cliente"),
							resultado.getString("nome"), resultado.getString("cpf"), resultado.getString("telefone"),
							resultado.getString("email"), resultado.getDate("data_cadastro"),
							resultado.getDate("data_alteracao"));
					f.setID(resultado.getInt("id_cliente"));
					f.setNome(resultado.getString("nome"));
					f.setCpf(resultado.getString("cpf"));
					f.setTelefone(resultado.getString("telefone"));
					f.setEmail(resultado.getString("email"));
					f.setData_cadastro(resultado.getDate("data_cadastro"));
					f.setData_alteracao(resultado.getDate("data_alteracao"));
					clientes.add(f);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return clientes;
		}
		
		// INSERT
		public boolean inserirCliente(clienteModel f) {
			Connection conn = null;
			PreparedStatement query = null;
			try {
				conn = conexao.getConnection();
				String sql = "insert clientes(nome,cpf,telefone,email,data_cadastro,data_alteracao) values (?,?,?,?,now(),null)";

				query=conn.prepareStatement(sql);
				query.setString(1, f.getNome());
				query.setString(2, f.getCpf());
				query.setString(3, f.getTelefone());
				query.setString(4, f.getEmail());

				int insert = query.executeUpdate();

				return insert > 0;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// UPDATE

		public boolean atualizarCliente(clienteModel f) {
			Connection conn=null;
			PreparedStatement query = null;
			try {
			conn=conexao.getConnection();
			String sql="update clientes set nome=?, cpf=?, telefone=?, email=?,data_alteracao=now() where id_cliente=?";
			
			query=conn.prepareStatement(sql);
			query.setString(1, f.getNome());
			query.setString(2, f.getCpf());
			query.setString(3, f.getTelefone());
			query.setString(4, f.getEmail());
			query.setInt(5, f.getID());
			
			int update = query.executeUpdate();
			
			return update>0;
			
			}catch(Exception e ) {
				e.printStackTrace();
			return false;
			}		
		}
		
		// DELETE
		public boolean excluirCliente(int id) {
			Connection conn = null;
			PreparedStatement query = null;
			
			try {
			conn= conexao.getConnection();
			String sql = "delete from clientes where id_cliente=?";
			
			query=conn.prepareStatement(sql);
			query.setInt(1, id);
			
			int delete = query.executeUpdate();
			
			return delete>1;
				
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

}