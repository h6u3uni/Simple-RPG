/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import items.HealItem;
import items.DmgItem;
import items.Weapon;
import items.Item;
import items.QuestItem;
import java.util.ArrayList;

/**
 *
 * @author haruk
 */
public class Inventory {
    private ArrayList<Item> inventory;
    
    public Inventory(){
        this.inventory = new ArrayList<>();
    }
    
    public void addItem(Item item){
        inventory.add(item);
    }
    
    public void removeItem(Item item){
        inventory.remove(item);
    }
    
    public void printInventory(){
        Logic.clearConsole();
        Logic.printHeading("Inventory");
        for(Item i : inventory){
            System.out.println("- " + i.getName());
        }
        Logic.gamePauser();
    }
    
    //return weapon arraylist by grabbing only the weapons in the inventory
    public ArrayList<Weapon> getWeapons(){
        ArrayList<Weapon> weapons = new ArrayList<>();
        for(Item item : this.inventory){
            if(item instanceof Weapon){
                weapons.add((Weapon)item);
            }
        }
        return weapons;
    }
    
    //return dmgitem arraylist by grabbing only the dmgitems in the inventory
    public ArrayList<DmgItem> getDmgItems(){
        ArrayList<DmgItem> dmgItems = new ArrayList<>();
        for(Item item : this.inventory){
            if(item instanceof DmgItem){
                dmgItems.add((DmgItem)item);
            }
        }
        return dmgItems;
    }
    
    //return healitem arraylist by grabbing only the healitem in the inventory
    public ArrayList<HealItem> getHealItems(){
        ArrayList<HealItem> healItems = new ArrayList<>();
        for(Item item : this.inventory){
            if(item instanceof HealItem){
                healItems.add((HealItem)item);
            }
        }
        return healItems;
    }
    
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    
    //return questitem arraylist by grabbing only the questitem in the inventory
    public ArrayList<QuestItem> getQuestItems(){
        ArrayList<QuestItem> QuestItems = new ArrayList<>();
        for(Item item : this.inventory){
            if(item instanceof QuestItem){
                QuestItems.add((QuestItem)item);
            }
        }
        return QuestItems;
    }
    
    //check if inventory contains the questitem. uses compare method in questitem class
    public boolean containsQuestItem(QuestItem item){
        ArrayList<QuestItem> QuestItems = getQuestItems();
        for(QuestItem q : QuestItems){
            if(item.compareTo(q) == 0){
                return true;
            }
        }
        return false;
    }    

    public String inventoryToString() {
        String out = "";
        
        for(Item i : inventory){
            out += "- " + i.getName() + "\n";
        }
        
        return out;
    }
}
