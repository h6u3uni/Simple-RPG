/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import rpggame.Logic;

/**
 *
 * @author haruk
 */
public class DmgItem extends Item{
    public int dmg; //equal to atk stat used in mob (same damage calculations)
    
    public DmgItem(String name, int dmg, int price) {
        super(name, true);
        this.dmg = dmg;
        this.price = price;
    }

    @Override
    // This method prints out the details of an item
    public void printItem() {
        Logic.clearConsole();
        Logic.printHeading(name);
        System.out.println("Damage: " + this.dmg);
    }
    
    
    // This static method parses a string in the format "name:dmg:price"
    // and returns a new DmgItem object with the specified values
    public static DmgItem parseDmg(String str) {
        // Split the input string into an array of strings using the colon (:) character as the separator
        String[] parts = str.split(":");
        // Extract the name, damage, and price values from the split string parts
        String name = parts[0];
        int dmg = Integer.parseInt(parts[1]);
        int price = Integer.parseInt(parts[2]);
        // Create and return a new DmgItem object with the parsed values
        return new DmgItem(name, dmg, price);
    }
}
