package br.com.project.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxml) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    ScreenManager.class.getResource(fxml)
            );

            loader.setControllerFactory(type -> {

               try {
//            	   tenta buscar no container
            	   return ApplicationContext
            			   .getInstance()
            			   .getBean(type);
               } catch (Exception e) {
            	   try {
            		   return type.getDeclaredConstructor().newInstance();
            	   } catch (Exception exc) {
            		   throw new RuntimeException(exc);
            	   }            	   
               }
            });

            stage.setScene(new Scene(loader.load()));
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}