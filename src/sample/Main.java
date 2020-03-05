package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tsubulko.entity.Adress;
import tsubulko.entity.Person;
import tsubulko.entity.Student;
import tsubulko.services.Director;
import tsubulko.services.PersonList;

import javax.swing.text.TableView;
import java.util.Date;

public class Main extends Application {

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
