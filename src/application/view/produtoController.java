package application.view;

import java.util.Date;
import java.util.List;

import application.dao.produtoDAO;
import application.model.funcionarioModel;
import application.model.produtoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class produtoController extends formularioController {

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
	private TableColumn<produtoModel, String> colDescricao;

	@FXML
	private TableColumn<produtoModel, Integer> colID;

	private ObservableList<produtoModel> produtoList;
	
	@FXML
	private AnchorPane formCadastro;

	@FXML
	private TableView<produtoModel> tabDados;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TextField txtData_cadastro;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtEstoque;
	
	@FXML
    private TextField txtCod_Barras;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPreco;

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
				txtPreco.setText(novaSelecao.getPreco().toString());
				txtEstoque.setText(String.valueOf(novaSelecao.getEstoque()));
				txtCod_Barras.setText(novaSelecao.getCod_barras());
				txtDescricao.setText(novaSelecao.getDescricao());
				txtData_cadastro.setText(novaSelecao.getData_cadastro().toString());
			}
		});
	}

	protected void carregaDados(String desc) {
		emEdicao(false);
		habilitarCampos(false);

		produtoDAO dao = new produtoDAO();
		List<produtoModel> produtos = dao.listarProduto(desc);
		produtoList = FXCollections.observableArrayList(produtos);
		tabDados.setItems(produtoList);

		if (!produtoList.isEmpty()) {
			tabDados.getSelectionModel().selectFirst();
			tabDados.getFocusModel();

			produtoModel produto = tabDados.getSelectionModel().getSelectedItem();
			if (produto != null) {
				txtNome.setText(produto.getNome());
				txtPreco.setText(produto.getPreco().toString());
				txtEstoque.setText(String.valueOf(produto.getEstoque()));
				txtCod_Barras.setText(produto.getCod_barras());
				txtDescricao.setText(produto.getDescricao());
				txtData_cadastro.setText(produto.getData_cadastro().toString());
			}
		}

	}

	@FXML
	protected void Salvar() {
		produtoDAO dao = new produtoDAO();

		try {

			String nome = txtNome.getText();
			Double preco = Double.valueOf(txtPreco.getText());
			Integer estoque = Integer.valueOf(txtEstoque.getText());
			String cod_barras = txtCod_Barras.getText();
			String descricao = txtDescricao.getText();
			Date data = new Date();

			if (statusForm == 1) {
				produtoModel novoProduto = new produtoModel(0, nome, descricao, preco, estoque, cod_barras, data, data);
				boolean ok = dao.inserirProduto(novoProduto);

				if (ok) {
					// MENAGEM DE CADASTRO CONCLUIDO
				} else {
					// MENSAGEM DE ERRO AO CADASTRA
				}

			} else if (statusForm == 2) {
				int id = tabDados.getSelectionModel().getSelectedItem().getID();
				produtoModel atualizarProduto = new produtoModel(id, nome, descricao, preco, estoque, cod_barras, null, null);
				boolean ok = dao.atualizarProduto(atualizarProduto);
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
		produtoDAO dao = new produtoDAO();
		int id = tabDados.getSelectionModel().getSelectedItem().getID();
		boolean ok = dao.excluirProduto(id);

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