package br.com.project.config;

import br.com.project.controller.LoginController;
import br.com.project.service.LoginService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {

    private static Stage stage;
    private static LoginService loginService;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void setLoginService(LoginService service) {
        loginService = service;
    }

    public static void switchScene(String fxml) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    ScreenManager.class.getResource(fxml)
            );

            loader.setControllerFactory(type -> {

                if (type == LoginController.class) {
                    return new LoginController(loginService);
                }

                try {
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}