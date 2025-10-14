package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
	/* ENCAPSULAMENTO DOS COMPONENTES DO FORMULÁRIO */
	/* INICIO ENCAPSULAMENTO */

	@FXML
	private Button btnEntrar;
	@FXML
	private Button btnFechar;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private TextField txtUsuario;

	/* FINAL ENCAPSULAMENTO */

	/* METODO FECHAR O SISTEMA */
	public void close() {
		System.exit(0);
	}

	/* METODO */

	public void login() {
		String usuario = txtUsuario.getText();
		String senha = txtSenha.getText();

		Alert mensagem;

		if (usuario.equals("1") && senha.equals("1")) {
			mensagem = new Alert(Alert.AlertType.CONFIRMATION);
			mensagem.setTitle("Comfimação");
			mensagem.setHeaderText(null);
			mensagem.setContentText("Bem vindo ao Sistema " + usuario);
			mensagem.showAndWait();
			
/*FECHAR TELA DE LOGIN*/
			
			btnEntrar.getScene().getWindow().hide();
			try {
			
			Parent root = FXMLLoader.load(getClass().getResource("principal.fxml")); 
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("Sistema by Rodrigo Faro");
			stage.centerOnScreen();
			stage.setMaximized(true);
			stage.show();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			mensagem = new Alert(Alert.AlertType.ERROR);
			mensagem.setTitle("Error");
			mensagem.setHeaderText(null);
			mensagem.setContentText("Usuário ou Senha incorretos! ");
			mensagem.showAndWait();

		}
	}

	/*
	 * @Override e @FXML INDICA QUE SERÁ CARREGADO JUNTO COM A PAGINA OU FORMULÁRIO
	 */

	@FXML
	public void initialize() {
		/* VINCULAR A AÇÃO DO BOTÃO FECHAR AO METODO CLOSE */
		btnFechar.setOnAction(e -> {close();});

		/* VINCULAR A AÇÃO DO BOTÃO ENTRAR AO METODO LOGIN */
		btnEntrar.setOnAction(e -> {login();});
		
/*ACIONAR O ENTER NOT TEXT FIEL USUARIO E ACESSAR O PASSORD FILD DE SENHA*/
		txtUsuario.setOnAction(e ->{txtSenha.requestFocus();});
		
/*ACIONAR O ENTER NO PASSWORD FILD DE SENHA A ACESSAR O METODO DE LOGIN*/
		txtSenha.setOnAction(e ->{login();});
		
		
		
	}
}