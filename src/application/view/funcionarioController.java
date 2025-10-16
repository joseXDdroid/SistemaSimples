package application.view;

import java.util.Date;
import java.util.List;

import application.dao.funcionarioDAO;
import application.model.funcionarioModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class funcionarioController extends formularioController {

	@FXML
	protected TextField txtCargo;

	@FXML
	protected TextField txtCpf;

	@FXML
	protected TextField txtData_cadastro;

	@FXML
	protected TextField txtNome;

	@FXML
	protected TextField txtRg;

	@FXML
	protected TextField txtSalario;

	@FXML
	protected TextField txtSenha;

	@FXML
	protected TextField txtUsuario;

	@FXML
	protected TableView<funcionarioModel> tabDados;

	@FXML
	protected TableColumn<funcionarioModel, String> colDescricao;

	@FXML
	protected TableColumn<funcionarioModel, Integer> colID;

	private ObservableList<funcionarioModel> funcionarioList;

	@FXML
	public void initialize() {
		super.init();

		colID.setCellValueFactory(
				data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getID()).asObject());

		colDescricao
				.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

		carregaDados(null);

		// IDENTIFICA A SELEÇÃO NA TABLE VIEW E ATUALIZA OS DADOS NOS CAMPOS DO
		// FORMULARIO

		tabDados.getSelectionModel().selectedItemProperty().addListener((obs, selecao, novaSelecao) -> {
			if (novaSelecao != null) {
				txtNome.setText(novaSelecao.getNome());
				txtCpf.setText(novaSelecao.getCpf());
				txtRg.setText(novaSelecao.getRg());
				txtCargo.setText(novaSelecao.getCargo());
				txtSalario.setText(novaSelecao.getSalario());
				txtUsuario.setText(novaSelecao.getUsuario());
				txtSenha.setText(novaSelecao.getSenha());
				txtData_cadastro.setText(novaSelecao.getData_cadastro().toString());
			}
		});
	}

	protected void carregaDados(String desc) {
		emEdicao(false);
		habilitarCampos(false);

		funcionarioDAO dao = new funcionarioDAO();
		List<funcionarioModel> funcionarios = dao.listarFuncionarios(desc);
		funcionarioList = FXCollections.observableArrayList(funcionarios);
		tabDados.setItems(funcionarioList);

		if (!funcionarioList.isEmpty()) {
			tabDados.getSelectionModel().selectFirst();
			tabDados.getFocusModel();

			funcionarioModel funcionario = tabDados.getSelectionModel().getSelectedItem();
			if (funcionario != null) {
				txtNome.setText(funcionario.getNome());
				txtCpf.setText(funcionario.getCpf());
				txtRg.setText(funcionario.getRg());
				txtCargo.setText(funcionario.getCargo());
				txtSalario.setText(funcionario.getSalario());
				txtUsuario.setText(funcionario.getUsuario());
				txtSenha.setText(funcionario.getSenha());
				txtData_cadastro.setText(funcionario.getData_cadastro().toString());
			}
		}

	}

	@FXML
	protected void Salvar() {
		funcionarioDAO dao = new funcionarioDAO();

		try {

			String nome = txtNome.getText();
			String cpf = txtCpf.getText();
			String rg = txtRg.getText();
			String cargo = txtCargo.getText();
			String salario = txtSalario.getText();
			String usuario = txtUsuario.getText();
			String senha = txtSenha.getText();
			Date data = new Date();

			if (statusForm == 1) {
				funcionarioModel novoFuncionario = new funcionarioModel(0, nome, cpf, rg, cargo, salario, usuario,
						senha, data, data);
				boolean ok = dao.inserirFuncionario(novoFuncionario);

				if (ok) {
					// MENAGEM DE CADASTRO CONCLUIDO
				} else {
					// MENSAGEM DE ERRO AO CADASTRA
				}

			} else if (statusForm == 2) {
				int id = tabDados.getSelectionModel().getSelectedItem().getID();
				funcionarioModel atualizarFuncionario = new funcionarioModel(id, nome, cpf, rg, cargo, salario, usuario,
						senha, null, null);
				boolean ok = dao.atualiazrFuncionario(atualizarFuncionario);
				if (ok) {
					// MENSAGEM DE ALTERAÇÃO BEM SUCEDIDO
				} else {
					// MENSAGEM DE ERRO AO ATUALIZAR
				}

			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			carregaDados(null);
		}
	}

	@FXML
	protected void Excluir() {
		funcionarioDAO dao = new funcionarioDAO();
		int id = tabDados.getSelectionModel().getSelectedItem().getID();
		boolean ok = dao.excluirFuncionario(id);

		try {
			if (ok) {
				// MENSAGEM DE EXCLUIDO COM SUCESSO
				carregaDados(null);
			} else {
				// MENSAGEM DE ERRO AO EXCLUIR
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			carregaDados(null);
		}

	}
}
