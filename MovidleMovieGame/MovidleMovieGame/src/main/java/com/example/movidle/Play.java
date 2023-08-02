package com.example.movidle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Play extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Text txt = new Text("MOVIDLE");
            txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16)); // Arial, Bold, 16pt
            MovidleController movidleController = new MovidleController();
            BorderPane BPane = new BorderPane();
            BPane.setCenter(movidleController);
            BPane.setTop(txt);
            Scene scene = new Scene(BPane, 1000, 600);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

