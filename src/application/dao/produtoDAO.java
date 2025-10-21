package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.model.produtoModel;
import application.util.conexao;

public class produtoDAO {
		
		// LISTA

		public List<produtoModel> listarProduto(String desc) {
			Connection conn = null;
			PreparedStatement query = null;
			ResultSet resultado = null;

			List<produtoModel> Produto = new ArrayList<produtoModel>();
			try {
				conn = conexao.getConnection();
				if (conn == null)
					return Produto;
				String sql = "select *from produtos";

				if (desc != null && !desc.isEmpty()) {
					sql = "select *from produtos where nome like ?";
					query = conn.prepareStatement(sql);
					query.setString(1, "%" + desc + "%");
				} else {
					query = conn.prepareStatement(sql);
				}

				resultado = query.executeQuery();

				while (resultado.next()) {
					produtoModel f = new produtoModel(resultado.getInt("id_produto"),
							resultado.getString("nome"), resultado.getString("descricao"), resultado.getDouble("preco"),
							resultado.getInt("estoque"),resultado.getString("cod_barras"),resultado.getDate("data_cadastro"),
							resultado.getDate("data_alteracao"));
					f.setID(resultado.getInt("id_produto"));
					f.setNome(resultado.getString("nome"));
					f.setDescricao(resultado.getString("descricao"));
					f.setPreco(resultado.getDouble("preco"));
					f.setEstoque(resultado.getInt("estoque"));
					f.setCod_barras(resultado.getString("cod_barras"));
					f.setData_cadastro(resultado.getDate("data_cadastro"));
					f.setData_alteracao(resultado.getDate("data_alteracao"));
					Produto.add(f);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return Produto;
		}

		// INSERT
		public boolean inserirProduto(produtoModel f) {
			Connection conn = null;
			PreparedStatement query = null;
			try {
				conn = conexao.getConnection();
				String sql = "insert produtos(nome,descricao,preco,estoque,cod_barras,data_cadastro,data_alteracao) values (?,?,?,?,?,now(),null)";

				query=conn.prepareStatement(sql);
				query.setString(1, f.getNome());
				query.setString(2, f.getDescricao());
				query.setDouble(3, f.getPreco());
				query.setInt(4, f.getEstoque());
				query.setString(5, f.getCod_barras());

				int insert = query.executeUpdate();

				return insert > 0;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		// UPDATE

		public boolean atualizarProduto(produtoModel f) {
			Connection conn=null;
			PreparedStatement query = null;
			try {
			conn=conexao.getConnection();
			String sql="update produtos set nome=?, descricao=?, preco=?, estoque=?,cod_barras=?,"+
			"data_alteracao=now() where id_produto=?";
			
			query=conn.prepareStatement(sql);
			query.setString(1, f.getNome());
			query.setString(2, f.getDescricao());
			query.setDouble(3, f.getPreco());
			query.setInt(4, f.getEstoque());
			query.setString(5, f.getCod_barras());
			query.setInt(6, f.getID());
			
			int update = query.executeUpdate();
			
			return update>0;
			
			}catch(Exception e ) {
				e.printStackTrace();
			return false;
			}		
		}

		// DELETE
		public boolean excluirProduto(int id) {
			Connection conn = null;
			PreparedStatement query = null;
			
			try {
			conn= conexao.getConnection();
			String sql = "delete from produtos where id_produto=?";
			
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
