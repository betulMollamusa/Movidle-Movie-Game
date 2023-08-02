package com.example.movidle;

public class GenerateObject {
    private String No;
    private String title;
    private String year;
    private String genre;
    private String origin;
    private String director;
    private String star;
    private String link;

    public GenerateObject(String No, String title, String year, String genre , String origin, String director, String star, String link) {
        this.No = No;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.origin= origin;
        this.director= director;
        this.star = star;
        this.link=link;

    }

    @Override
    public String toString() {
        return "generateObject{" +
                "Title='" + title + '\'' +
                ", Year='" + year + '\'' +
                ", Genre='" + genre + '\'' +
                ", Origin ='" + origin + '\'' +
                ", Director='" + director + '\'' +
                ", Star ='" + star + '\'' +
                ", IMDB Link= '"+ link + '\'' +
                '}';
    }

    public String getNo(){ return No;}
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getOrigin() {
        return origin;
    }
    public String getDirector() {
        return director;
    }

    public String getStar() {
        return star;
    }

    public String getLink() {
        return link;
    }
}
