package application.view;

import application.model.clienteModel;
import application.model.funcionarioModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class clienteController {

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
	protected TableColumn<clienteModel, String> colDescricao;

    @FXML
	protected TableView<clienteModel> tabDados;
	
	@FXML
	protected TableColumn<clienteModel, Integer> colID;
	
	private ObservableList<clienteModel> clienteList;

    @FXML
    private AnchorPane formCadastro;

    @FXML
    private TextField txtBuscar;

}
