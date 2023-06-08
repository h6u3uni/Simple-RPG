/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import dialogue.Dialogue;
import gui.ConfirmView;
import gui.RPGGameGUI;
import items.Weapon;
import items.QuestItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author haruk
 */
public class Logic {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Weapon> starters;
    
    static QuestItem[] clearRewards;
    
    public static Player player;
    static boolean isRunning;
    public static boolean newPlay = true;
    public static boolean pause = false;
    
    private static int exploration = 0;
    
    public static int place = 0, act = 0;
    public static String[] places;
    
    static HashMap<String, String[]> enemyTypes;
    
    static HashMap<String, double[]> enemyStats;
    
    static HashMap<String, String> bossTypes;
    
    static HashMap<String, Weapon> enemyWeapon;
    
    static SaveManager sMan;
    public static RPGGameGUI frame;
    public static Player originPlayer;
    
    //creates a separator for cool UI
    public static String createSeparator(int n){
        String ret = "";
        for(int i = 0; i < n; i++){
            ret+= "-"; //------------------
        }
        return ret;
    }
    
    //creates a heading with input String for cool UI
    public static String createHeading(String title){
        String text = "";
        text += createSeparator(30);
        text += "\n";
        text += title;
        text += "\n";
        text += createSeparator(30);
        return text;
    }
    
    //starts the game. if save exists, asks the user if they want to continue. else newGame()
    public static void gameStart(){
        isRunning = true;
        
        sMan = new SaveManager();
        
        clearRewards = SaveManager.getQuestItems();
        starters = SaveManager.getStarterWeapons();
        places = SaveManager.getPlaces();
        enemyTypes = SaveManager.getEnemyTypes();
        bossTypes = SaveManager.getBossTypes();
        enemyStats = SaveManager.getEnemyStats();
        enemyWeapon = SaveManager.getEnemyWeapon();
        
        frame = new RPGGameGUI();
        // Make the JFrame visible
        frame.setVisible(true);
    }
    
    //starts a new game
    public static void newGame(){
        newPlay = true;
        frame.showNewGameView();
    }
    
    // make new player
    public static void makeNewPlayer(String name, String gender){
        player = new Player(name, gender);
        
//        player.chooseStats();
        player.setId(SaveManager.getNewPlayerID());
        frame.showStatChooseView(player, newPlay);
//        while(pause){}
//        pause = true;
    }
    
    //choose starter weapon
    public static void chooseStartWeapon(){
        frame.showWeaponChooseView(starters, player, newPlay);
    }
    
    //continue game 
    public static void continueGame() {
        ArrayList<Player> saves = SaveManager.getPlayerSaves();
        frame.showContinueGameView(saves);
    }
    
    //choose player to continue game with
    public static void playerSelected(Player player){
        newPlay = false;
        Logic.originPlayer = player.makeCopy();
        Logic.player = player;
        act = player.act;
        place = player.place;
        Logic.player.inventory = SaveManager.getInventory(player);
        String[] loc = {createHeading(places[place])};
        frame.showGameView(loc, false, true);
    }
    
    public static void contFromDeath(Player player){
        newPlay = false;
        Logic.player = player;
        act = player.act;
        place = player.place;
        Logic.player.inventory = SaveManager.getInventory(player);
        String[] loc = {createHeading(places[place])};
        frame.showGameView(loc, false, true);
    }
    
    //get current location of character
    public static String getCurrentLocationText(){
        return Logic.createHeading(places[place]);
    }
    
    // set story or dialogue text in gameview
    public static void setStoryOrDialogue(String[] text, boolean end){
        frame.showGameView(text, end, false);
    }
    
    // set story or dialogue text in gameview
    public static void setGUIText(String text){
        frame.gView.textArea.setText(text);
    }
    
    // add story or dialogue text in gameview
    public static void addGUIText(String text){
        frame.gView.addGUIText(text);
    }
    
    // continue journey
    public static void continueJourney() {
        frame.gView.resetDynamicPanel();
        setGUIText(Logic.createHeading(places[place]));
        if(place == 0){ //in in elven city
            checkAct();
        }
        else{
            explore();
        }        
    }
    
