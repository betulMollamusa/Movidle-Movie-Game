package com.example.movidle;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class ReadFile {
    public ReadFile(){
        Listing();
    }

    public static ArrayList<GenerateObject> Listing(){
        String csvFilePath = "/imdb_top_250.csv";
        ArrayList<GenerateObject> movies = new ArrayList<>();

        try (InputStream inputStream = ReadFile.class.getResourceAsStream(csvFilePath);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            reader.readLine();//ilk satırı önceden okuyup listeye eklemeyerek atlaması sağlanır
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");
                // varsayılan CSV format: Title, Year, Genre, Origin, Director, Star, IMDB Link
                String No = columns[0];
                String Title = columns[1];
                String Year = columns[2];
                String Genre = columns[3];
                String Origin = columns[4];
                String Director = columns[5];
                String Star= columns[6];
                String Link = columns[7];

                GenerateObject movie = new GenerateObject(No,Title, Year, Genre, Origin,Director,Star,Link);
                movies.add(movie);
            }
            return movies;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//listing


}

//obje oluştur

