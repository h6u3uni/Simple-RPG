/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import items.Weapon;

/**
 *
 * @author haruk
 */
public class Enemy extends Mob{
    public Weapon weapon;
    public boolean isBoss;
    double[] stats;
   
    public Enemy(String name, int maxLvl, int minLvl, boolean boss) {
        super(name); // call the constructor of the parent class, Mob, passing the name of the enemy

        // set the level of the enemy based on whether it's a boss or not
        if(!boss){
            this.lvl = (int)(Math.random()*(maxLvl-minLvl + 1)) +1;
        }
        else{
            this.lvl = maxLvl;
        }

        this.isBoss = boss;
        this.xpNeeded = 0;
        this.xpNow = 0;

        // set the number of stat points available for the enemy based on its level
        this.statPoint = this.lvl * 3 + 7; //at lvl 1 u have 10 points and per level, u increase by 3 so

        // get the stats for the enemy from a predefined map, based on its name
        this.stats = Logic.enemyStats.get(name);

        // choose the stats of the enemy
        chooseStats();
    }

    @Override
    public void chooseStats() {
        //hp:0, atk:1, def:2, spd:3

        // calculate the number of stat points for hp, atk, def, and spd based on the enemy's stats and available stat points
        this.hpStat = 10 + (int) (this.statPoint * stats[0]);
        this.atk = 1 + (int) (this.statPoint * stats[1]);
        this.def = 1 + (int) (this.statPoint * stats[2]);
        this.spd = 1 + (int) (this.statPoint * stats[3]);

        // calculate the number of remaining stat points after assigning points for hp, atk, def, and spd
        int remainingPoints = this.statPoint - hpStat - atk - def - spd;

        // distribute any remaining stat points to the attributes in the order of hp, atk, def, spd
        for (int i = 0; i < remainingPoints; i++) {
            if (hpStat < atk && hpStat < def && hpStat < spd) {
                hpStat++; // add the remaining points to hp if it has the lowest current value among hp, atk, def, and spd
            } else if (atk < def && atk < spd) {
                atk++; // add the remaining points to atk if it has the lowest current value among atk, def, and spd
            } else if (def < spd) {
                def++; // add the remaining points to def if it has the lowest current value among def and spd
            } else {
                spd++; // add the remaining points to spd if it has the lowest current value among hp, atk, def, and spd
            }
        }

        // calculate the max and current hp of the enemy based on its hp stat
        this.maxHP = this.hpStat * 5;
        this.hp = maxHP;
    }
}

