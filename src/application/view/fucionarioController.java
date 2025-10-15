package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class fucionarioController extends formularioController {

	@FXML
    private TextField txtCargo;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtData_cadastro;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRg;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;
	
	@FXML
	public void initialize() {
		super.init();
	}
	
}
