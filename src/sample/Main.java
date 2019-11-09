package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main extends Application {
private static SwingNode sNode;
private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        sNode=new SwingNode();
        stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Notepad");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public static SwingNode getSwingNode()
    {
        return sNode;
    }

    public static Stage getStage()
    {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
