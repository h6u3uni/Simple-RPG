/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

/**
 *
 * @author haruk
 */

//abstract class mob. Enemy and Player class extend this. 
public abstract class Mob {
    public String name;
    public int hpStat, maxHP, hp, xpNow, xpNeeded, lvl, atk, def, spd, statPoint;
    
    public Mob(String name){
        this.name = name;
        this.xpNow = 0;
    }
    
    public abstract void chooseStats();
}
