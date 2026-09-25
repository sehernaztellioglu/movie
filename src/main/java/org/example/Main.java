package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<movie> filmler = new LinkedList<>();
    static HashSet<String> studiolar = new HashSet<>();



    public static void filmDosyaSiralaYil(int year) {
        for (movie film : filmler) {
            if (film.yil == year) {
                System.out.println(film.leadStudio + " - " + film.genre + " - " + film.yil);
            }
        }
    }

    public static void filmDosyaSiralaStudio(String studio){
        for (movie film : filmler) {
            if (film.leadStudio.trim().equalsIgnoreCase(studio.trim())){
                System.out.println(film.isim + " - " + film.genre + " - " + film.yil);
            }
        }
    }


    public static void filmDosyasiniOku() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("HollywoodMovies.csv"))) {

            String satir;

            reader.readLine();

            while ((satir = reader.readLine()) != null) {

                String[] bilgiler = satir.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                movie film = new movie();

                film.isim = bilgiler[0];


                film.leadStudio = bilgiler[1];
                film.genre = bilgiler[5];
                film.yil = Integer.parseInt(bilgiler[bilgiler.length - 1].trim());
                film.score = bilgiler[3].trim().isEmpty()
                        ? null
                        : Integer.parseInt(bilgiler[3].trim());
                filmler.add(film);
                studiolar.add(film.leadStudio);








            }





        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
        }
    }






    public static void main(String[] args) {

        filmDosyasiniOku();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Yıla göre filmleri listele");
        System.out.println("2 - Stüdyoya göre filmleri listele");

        int secim = Integer.parseInt(scanner.nextLine());

        if (secim == 1) {
            System.out.println("Hangi yılın filmleri?");

            int year = Integer.parseInt(scanner.nextLine());
            filmDosyaSiralaYil(year);
        }else if (secim == 2) {
            for (String studio : studiolar) {
                System.out.println(studio);
            }

            System.out.println("Hangi stüdyonun filmleri?");
            String studio = scanner.nextLine();
            filmDosyaSiralaStudio(studio);
        }


    }
}