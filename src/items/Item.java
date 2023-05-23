/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author haruk
 */

//abstract class item for abstraction, polymorphism, etc for rpg game items. weapon, healitem, dmgitem, questitem all extend this. 
//public abstract class Item {
//    public final String name;
//    public final boolean usable;
//    public int price;
//    
//    public Item(String name, boolean usable){
//        this.name = name;
//        this.usable = usable;
//    }
//    
//    public abstract void printItem();
//}

public interface Item {
    String getName();
    int getPrice();
    void printItem();
}
