/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import java.util.HashMap;
import rpggame.Logic;

/**
 *
 * @author haruk
 */
public class Weapon extends Item{
    private final int atk;
    private final int def;
    private final int spd;
    
    public Weapon(String name, int atk, int def, int spd, int price){
        super(name, false);
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.price = price;
    }
    
    @Override
    public void printItem(){
        Logic.clearConsole();
        Logic.printHeading(name);
        System.out.println("Atk Boost: " + this.atk);
        System.out.println("Def Boost: " + this.def);
        System.out.println("Spd Boost: " + this.spd);
    }
    
    // This static method parses a string in the format "name:atk:def:spd:price"
    // and returns a new Weapon object with the specified values
    public static Weapon parseWeapon(String str) {
        String[] parts = str.split(":");
        String name = parts[0];
        int atk = Integer.parseInt(parts[1]);
        int def = Integer.parseInt(parts[2]);
        int spd = Integer.parseInt(parts[3]);
        int price = Integer.parseInt(parts[4]);
        return new Weapon(name, atk, def, spd, price);
    }
    
    public int getAtk(){
        return this.atk;
    }
    
    public int getDef(){
        return this.def;
    }
    
    public int getSpd(){
        return this.spd;
    }
}
