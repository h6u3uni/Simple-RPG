/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import items.DmgItem;
import items.HealItem;
import items.Weapon;
import items.Item;
import java.util.ArrayList;

/**
 *
 * @author haruk
 */
public class Player extends Mob{
    public int money;
    public Weapon weapon;
    public Inventory inventory;
    private boolean inBattle;
    public Enemy currEnemy;
    public String gender;
    private int id;
    public int act;
    public int place;
    
    public Player(String name, String gender) {
        super(name);
        this.gender = gender;
        this.money = 100;
        this.lvl = 1;
        this.xpNeeded = (int) Math.pow(lvl/0.3, 1.6);
        this.statPoint = 10;
        this.hpStat = 10;
        this.atk = 1;
        this.def = 1;
        this.spd = 1;
        this.inBattle = false;
        this.inventory = new Inventory();
    }
    
    //distribute stat points for the player.
    @Override
    public void chooseStats(){
        int tempStatPt = this.statPoint;
        int tempHP = this.hpStat;
        int tempAtk = this.atk;
        int tempDef = this.def;
        int tempSpd = this.spd;
        while(tempStatPt > 0){
            Logic.clearConsole();
            Logic.printHeading("Please Distribute stat points. \nRemaining: " + tempStatPt);
            System.out.println("(1) HP Stat: " + tempHP + " (Each statpoint is equal to 5 HP. Default HP Stat is 10. Thus HP is 50.)");
            System.out.println("(2) Atk Stat: " + tempAtk);
            System.out.println("(3) Def Stat: " + tempDef);
            System.out.println("(4) Spd Stat: " + tempSpd);
            int input = Logic.readInt("-> ", 4);
            switch(input){
                case 1:
                    tempHP++;
                    break;
                case 2:
                    tempAtk++;
                    break;
                case 3:
                    tempDef++;
                    break;
                case 4:
                    tempSpd++;
                    break;
            }
            tempStatPt -= 1;
        }
        Logic.clearConsole();
        Logic.printHeading("Is the following selection alright?");
        System.out.println("HP Stat: " + tempHP);
        System.out.println("Atk Stat: " + tempAtk);
        System.out.println("Def Stat: " + tempDef);
        System.out.println("Spd Stat: " + tempSpd);
        System.out.println();
        System.out.println("(1) Yes");
        System.out.println("(2) No, let me retry");
        int input = Logic.readInt("-> ", 2);
        if(input == 2){
            chooseStats();
        }
        else{
            this.hpStat = tempHP;
            this.maxHP = this.hpStat * 5;
            this.hp = this.maxHP;
            this.atk = tempAtk;
            this.def = tempDef;
            this.spd = tempSpd;
            this.statPoint = 0;
        }
    }
    
    //change weapon to weapon in inventory
    public void changeWeapon(){
        Logic.clearConsole();
        Logic.printHeading("Change current weapon, " + this.weapon.name + ", to: ");
        ArrayList<Weapon> weapons = inventory.getWeapons();
        if(weapons.isEmpty()){
            System.out.println("You don't have any other weapons!");
            Logic.gamePauser();
        }
        else{
            for(int i = 0; i<weapons.size(); i++){
                System.out.println("(" + (i+1) + ") " + weapons.get(i).name);
            }
            System.out.println("(" + (weapons.size()+1) + ") Cancel");
            int input = Logic.readInt("-> ", (weapons.size()+1));
            if(!(input == (weapons.size()+1))){
                weapons.get(input-1).printItem();
                System.out.println("");
                System.out.println("Confirm weapon?");
                System.out.println("(1) Yes");
                System.out.println("(2) No, let me choose a different one");
                int confirm = Logic.readInt("-> ", 2);
                if(confirm == 2){
                    changeWeapon();
                }
                else{
                    inventory.addItem(this.weapon);
                    this.weapon = weapons.get(input-1);
                    inventory.removeItem(weapons.get(input-1));
                    System.out.println("You changed to the " + this.weapon.name);
                    Logic.gamePauser();
                }
            }
        }
    }
    
    //get the boolean value inBattle. 
    public boolean getInBattle(){
        return this.inBattle;
    }
    
    //sets the boolean value inBattle
    public void setInBattle(boolean inBattle){
        this.inBattle = inBattle;
    }
    
