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
    @Override //irrelevant but necessary for abstract class
    public void chooseStats(){
//        int tempStatPt = this.statPoint;
//        int tempHP = this.hpStat;
//        int tempAtk = this.atk;
//        int tempDef = this.def;
//        int tempSpd = this.spd;
//        while(tempStatPt > 0){
//            Logic.clearConsole();
//            Logic.printHeading("Please Distribute stat points. \nRemaining: " + tempStatPt);
//            System.out.println("(1) HP Stat: " + tempHP + " (Each statpoint is equal to 5 HP. Default HP Stat is 10. Thus HP is 50.)");
//            System.out.println("(2) Atk Stat: " + tempAtk);
//            System.out.println("(3) Def Stat: " + tempDef);
//            System.out.println("(4) Spd Stat: " + tempSpd);
//            int input = Logic.readInt("-> ", 4);
//            switch(input){
//                case 1:
//                    tempHP++;
//                    break;
//                case 2:
//                    tempAtk++;
//                    break;
//                case 3:
//                    tempDef++;
//                    break;
//                case 4:
//                    tempSpd++;
//                    break;
//            }
//            tempStatPt -= 1;
//        }
//        Logic.clearConsole();
//        Logic.printHeading("Is the following selection alright?");
//        System.out.println("HP Stat: " + tempHP);
//        System.out.println("Atk Stat: " + tempAtk);
//        System.out.println("Def Stat: " + tempDef);
//        System.out.println("Spd Stat: " + tempSpd);
//        System.out.println();
//        System.out.println("(1) Yes");
//        System.out.println("(2) No, let me retry");
//        int input = Logic.readInt("-> ", 2);
//        if(input == 2){
//            chooseStats();
//        }
//        else{
//            this.hpStat = tempHP;
//            this.maxHP = this.hpStat * 5;
//            this.hp = this.maxHP;
//            this.atk = tempAtk;
//            this.def = tempDef;
//            this.spd = tempSpd;
//            this.statPoint = 0;
//        }
    }
    
    //get the boolean value inBattle. 
    public boolean getInBattle(){
        return this.inBattle;
    }
    
    //sets the boolean value inBattle
    public void setInBattle(boolean inBattle){
        this.inBattle = inBattle;
    }
    
    //checks if the player will level up. if level up, lvl will be increased, player will be given 3 statpoints and choosestats will be called. 
    public boolean levelCheck(){
        if(xpNow>=xpNeeded){
            this.lvl++;
            this.xpNow -= this.xpNeeded;
            this.xpNeeded = (int) Math.pow(lvl/0.3, 1.6);
            Logic.addGUIText("\n\nYou levelled up!");
            Logic.addGUIText("\nLVL " + (this.lvl-1) + " -> " + "LVL " + (this.lvl));
            Logic.addGUIText("\nXP Needed for next Lvl up: " + this.xpNeeded);
            this.statPoint += 3;
            Logic.frame.gView.showStatChooseView();
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
    
    @Override
    public String toString(){
        return this.name + " | LVL: " + this.lvl + " | ACT: " + this.act;
    }
}
