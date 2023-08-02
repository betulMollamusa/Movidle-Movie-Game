package com.example.movidle;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Movidle extends HBox {
    public Movidle(){
    }

    public HBox setLabels(String Guess, String[] RFilm) {
        ReadFile readFile = new ReadFile();

        ImageView year=new ImageView();
        boolean control=false;
        Label title_lbl = createLabel();
        Label year_lbl = createLabel();
        Label genre_lbl = createLabel();
        Label origin_lbl = createLabel();
        Label director_lbl = createLabel();
        Label star_lbl = createLabel();
        //Label link_lbl = createLabel();    linkler csv

        ArrayList<GenerateObject> movies = readFile.Listing();//Read sınıfdan obje türünde filmlerin arrayListi çekilir
        int counter = 0;//filmin numarasını verecektir

        //girilen tahmine ait filmin sırası aranır ve elemanların çekilmesi için satır değeri taranır
        for (GenerateObject movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(Guess)) {
                control=true;
                break;
            }
            else
                counter++;
        }

        //eğer girilen film tahmini listede varsa labeller doldurulup rengi ayarlanır
        if (control) {
            setTextAndStyle(title_lbl, movies.get(counter).getTitle(), RFilm[0]);
            setTextAndStyle(year_lbl, movies.get(counter).getYear(), RFilm[1]);
            setTextAndStyle(genre_lbl, movies.get(counter).getGenre(), RFilm[2]);
            setTextAndStyle(origin_lbl, movies.get(counter).getOrigin(), RFilm[3]);
            setTextAndStyle(director_lbl, movies.get(counter).getDirector(), RFilm[4]);
            setTextAndStyle(star_lbl, movies.get(counter).getStar(), RFilm[5]);

            FadeTransition titleFade = createFadeTransition(title_lbl);
            FadeTransition yearFade = createFadeTransition(year_lbl);
            FadeTransition genreFade = createFadeTransition(genre_lbl);
            FadeTransition originFade = createFadeTransition(origin_lbl);
            FadeTransition directorFade = createFadeTransition(director_lbl);
            FadeTransition starFade = createFadeTransition(star_lbl);

            titleFade.play();
            yearFade.play();
            genreFade.play();
            originFade.play();
            directorFade.play();
            starFade.play();

            int randomYear = Integer.parseInt(RFilm[1]);
            int movieYear = Integer.parseInt(movies.get(counter).getYear());
            year = YearCompare(movieYear,randomYear);

        }
        else {
            falseControl();
        }

        HBox hbox = new HBox(title_lbl,year_lbl,genre_lbl,origin_lbl,director_lbl,star_lbl,year);
        hbox.setSpacing(7);
        HBox.setHgrow(hbox, Priority.ALWAYS);
        hbox.setAlignment(Pos.CENTER);
        getChildren().add(hbox);
        return hbox;
    }


    //  Yanlış metin tahmininde uyarı verilir.
    private void falseControl(){
        Alert warningAlert = new Alert(Alert.AlertType.WARNING);
        warningAlert.setTitle("");
        warningAlert.setHeaderText("invalid guess entry");
        warningAlert.showAndWait();

    }


    //labelleri oluşturur
    private Label createLabel() {
        Label label = new Label();
        label.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
        label.setMinSize(85, 85);
        label.setMaxSize(85, 85);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        return label;
    }


    //labellerin renginin özelleştirilmesi sağlannır
    private void setTextAndStyle(Label label, String movieData, String guessData) {
        label.setText(movieData);
        if (equality(guessData, movieData))
            label.setStyle("-fx-background-color: green;");
        else
            label.setStyle("-fx-background-color: red;");
    }



    //bu metotta fadetransition ile animasyon geçişin özellikleri sağlanır ve labellere atanır
    private FadeTransition createFadeTransition(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        return fadeTransition;
    }



    //bu metotta Random yapılarak bir film seçilir ve satır sayısına bağlı olarak o filme ait veriler diziye aktarılıp döndürülür
    public void Randoming(String[] randomFilm){
        //random işlemi kodlanmalı

        Random random = new Random();
        int randomNo =random.nextInt(250) + 1;

        ReadFile tara = new ReadFile();
        List<GenerateObject> films = tara.Listing();

        randomFilm[0] = films.get(randomNo).getTitle();
        randomFilm[1] = films.get(randomNo).getYear();
        randomFilm[2] = films.get(randomNo).getGenre();
        randomFilm[3] = films.get(randomNo).getOrigin();
        randomFilm[4] = films.get(randomNo).getDirector();
        randomFilm[5] = films.get(randomNo).getStar();
        randomFilm[6] = films.get(randomNo).getLink();

    }



    //bu metotta girilen film tahmini ile random seçilip aranan film arasında eşleşme durumu kontrol edilir ve bool değer döndürülür
    public boolean equality(String RandomF, String guess){
        if(guess.equalsIgnoreCase(RandomF))
            return true;
        else
            return false;
    }



    //yıllar için yukarı-aşağı butonları eklenir
    private ImageView YearCompare(int GuessY,int RandomY){
        ImageView yearIcon = new ImageView();
        if (GuessY < RandomY) {
            yearIcon.setImage(new Image(getClass().getResource("/up_icon.png").toExternalForm())); // Replace with your down arrow icon path
        } else if (GuessY > RandomY) {
            yearIcon.setImage(new Image(getClass().getResource("/down_icon.png").toExternalForm()));// Replace with your up arrow icon path
        }
        yearIcon.setFitHeight(15);
        yearIcon.setPreserveRatio(true);
        return yearIcon;
    }

}
