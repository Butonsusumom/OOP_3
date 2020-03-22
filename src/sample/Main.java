package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.text.TableView;
import java.io.File;
import java.util.Date;

public class Main extends Application {
    public Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Firm");
        primaryStage.setScene(new Scene(root, 1208, 774));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }


}
