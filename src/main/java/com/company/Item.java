package com.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Setter @Getter
public class Item {
    private String item_name;
    private String item_price;
    private String item_category;
    private String item_link;
    private String last_update;

    Item(String name, String price, String category, String link){
        item_name = name;
        item_price = price;
        item_category = category;
        item_link = link;
    }
}
