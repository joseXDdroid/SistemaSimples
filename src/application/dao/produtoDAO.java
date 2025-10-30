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

	public List<produtoModel> listarProduto(String desc, int tipo){
		Connection conn = null;
		PreparedStatement query=null;
		ResultSet resultado=null;
		
		List <produtoModel> produtos = new ArrayList <produtoModel>();
		try {
			conn=conexao.getConnection();
			if(conn==null) return produtos;
			String sql="select *from produtos";
			
			if(desc!=null && !desc.isEmpty()) {
				if (tipo==1) {
				 sql="select * from produtos where nome like ?";
				 query=conn.prepareStatement(sql);
				 query.setString(1, "%"+desc+"%");
				} else if(tipo==2) {
				 sql="select * from produtos where codbarras = ?";
				 query=conn.prepareStatement(sql);
				 query.setString(1, desc);
				}
				
			} else {
				query=conn.prepareStatement(sql);
			}
			
			resultado = query.executeQuery();
			
			while (resultado.next()) {
				produtoModel p = new produtoModel(resultado.getInt("id_produto"),
						resultado.getString("nome"), resultado.getString("descricao"), resultado.getDouble("preco"),
						resultado.getInt("estoque"),resultado.getString("cod_barras"),resultado.getDate("data_cadastro"),
						resultado.getDate("data_alteracao"));
				p.setID(resultado.getInt("id_produto"));
				p.setNome(resultado.getString("nome"));
				p.setDescricao(resultado.getString("descricao"));
				p.setPreco(resultado.getDouble("preco"));
				p.setEstoque(resultado.getInt("estoque"));
				p.setCod_barras(resultado.getString("cod_barras"));
				p.setData_cadastro(resultado.getDate("data_cadastro"));
				p.setData_alteracao(resultado.getDate("data_alteracao"));
				produtos.add(p);

			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

		// INSERT
		public boolean inserirProduto(produtoModel p) {
			Connection conn=null;
			PreparedStatement query = null;
			try {
			conn=conexao.getConnection();
			String sql="insert produtos"+
			"(nome,descricao,codbarras,preco,estoque,data_cadastro,data_alteracao)"+
			" values (?,?,?,?,?,now(),null)";
			
			query=conn.prepareStatement(sql);
			query.setString(1, p.getNome());
			query.setString(2, p.getDescricao());
			query.setString(3, p.getCod_barras());
			query.setDouble(4, p.getPreco());
			query.setInt(5, p.getEstoque());
			
			
			int insert = query.executeUpdate();
			
			return insert>0;
			
			}catch(Exception e ) {
				e.printStackTrace();
			return false;
			}			
			
		}

		// UPDATE

		public boolean atualizarProduto(produtoModel p) {
			Connection conn=null;
			PreparedStatement query = null;
			try {
			conn=conexao.getConnection();
			String sql="update produtos set nome=?, descricao=?, preco=?, estoque=?,cod_barras=?,"+
			"data_alteracao=now() where id_produto=?";
			
			query=conn.prepareStatement(sql);
			query.setString(1, p.getNome());
			query.setString(2, p.getDescricao());
			query.setDouble(3, p.getPreco());
			query.setInt(4, p.getEstoque());
			query.setString(5, p.getCod_barras());
			query.setInt(6, p.getID());
			
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