    //updates the current act/elf npc logic. if player has specific questitems, the act changes.
    private static void checkAct() {
        if(player.inventory.containsQuestItem(clearRewards[5])){
            act = 6;
            setStoryOrDialogue(Dialogue.getDialogueSix(), true);
        }
        else if(player.inventory.containsQuestItem(clearRewards[3])){
            act = 5;
            setStoryOrDialogue(Dialogue.getDialogueFive(), false);
        }
        else if(player.inventory.containsQuestItem(clearRewards[2])){
            act = 4;
            setStoryOrDialogue(Dialogue.getDialogueFour(), false);
        }
        else if(player.inventory.containsQuestItem(clearRewards[1])){
            act = 3;
            setStoryOrDialogue(Dialogue.getDialogueThree(), false);
        }
        else if(player.inventory.containsQuestItem(clearRewards[0])){
            act = 2;
            setStoryOrDialogue(Dialogue.getDialogueTwo(), false);
        }
        else {
            act = 1;
            setStoryOrDialogue(Dialogue.getDialogueOne(), false);
        }
    }
    
    //updates the exploration rate of the player. if exploration rate is 100, boss is encountered.
    private static void explore() {
        if(exploration < 100){
            exploration += (int)(Math.random()*10) + 1;
            if(exploration >= 100){
                exploration = 100;
            }
            addGUIText("\nExploration Rate: " + exploration + "/100");
//            System.out.println("Exploration Rate: " + exploration + "/100");
            double encounter = Math.random();
            if(encounter <= 0.2 && place != 6){
                addGUIText("\nYou encountered an Enemy! You will have to fight it...");
                randomBattle();
            }
        }
        else {
            exploration = 100;
            addGUIText("\nExploration Rate: " + exploration + "/100");
            addGUIText("\nYou have encountered the area boss.");
            bossBattle();
        }
    }
    
    //handles when player dies. 
    public static void playerDied() {
        frame.gView.showDeadView();
    }

    //change current location of character. changes the location options depending on current act.
    public static void changeLocation(int place) {
        Logic.place = place;
        setGUIText(Logic.createHeading(places[place]));
        exploration = 0;
    }
    
    //random enemy generated. battle() is called
    private static void randomBattle(){
        int maxLevel = player.lvl+3;
        int minLevel = (player.lvl-3>=1 ? player.lvl-3 : 1);
        String enemyEncountered = enemyTypes.get(places[place])[(int)(Math.random()*enemyTypes.get(places[place]).length)];
        battle(new Enemy(enemyEncountered, maxLevel, minLevel, false));
    }
    
    //boss enemy generated. battle() is called
    private static void bossBattle(){
        String enemyEncountered = bossTypes.get(places[place]);
        exploration = 0;
        battle(new Enemy(enemyEncountered, player.lvl+5, player.lvl-3, true));
    }
    
    //controls the battle system of the game. 
    private static void battle(Enemy enemy){
        player.setInBattle(true);
        player.currEnemy = enemy;
        player.currEnemy.weapon = enemyWeapon.get(enemy.name);
        frame.gView.showBattleView(player);
    }
    
    // compares speed of player and enemy. if player is faster, player strikes first. else enemy strikes first.
    private static boolean isPlayerFaster(){
        return (player.spd + player.weapon.getSpd()) > (player.currEnemy.spd + player.currEnemy.weapon.getSpd());
    }
    
    //returns the header of the enemy in battle
    private static String getEnemyHeader(Enemy e){
        return e.name + " HP: " + e.hp + "/" + e.maxHP + " LVL: " + e.lvl;
    }
    
    //returns the header of the player in battle
    private static String getPlayerHeader(){
        return player.name + " HP: " + player.hp + "/" + player.maxHP + " LVL: " + player.lvl + " Weapon: " + player.weapon.getName();
    }
    
    //returns the damage dealt by the player
    private static int getDamageDealt(){
        return (int)((player.atk + player.weapon.getAtk())/((player.currEnemy.def+100+player.currEnemy.weapon.getDef())/100));
    }
    
    //returns the damage dealt by the enemy
    private static int getDamageTaken(){
        return (int)((player.currEnemy.atk+player.currEnemy.weapon.getAtk())/((player.def+100+player.weapon.getDef())/100));
    }
    
