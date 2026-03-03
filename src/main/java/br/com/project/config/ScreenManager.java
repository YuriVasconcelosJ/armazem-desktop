package br.com.project.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {

	private static Stage stage;

	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
	}

	public static void switchScene(String tela) {

		try {
			FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(tela));

			loader.setControllerFactory(type -> {

				try {
//            	   tenta buscar no container
					return ApplicationContext.getInstance().getBean(type);
				} catch (Exception e) {
					try {
						return type.getDeclaredConstructor().newInstance();
					} catch (Exception exc) {
						throw new RuntimeException(exc);
					}
				}
			});

			Scene scene = new Scene(loader.load());
			scene.getStylesheets().add(ScreenManager.class.getResource("/css/themes.css").toExternalForm());

			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}