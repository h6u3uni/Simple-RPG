/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import items.DmgItem;
import items.HealItem;
import items.Weapon;
import java.util.ArrayList;

public class Shop {
    static Player player;
    static SaveManager sMan;
    static HealItem[] healItems;
    
    static DmgItem[] dmgItems;
    
    static Weapon[] shopWeapons;
    
    //main method to call in logic class to entershop. interface to interact with shop. 
    public static void enterShop(Player player){
        sMan = Logic.sMan;
        healItems = sMan.getShopHeals();
        dmgItems = sMan.getShopDmgs();
        shopWeapons = sMan.getShopWeapons();
        Shop.player = player;
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What would you like to do?");
        System.out.println("(1) Buy");
        System.out.println("(2) Sell");
        System.out.println("(3) Leave");
        int input = Logic.readInt("-> ", 3);
        switch(input){
            case 1:
                buy();
                break;
            case 2:
                sell();
                break;
            case 3:
                break;
        }
    }
    
    //prints buy options. buyheal, buydamage, buyweapon can be called.
    private static void buy(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What would you like buy today?");
        System.out.println("(1) Weapons");
        System.out.println("(2) Healing Items");
        System.out.println("(3) Damaging Items");
        System.out.println("(4) Go back");
        int input = Logic.readInt("-> ", 4);
        switch (input) {
            case 1:
                buyWeapon();
                break;
            case 2:
                buyHeal();
                break;
            case 3:
                buyDamage();
                break;
            case 4:
                enterShop(Shop.player);
                break;
        }
    }
    
    //buy heal items
    private static void buyHeal(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("Here are the Healing Items we have:");
        for(int i = 0; i<healItems.length; i++){
            System.out.println("(" + (i+1) + ") " + healItems[i].name + " - Heals: " + healItems[i].heal + " - Price: " + healItems[i].price + " Gold");
        }
        System.out.println("(" + (healItems.length+1) + ") Go Back");
        int buy = Logic.readInt("-> ", (healItems.length+1));
        if(buy == (healItems.length+1)){
            buy();
        }
        else if(Shop.player.money >= healItems[buy-1].price){
            System.out.println("Buy " + healItems[buy-1].name + "?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ",2);
            if(confirm == 2){
                buyHeal();
            }
            else{
                player.inventory.addItem(healItems[buy-1]);
                player.money -= healItems[buy-1].price;
                System.out.println("You purchased 1 " + healItems[buy-1].name);
                Logic.gamePauser();
                buyHeal();
            }
        }
        else {
            System.out.println("You don't have enough money to buy that!");
            Logic.gamePauser();
            buyHeal();
        }
    }
    
    //buy damage items
    private static void buyDamage(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("Here are the Damage Items we have:");
        for(int i = 0; i<dmgItems.length; i++){
            System.out.println("(" + (i+1) + ") " + dmgItems[i].name + " - Damage: " + dmgItems[i].dmg + " - Price: " + dmgItems[i].price + " Gold");
        }
        System.out.println("(" + (dmgItems.length+1) + ") Go Back");
        int buy = Logic.readInt("-> ", (dmgItems.length+1));
        if(buy == (dmgItems.length+1)){
            buy();
        }
        else if(Shop.player.money >= dmgItems[buy-1].price){
            System.out.println("Buy " + dmgItems[buy-1].name + "?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ",2);
            if(confirm == 2){
                buyDamage();
            }
            else{
                player.inventory.addItem(dmgItems[buy-1]);
                player.money -= dmgItems[buy-1].price;
                System.out.println("You purchased 1 " + dmgItems[buy-1].name);
                Logic.gamePauser();
                buyDamage();
            }
        }
        else {
            System.out.println("You don't have enough money to buy that!");
            Logic.gamePauser();
            buyDamage();
        }
    }
    
    //buy weapons
    private static void buyWeapon(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("Here are the Weapons we have:");
        for(int i = 0; i<shopWeapons.length; i++){
            System.out.println("(" + (i+1) + ") " + shopWeapons[i].name + " - Price: " + shopWeapons[i].price + " Gold");
        }
        System.out.println("(" + (shopWeapons.length+1) + ") Go Back");
        int buy = Logic.readInt("-> ", (shopWeapons.length+1));
        if(buy == (shopWeapons.length+1)){
            buy();
        }
        else if(Shop.player.money >= shopWeapons[buy-1].price){
            System.out.println("Buy " + shopWeapons[buy-1].name + "?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ",2);
            if(confirm == 2){
                buyWeapon();
            }
            else{
                player.inventory.addItem(shopWeapons[buy-1]);
                player.money -= shopWeapons[buy-1].price;
                System.out.println("You purchased 1 " + shopWeapons[buy-1].name);
                Logic.gamePauser();
                buyWeapon();
            }
        }
        else {
            System.out.println("You don't have enough money to buy that!");
            Logic.gamePauser();
            buyWeapon();
        }
    }
    
