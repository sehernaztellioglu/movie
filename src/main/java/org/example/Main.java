package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<movie> filmler = new LinkedList<>();


    public static void filmDosyaSiralaYil(int year) {
        for (movie film : filmler) {
            if (film.yil == year) {
                System.out.println(film.leadStudio + " - " + film.genre + " - " + film.yil);
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

                film.leadStudio = bilgiler[1];
                film.genre = bilgiler[5];
                film.yil = Integer.parseInt(bilgiler[bilgiler.length - 1].trim());
                film.score = bilgiler[3].trim().isEmpty()
                        ? null
                        : Integer.parseInt(bilgiler[3].trim());
                filmler.add(film);




            }





        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
        }
    }






    public static void main(String[] args) {

        filmDosyasiniOku();

        Scanner scanner = new Scanner(System.in);

        System.out.println(" - filmleri listele(yıla göre)\n choose year");


        int secim = Integer.parseInt(scanner.nextLine());

        if (secim == 1) {
            System.out.println("Hangi yılın filmleri?");

            int year = Integer.parseInt(scanner.nextLine());
            filmDosyaSiralaYil(year);
        }


    }
}