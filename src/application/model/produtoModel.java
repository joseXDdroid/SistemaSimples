package application.model;

import java.util.Date;

public class produtoModel {

	private int id;
	private String nome;
	private String descricao;
	private Double preco;
	private int estoque;
	private String codBarras;
	private Date data_cadastro;
	private Date data_alteracao;

	public produtoModel(int id, String nome, String descricao, Double preco, int estoque, String codBarras,
			Date data_cadastro, Date data_alteracao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.codBarras = codBarras;
		this.data_alteracao = data_alteracao;
		this.data_cadastro = data_cadastro;
	}

	/* GETTERS E SETTERS */

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getCod_barras() {
		return codBarras;
	}

	public void setCod_barras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

}