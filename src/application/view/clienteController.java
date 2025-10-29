package application.view;

import java.util.Date;
import java.util.List;


import application.dao.clienteDAO;
import application.dao.funcionarioDAO;
import application.model.clienteModel;
import application.model.funcionarioModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class clienteController extends formularioController {

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtData_cadastro;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtTelefone;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnNovo;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnSalvar;

	@FXML
	private AnchorPane formCadastro;

	@FXML
	private TextField txtBuscar;

	@FXML
	protected TableView<clienteModel> tabDados;

	@FXML
	protected TableColumn<clienteModel, String> colDescricao;

	@FXML
	protected TableColumn<clienteModel, Integer> colID;

	private ObservableList<clienteModel> clienteList;

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
				txtTelefone.setText(novaSelecao.getTelefone());
				txtEmail.setText(novaSelecao.getEmail());
				txtData_cadastro.setText(novaSelecao.getData_cadastro().toString());
			}
		});
	}
	
	protected void carregaDados(String desc) {
		emEdicao(false);
		habilitarCampos(false);

		clienteDAO dao = new clienteDAO();
		List<clienteModel> clientes = dao.listarCliente(desc);
		clienteList = FXCollections.observableArrayList(clientes);
		tabDados.setItems(clienteList);

		if (!clienteList.isEmpty()) {
			tabDados.getSelectionModel().selectFirst();
			tabDados.getFocusModel();

			clienteModel cliente = tabDados.getSelectionModel().getSelectedItem();
			if (cliente != null) {
				txtNome.setText(cliente.getNome());
				txtCpf.setText(cliente.getCpf());
				txtTelefone.setText(cliente.getTelefone());
				txtEmail.setText(cliente.getEmail());
				txtData_cadastro.setText(cliente.getData_cadastro().toString());
			}
		}

	}
	
	@FXML
	protected void Salvar() {
		clienteDAO dao = new clienteDAO();

		try {

			String nome = txtNome.getText();
			String cpf = txtCpf.getText();
			String telefone = txtTelefone.getText();
			String email = txtEmail.getText();
			Date data = new Date();

			if (statusForm == 1) {
				clienteModel novoCliente = new clienteModel(0, nome, cpf, telefone, email, data, data);
				boolean ok = dao.inserirCliente(novoCliente);

				if (ok) {
					// MENAGEM DE CADASTRO CONCLUIDO
				} else {
					// MENSAGEM DE ERRO AO CADASTRA
				}

			} else if (statusForm == 2) {
				int id = tabDados.getSelectionModel().getSelectedItem().getID();
				clienteModel atualizarCliente = new clienteModel(id, nome, cpf, telefone, email, null, null);
				boolean ok = dao.atualizarCliente(atualizarCliente);
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
		clienteDAO dao = new clienteDAO();
		int id = tabDados.getSelectionModel().getSelectedItem().getID();
		boolean ok = dao.excluirCliente(id);

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