    //use item. (changes effect depending if the player is inbattle or not.)
    public void useItem(Item item){
        if(!inBattle){
            if(item instanceof HealItem){
                Logic.clearConsole();
                Logic.printHeading("Use " + item.getName() + "?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int confirm = Logic.readInt("-> ", 2);
                if(confirm == 2){
                    System.out.println("Didn't use " + item.getName());
                    Logic.gamePauser();
                }
                else{
                    if(this.hp < this.maxHP){
                        inventory.removeItem(item);
                        HealItem heal = (HealItem)item;
                        this.hp += heal.heal;
                        if(this.hp>this.maxHP){
                            this.hp = this.maxHP;
                        }
                    }
                    else {
                        System.out.println("It won't have any effect!");
                        Logic.gamePauser();
                    }
                }
            }
            else if(item instanceof DmgItem){
                Logic.clearConsole();
                Logic.printHeading("Use " + item.getName() + "?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int confirm = Logic.readInt("-> ", 2);
                if(confirm == 2){
                    System.out.println("Didn't use " + item.getName());
                    Logic.gamePauser();
                }
                else{
                    inventory.removeItem(item);
                    DmgItem dmg = (DmgItem) item;
                    int damage = (int)(dmg.dmg/((this.def+100)/100));
                    this.hp -= damage;
                    System.out.println("You damaged yourself!");
                    System.out.println();
                    System.out.println("You took " + damage + " damage.");
                    Logic.gamePauser();
                }
            }
        }
        else{ //inbattle
            if(item instanceof HealItem){
                Logic.clearConsole();
                Logic.printHeading("Use " + item.getName() + "?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int confirm = Logic.readInt("-> ", 2);
                if(confirm == 2){
                    System.out.println("Didn't use " + item.getName());
                    Logic.gamePauser();
                }
                else{
                    if(this.hp < this.maxHP){
                        inventory.removeItem(item);
                        HealItem heal = (HealItem)item;
                        this.hp += heal.heal;
                        if(this.hp>this.maxHP){
                            this.hp = this.maxHP;
                        }
                    }
                    else {
                        System.out.println("It won't have any effect!");
                        Logic.gamePauser();
                    }
                }
            }
            else if(item instanceof DmgItem){
                Logic.clearConsole();
                Logic.printHeading("Use " + item.getName() + "?");
                System.out.println("(1) Yes");
                System.out.println("(2) No");
                int confirm = Logic.readInt("-> ", 2);
                if(confirm == 2){
                    System.out.println("Didn't use " + item.getName());
                    Logic.gamePauser();
                }
                else{
                    inventory.removeItem(item);
                    DmgItem dmg = (DmgItem) item;
                    int damage = (int)(dmg.dmg/((currEnemy.def+100)/100));
                    currEnemy.hp -= damage;
                    System.out.println("You damaged the enemy!");
                    System.out.println();
                    System.out.println("They took " + damage + " damage.");
                    Logic.gamePauser();
                }
            }
        }
    }
    
    //checks if the player will level up. if level up, lvl will be increased, player will be given 3 statpoints and choosestats will be called. 
    public void levelCheck(){
        if(xpNow>=xpNeeded){
            this.lvl++;
            this.xpNow -= this.xpNeeded;
            this.xpNeeded = (int) Math.pow(lvl/0.3, 1.6);
            Logic.clearConsole();
            Logic.printHeading("You levelled up!");
            System.out.println("LVL " + (this.lvl-1) + " -> " + "LVL " + (this.lvl));
            System.out.println("XP Needed for next Lvl up: " + this.xpNeeded);
            this.statPoint = 3;
            Logic.gamePauser();
            chooseStats();
        }
    }
    
    //prints the details of the player.
    public void printPlayer(){
        Logic.clearConsole();
        Logic.printHeading(this.name + " " + this.gender + " Level: " + this.lvl);
        System.out.println("Stats:");
        System.out.println(xpNow + "/" + xpNeeded + "XP");
        System.out.println(hp + "/" + maxHP + "HP");
        System.out.println("Attack: " + atk + "(" + weapon.getAtk() + ")");
        System.out.println("Defense: " + def + "(" + weapon.getDef() + ")");
        System.out.println("Speed: " + spd + "(" + weapon.getSpd() + ")");
        System.out.println("Money: " + money + " Gold");
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.name + " | LVL: " + this.lvl + " | ACT: " + this.act;
    }
}
