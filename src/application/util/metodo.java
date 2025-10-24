package application.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class metodo {

	public static boolean mensagemConfirmacao(String titulo, String cabecalho, String texto) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(texto);

        // Define os botões como SIM e NÃO
        ButtonType SIM = new ButtonType("Sim");
        ButtonType NAO = new ButtonType("Não");

        alert.getButtonTypes().setAll(SIM, NAO);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == SIM;
    }
	
}
