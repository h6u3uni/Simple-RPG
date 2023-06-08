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
public class HealItem implements Item{
    public String name;
    public int heal;
    public int price;
    
    public HealItem(String name, int heal, int price) {
        this.name = name;
        this.heal = heal;
        this.price = price;
    }

    @Override
    public void printItem() {
//        Logic.clearConsole();
//        Logic.printHeading(name);
//        System.out.println("Heal: " + this.heal);
    }
    
    // This static method parses a string in the format "name:heal:price"
    // and returns a new HealItem object with the specified values
    public static HealItem parseHeal(String str) {
        // Split the input string by colon and get the different parts
        String[] parts = str.split(":");
        String name = parts[0]; // The first part is the name of the item
        int heal = Integer.parseInt(parts[1]); // The second part is the amount of HP the item can heal
        int price = Integer.parseInt(parts[2]); // The third part is the price of the item
        // Create a new HealItem object using the extracted values and return it
        return new HealItem(name, heal, price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) obj;

        // Compare the attributes specific to Weapon
        if (!(otherItem instanceof HealItem)) {
            return false;
        }

        HealItem otherWeapon = (HealItem) otherItem;

        return this.name.equals(otherWeapon.getName())
                && this.heal == otherWeapon.heal
                && this.price == otherWeapon.getPrice();
    }
}
