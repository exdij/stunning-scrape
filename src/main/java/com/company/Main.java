package com.company;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        Scraper scraper = new Scraper();

        String md5;
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        items = db.getData("antolini");

        for (ArrayList<String> child:items){
            System.out.println(child);
        }
        db.writeData(items,"antolini");
    }


}