    //handles the player's turn in battle
    public static void fight(){
        Enemy enemy = player.currEnemy;
        if(isPlayerFaster()){
            addGUIText("\n\nYou strike first!");
            addGUIText("\n\n" + createSeparator(20));
            addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
            addGUIText("\nvs\n" + createHeading(getPlayerHeader())); 
            int damageDealt = getDamageDealt();
            enemy.hp -= damageDealt;
            addGUIText("\nYou dealt " + damageDealt + " damage to the enemy.");
            addGUIText("\n\n" + createSeparator(20));
            addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
            addGUIText("\nvs\n" + createHeading(getPlayerHeader())); 
            if(enemy.hp <= 0){
                battleFin(true, enemy);
            }
            else {
                addGUIText("\n\nThe enemy strikes.");
                int damageTaken = getDamageTaken();
                player.hp -= damageTaken;
                addGUIText("\n\n" + createSeparator(20));
                addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
                addGUIText("\nvs\n" + createHeading(getPlayerHeader())); 
                addGUIText("\nYou took " + damageTaken + " damage from the enemy.");
                if(player.hp <= 0){
                    playerDied();
                }
            }
        }
        else {
            addGUIText("\n\nThe enemy strikes first!");
            addGUIText("\n\n" + createSeparator(20));
            addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
            addGUIText("\nvs\n" + createHeading(getPlayerHeader()));
            int damageTaken = getDamageTaken();
            player.hp -= damageTaken;
            addGUIText("\nYou took " + damageTaken + " damage from the enemy.");
            addGUIText("\n\n" + createSeparator(20));
            addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
            addGUIText("\nvs\n" + createHeading(getPlayerHeader()));
            if(player.hp <= 0){
                playerDied();
            }
            else {
                addGUIText("\n\nYour turn to strike.");
                int damageDealt = getDamageDealt();
                enemy.hp -= damageDealt;
                addGUIText("\nYou dealt " + damageDealt + " damage to the enemy.");
                addGUIText("\n\n" + createSeparator(20));
                addGUIText("\n\n" + createHeading(getEnemyHeader(enemy)));
                addGUIText("\nvs\n" + createHeading(getPlayerHeader())); 
                if(enemy.hp <= 0){
                    battleFin(true, enemy);
                }
            }
        }
    }    
    
    //handles the player's turn in battle
    public static void run(){
        Enemy enemy = player.currEnemy;
        if(enemy.isBoss){
            Logic.addGUIText("\nYou cannot run from a boss fight!");
        }
        else{
            if(!isPlayerFaster()){
                Logic.addGUIText("\nThe enemy is faster than you! You can't run away!");
            }
            else{
                double run = Math.random();
                if(run<=0.5){
                    Logic.addGUIText("\nYou successfully ran away!");
                    battleFin(false, enemy);
                }
                else {
                    Logic.addGUIText("\nYou failed to run away!");
                    Logic.addGUIText("\nThe enemy strikes!");
                    int damageTaken = (int)((enemy.atk+enemy.weapon.getAtk())/((player.def+100+player.weapon.getDef())/100));
                    Logic.addGUIText("\nYou took " + damageTaken + " damage from the enemy.");
                    if(player.hp <= 0){
                        Logic.playerDied();
                    }
                }
            }
        }
        ConfirmView cont = new ConfirmView("");
        cont.confirmed("Continue?");
        frame.gView.goNext(cont);
    }
    
    // calculates the exp gain of the player
    private static int getExpGain(Enemy enemy){
        //return (int) ((Math.pow((double)player.lvl, 1.2)) * (player.currEnemy.lvl/player.lvl));
        int out = (int) ((Math.pow((double)player.lvl, 1.2)) * ((double)enemy.lvl/player.lvl));
//        System.out.println((Math.pow((double)player.lvl, 1.2)));
//        System.out.println((double)enemy.lvl/player.lvl);
//        System.out.println(out);
        return out;
    }
    
    //handles the end of battle. if player wins, exp and money is gained. if enemy is boss, boss item is obtained.
    public static void battleFin(boolean win, Enemy enemy){
        if(win){
            addGUIText("\n\nYou defeated the enemy!");
            int expGain = getExpGain(enemy);
            player.xpNow += expGain;
            addGUIText("\nYou gained " + expGain + " exp.");
            int money = enemy.lvl*10;
            addGUIText("\nYou obtained " + money + " gold.");
            player.money += money;
            if(enemy.isBoss){
                addGUIText("\nYou obtained the " + enemy.weapon.name);
                player.inventory.addItem(enemy.weapon);
                if(place != 6 && place !=5){
                    player.inventory.addItem(clearRewards[place-1]);
                }
                if(enemy.name.equals("Demon Lord")){
                    player.inventory.addItem(clearRewards[5]);
                }
            }
            else{
                double drop = Math.random();
                if(drop<=0.4){
                    addGUIText("\nThe enemy dropped the " + enemy.weapon.name);
                    player.inventory.addItem(enemy.weapon);
                }
            }
        }else{//runaway
            setGUIText(createHeading(places[place]));
            Logic.addGUIText("\n\nYou successfully ran away from the fight!");
        }
        if(!(player.levelCheck())){
            frame.gView.resetDynamicPanel();
            frame.gView.enableButtons();
        }
    }
    
    //exit game
    public static void exit(boolean save) {
        if(save){
            SaveManager.saveAll();
            frame.dispose();
        }
        else{
            frame.dispose();
        }
    }
}
