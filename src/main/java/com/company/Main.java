package com.company;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Database db = new Database();
        //Scraper scraper = new Scraper();

        String md5;
        List<AntoliniEntity> items = new ArrayList<>();
        items = db.getData("antolini");

        for (AntoliniEntity child:items){
            System.out.println(child);
        }
       // db.writeData(items,"antolini");
    }


}
