package br.com.project;

import br.com.project.config.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ScreenManager.setStage(stage);
		ScreenManager.switchScene("/br/com/project/view/LoginScreen.fxml");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
