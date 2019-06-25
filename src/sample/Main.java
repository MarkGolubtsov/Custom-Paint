package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.config.Config;
import sample.config.ConfigController;
import sample.config.MainConfig;

public class Main extends Application {
    private Config config ;
    @Override
    public void start(Stage primaryStage) throws Exception{   //точка входа
        config = ConfigController.readFromXML();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Laba1");
        primaryStage.setScene(new Scene(root, config.getWidth(), config.getHeight()));

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
