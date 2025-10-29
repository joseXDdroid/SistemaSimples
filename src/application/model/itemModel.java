package application.model;

import java.util.Date;

public class itemModel {
	private int id_item;
	private int id_pedido;
	private int id_produto;
	private int quantidade;
	private String cod_barras;
	private String descricao;
	private Double preco_unitario;
	private Double desconto;
	private Double valor_total;
	private Date data_cadastro;
	private Date data_alteracao;

	public itemModel(int id_item, int id_pedido, int id_produto, int quantidade, String cod_barras, String descricao,
			Double preco_unitario, Double desconto, Double valor_total, Date data_cadastro, Date data_alteracao) {
		this.id_item = id_item;
		this.id_pedido = id_pedido;
		this.id_produto = id_produto;
		this.quantidade = quantidade;
		this.cod_barras = cod_barras;
		this.descricao = descricao;
		this.preco_unitario = preco_unitario;
		this.desconto = desconto;
		this.valor_total = valor_total;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
	}

	// GETTERS E SETTERS

	public int getIdItem() {
		return id_item;
	}

	public void setIdItem(int id_item) {
		this.id_item = id_item;
	}

	public int getIdPedido() {
		return id_pedido;
	}

	public void setIdPedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getIdProduto() {
		return id_produto;
	}

	public void setIdProduto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getCod_Barras() {
		return cod_barras;
	}

	public void setCod_Barras(String cod_barras) {
		this.cod_barras = cod_barras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPrecoUnitario() {
		return preco_unitario;
	}

	public void setPrecoUnitario(Double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getValorTotal() {
		return valor_total;
	}

	public void setValorTotal(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Date getDataCadastro() {
		return data_cadastro;
	}

	public void setDataCadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getDataAlteracao() {
		return data_alteracao;
	}

	public void setDataAlteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
}