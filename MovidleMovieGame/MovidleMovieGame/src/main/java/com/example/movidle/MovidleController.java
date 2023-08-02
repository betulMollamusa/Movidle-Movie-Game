package com.example.movidle;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovidleController extends VBox {
    private int clickCount = 5; // Sayaç
    Movidle movidle = new Movidle();
    String[] randomFilm = new String[7];
    private VBox vroot = new VBox();

    public MovidleController(){
        tasarim();
    }
    public void tasarim(){
        VBox vroot = new VBox();
        ReadFile readFile = new ReadFile();
        ArrayList<GenerateObject> filmler = readFile.Listing();
        movidle.Randoming(randomFilm);
        TextField textField = new TextField();
        textField.setMinWidth(60);
        textField.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
        Button Guess_button = new Button("GUESS");
        Guess_button.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 13));
        Label Counter_lbl = new Label("Kalan Tahmin Hakkınız : "+Integer.toString(clickCount));
        Counter_lbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16)); // Arial, Bold, 16pt

        // AutoComplete özelliği için filmleri bir liste olarak saklayalım
        List<String> filmNames = filmler.stream().map(GenerateObject::getTitle).collect(Collectors.toList());

        // TextField'a metin girildiğinde AutoComplete gerçekleştirilsin
        textField.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            String GuessText = textField.getText();
            if (!GuessText.isEmpty()) {
                List<String> autoCompleteList = filmNames.stream()
                        .filter(film -> film.toLowerCase().startsWith(GuessText.toLowerCase()))
                        .collect(Collectors.toList());
                if (!autoCompleteList.isEmpty()) {
                    String autoCompleteText = autoCompleteList.get(0);
                    textField.setText(autoCompleteText);
                    textField.selectRange(GuessText.length(), autoCompleteText.length());
                }
            }
        });

        //Enter veya mouse ile tahminin alınması
        Guess_button.setOnAction(e -> handleGuessAction( textField, Counter_lbl, vroot));
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleGuessAction( textField, Counter_lbl, vroot);
            }
        });

        HBox hroot = new HBox(textField,Guess_button);
        hroot.setSpacing(20);
        hroot.setAlignment(Pos.CENTER);

        VBox.setVgrow(vroot, Priority.ALWAYS);
        vroot.setAlignment(Pos.CENTER);
        vroot.setSpacing(10);

        Counter_lbl.setAlignment(Pos.BOTTOM_CENTER);

        getChildren().addAll(hroot, vroot,Counter_lbl);
        setAlignment(Pos.CENTER);

    }//tasarim metodu



    //Buttona tıklandığında kontrollerin sağlanması
    public void handleGuessAction(TextField textField, Label counterLabel, VBox vRoot) {
        String guess = textField.getText();
        HBox guessRow = movidle.setLabels(guess, randomFilm);

        // Tahmin kontrolü
        if (clickCount > 0) {
            clickCount--;
        } else {
            clickCount = 5;
        }

        if (clickCount >= 0) {
            addVBox(vRoot, guessRow);
        } else {
            vRoot.getChildren().removeAll(vRoot.getChildren());
        }

        if ((clickCount == 0) || (movidle.equality(randomFilm[0], guess))) {
            if ((clickCount == 0) && (!movidle.equality(randomFilm[0], guess))) {
                showWinDialog(false);//kaybetti
            } else {
                showWinDialog(true);//kazandı
            }
            clickCount = 5;
            movidle.Randoming(randomFilm);
            vRoot.getChildren().removeAll(vRoot.getChildren());
        }

        counterLabel.setText("Kalan Tahmin Hakkınız: " + clickCount);

        textField.clear();
        System.out.println(randomFilm[0]);
    }




    //kutucukların olduğu satır eklenir
    public void addVBox(VBox vBox,HBox row) {
        vBox.getChildren().add(row);
    }


    //kazanma durumunun gösterilmesi
    public void showWinDialog(boolean Case) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Case){
            alert.setTitle("Congratulations! ");
            alert.setHeaderText("You win ");
        }
        else{
            alert.setTitle("Unfortunately! ");
            alert.setHeaderText("You lost ");
        }

        ButtonType restartButton = new ButtonType("Restart", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(restartButton);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait().ifPresent(response -> {
            if(response == restartButton){
                resetGame();
            }
        });
    }


    //oyun tekrarı
    public void resetGame(){
        clickCount = 5;
        vroot.getChildren().removeAll(vroot.getChildren());
    }
} //Class