    //prints selling options. sellweapon, sellheal, selldmg can be called.
    private static void sell(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What would you like to sell?");
        System.out.println("(1) Weapons");
        System.out.println("(2) Healing Items");
        System.out.println("(3) Damage Items");
        System.out.println("(4) Go Back");
        int input = Logic.readInt("-> ", 4);
        switch(input){
            case 1:
                sellWeapon();
                break;
            case 2:
                sellHeal();
                break;
            case 3:
                sellDmg();
                break;
            case 4:
                enterShop(Shop.player);
                break;
        }
    }
    
    //sell weapons in inventory
    private static void sellWeapon(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What Weapon would you like to sell?");
        ArrayList<Weapon> weapons = player.inventory.getWeapons();
        for(int i = 0; i<weapons.size(); i++){
            System.out.println("(" + (i+1) + ") " + weapons.get(i).name + " - Sell Price: " + (weapons.get(i).price/2));
        }
        System.out.println("(" + (weapons.size()+1) + ") Go Back");
        int input = Logic.readInt("-> ", (weapons.size()+1));
        if(input == (weapons.size()+1)){
            sell();
        }
        else{
            System.out.println("Sell " + weapons.get(input-1).name + " for " + (weapons.get(input-1).price/2) + " gold?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ", 2);
            if(confirm == 2){
                sellWeapon();
            }
            else{
                System.out.println("Sold " + weapons.get(input-1).name + " for " + (weapons.get(input).price/2) + " gold");
                player.inventory.removeItem(weapons.get(input-1));
                player.money += (weapons.get(input-1).price/2);
                Logic.gamePauser();
                sellWeapon();
            }
        }
    }
    
    //sell healing items in inventory
    private static void sellHeal(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What Healing Item would you like to sell?");
        ArrayList<HealItem> hItems = player.inventory.getHealItems();
        for(int i = 0; i<hItems.size(); i++){
            System.out.println("(" + (i+1) + ") " + hItems.get(i).name + " - Sell Price: " + (hItems.get(i).price/2));
        }
        System.out.println("(" + (hItems.size()+1) + ") Go Back");
        int input = Logic.readInt("-> ", (hItems.size()+1));
        if(input == (hItems.size()+1)){
            sell();
        }
        else{
            System.out.println("Sell " + hItems.get(input-1).name + " for " + (hItems.get(input-1).price/2) + " gold?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ", 2);
            if(confirm == 2){
                sellHeal();
            }
            else{
                System.out.println("Sold " + hItems.get(input-1).name + " for " + (hItems.get(input-1).price/2) + " gold");
                player.inventory.removeItem(hItems.get(input-1));
                player.money += (hItems.get(input-1).price/2);
                Logic.gamePauser();
                sellHeal();
            }
        }
    }
    
    //sell damage items in inventory. 
    private static void sellDmg(){
        Logic.clearConsole();
        Logic.printHeading("Welcome to the Shop");
        System.out.println("What Damage Item would you like to sell?");
        ArrayList<DmgItem> dItems = player.inventory.getDmgItems();
        for(int i = 0; i<dItems.size(); i++){
            System.out.println("(" + (i+1) + ") " + dItems.get(i).name + " - Sell Price: " + (dItems.get(i).price/2));
        }
        System.out.println("(" + (dItems.size()+1) + ") Go Back");
        int input = Logic.readInt("-> ", (dItems.size()+1));
        if(input == (dItems.size()+1)){
            sell();
        }
        else{
            System.out.println("Sell " + dItems.get(input-1).name + " for " + (dItems.get(input-1).price/2) + " gold?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, go back");
            int confirm = Logic.readInt("-> ", 2);
            if(confirm == 2){
                sellDmg();
            }
            else{
                System.out.println("Sold " + dItems.get(input-1).name + " for " + (dItems.get(input-1).price/2) + " gold");
                player.inventory.removeItem(dItems.get(input-1));
                player.money += (dItems.get(input-1).price/2);
                Logic.gamePauser();
                sellDmg();
            }
        }
    }
}
