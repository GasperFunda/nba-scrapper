package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Scanner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String url = "https://www.basketball-reference.com/players/";
        System.out.println("Enter full name of the player (First name first then surname): ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        String[] nameArray = playerName.split(" ",-2);
        if(nameArray.length > 2) {
            System.out.println("Only enter one first and one last name.");
            return;
        }
        else if(nameArray.length < 2){
            System.out.println("Please enter first and last name.");
            return;
        }
        url += nameArray[1].toLowerCase().charAt(0) + "/" + nameArray[1].toLowerCase().substring(0,5) + nameArray[0].toLowerCase().substring(0,2) + "01.html";
        try{
            Document doc= Jsoup.connect(url).get();
            Elements threePointers = doc.select("table[id=per_game] > tbody > tr > td[data-stat=fg3_per_g]");
            Elements seasons = doc.select("table[id=per_game] > tbody >tr > th[data-stat=season] > a");
            for (int i = 0; i < threePointers.size(); ++i){
                System.out.println(seasons.get(i).text() + " " + threePointers.get(i).text());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
