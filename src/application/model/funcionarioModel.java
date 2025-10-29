package application.model;

import java.util.Date;

public class funcionarioModel {

	private int id;
	private String nome;
	private String cpf;
	private String rg;
	private String cargo;
	private String salario;
	private String usuario;
	private String senha;
	private Date data_cadastro;
	private Date data_alteracao;

	public funcionarioModel(int id, String nome, String cpf, String rg, String cargo, String salario, String usuario,
			String senha, Date data_cadastro, Date data_alteracao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.cargo = cargo;
		this.salario = salario;
		this.usuario = usuario;
		this.senha = senha;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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