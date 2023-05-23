/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import dialogue.Elf;
import dialogue.Story;
import items.DmgItem;
import items.HealItem;
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
    static Weapon[] starters;
    
    static QuestItem[] clearRewards;
    
    static Player player;
    static boolean isRunning;
    static boolean newPlay = true;
    
    private static int exploration = 0;
    
    public static int place = 0, act = 0;
    public static String[] places;
    
    static HashMap<String, String[]> enemyTypes;
    
    static HashMap<String, double[]> enemyStats = new HashMap<>();
    
    static HashMap<String, String> bossTypes = new HashMap<>();
    
    static HashMap<String, Weapon> enemyWeapon = new HashMap<>();
    
    static SaveManager sMan;
    
    //handles virtually every input in the game. Takes in a string and a numberofuserchoice. 
    //handles all options, all types of inputs, and exceptions. 
    public static int readInt(String prompt, int userChoices){
        int input;
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scan.next());
            }catch(Exception e){
                input = -1;
                System.out.println("Please input an integer!");
            }
        }while(input<1 || input>userChoices);
        return input;
    }
    
    //prints a separator for cool UI
    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-"); //------------------
        }
        System.out.println();
    }
    
    //prints a heading with input String for cool UI
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }
    
    //pauses the game and waits for uesr input. necessary so that the game doesn't skip text.
    public static void gamePauser(){
        System.out.println("\nEnter anything to continue...");
        scan.next();
    }
    
    //simulates the clearing of console. just prints 100 lines of empty text
    public static void clearConsole(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    
    //starts the game. if save exists, asks the user if they want to continue. else newGame()
    public static void gameStart(){
        clearConsole();
        printSeparator(40);
        printHeading("Simple RPG \nA text RPG made by Haruki Uda");
        printSeparator(40);
        gamePauser();
        isRunning = true;
        
        sMan = new SaveManager();
        
        clearRewards = SaveManager.getQuestItems();
        starters = SaveManager.getStarterWeapons();
        places = SaveManager.getPlaces();
        enemyTypes = SaveManager.getEnemyTypes();
        
        // Add enemy types and their ratio values to the HashMap
         // hpratio, atkratio, defratio, spdratio
        enemyStats.put("Goblin", new double[]{0.1667, 0.0833, 0.1667, 0.5833});
        enemyStats.put("Giant Spider", new double[]{0.2308, 0.3077, 0.0769, 0.3846});
        enemyStats.put("Orc", new double[]{0.2941, 0.1765, 0.1176, 0.4118});
        enemyStats.put("Goblin King", new double[]{0.1667, 0.0833, 0.1667, 0.5833});
        //boss goblin king
        
        enemyStats.put("Skeleton", new double[]{0.2667, 0.1333, 0.2, 0.4});
        enemyStats.put("Zombie", new double[]{0.3529, 0.2059, 0.2353, 0.2059});
        enemyStats.put("Dark Knight", new double[]{0.3158, 0.3158, 0.2632, 0.1053});
        //boss dark knight
        
        enemyStats.put("Golem", new double[]{0.3529, 0.0571, 0.5, 0.09});
        enemyStats.put("Troll", new double[]{0.3636, 0.2273, 0.1818, 0.2273});
        enemyStats.put("Minotaur", new double[]{0.3529, 0.2059, 0.2353, 0.2059});
        //boss minotaur
        
        enemyStats.put("Wyvern", new double[]{0.25, 0.25, 0.25, 0.25});
        enemyStats.put("Dragon", new double[]{0.375, 0.25, 0.25, 0.125});
        enemyStats.put("Ancient Dragon", new double[]{0.375, 0.25, 0.25, 0.125});
        //boss ancient dragon
        
        enemyStats.put("Imp", new double[]{0.3, 0.2, 0.1, 0.4});
        enemyStats.put("Demon", new double[]{0.3333, 0.2667, 0.2, 0.1333});
        enemyStats.put("Demon Lord", new double[]{0.3333, 0.2667, 0.2, 0.1333});
        //final boss demon lord
        
        bossTypes.put(places[1], "Goblin King");
        bossTypes.put(places[2], "Dark Knight");
        bossTypes.put(places[3], "Minotaur");
        bossTypes.put(places[4], "Ancient Dragon");
        bossTypes.put(places[5], "Demon Lord");
        
        enemyWeapon.put("Goblin", new Weapon("Goblin Dagger", 2,0,3,200));
        enemyWeapon.put("Giant Spider", new Weapon("Spider Shield", 1,3,1,200));
        enemyWeapon.put("Orc", new Weapon("Orc Club", 7,0,-2,200));
        enemyWeapon.put("Goblin King", new Weapon("Dagger of the Goblin King", 5,0,5,1000));
        //boss goblin king
        
        enemyWeapon.put("Skeleton", new Weapon("Skeleton Bow", 3,0,7,200));
        enemyWeapon.put("Zombie", new Weapon("Zombie Flesh", 0,10,0,200));
        enemyWeapon.put("Dark Knight", new Weapon("Blade of the Fallen Knight", 15,0,0,1000));
        //boss dark knight
        
        enemyWeapon.put("Golem", new Weapon("Golem Arm", 10,5,0,200));
        enemyWeapon.put("Troll", new Weapon("Troll Axe", 17,0,-2,200));
        enemyWeapon.put("Minotaur", new Weapon("Axe of the Minotaur", 25,0,-5,1000));
        //boss minotaur
        
        enemyWeapon.put("Wyvern", new Weapon("Wyvern Wing", 5,5,10,200));
        enemyWeapon.put("Dragon", new Weapon("Dragon Heart", 15,5,0,200));
        enemyWeapon.put("Ancient Dragon", new Weapon("Dragon Armour", 10,5,10,1000));
        //boss ancient dragon
        
        enemyWeapon.put("Imp", new Weapon("Imp Spear", 10,4,11,200));
        enemyWeapon.put("Demon", new Weapon("Demon Spear", 16,4,5,200));
        enemyWeapon.put("Demon Lord", new Weapon("Excalibur", 30,0,5,1000));
        
        
        //file check
        if(SaveManager.saveExist()){
            System.out.println("Would you like to start from the last save or create a new game?");
            System.out.println("(1) Yes, continue game.");
            System.out.println("(2) No, start a new game.");
            int input = readInt("-> ", 2);
            if(input == 1){
                continueGame();
                newPlay = false;
            }
            else{
                newGame();
                newPlay = true;
            }
        }
        else{
            newGame();
            newPlay = true;
        }
    }
    
    //starts a new game
    public static void newGame(){
        boolean nameSet = false;
        String name;
        String gender = "Male";
        do{
            clearConsole();
            printHeading("What's your name?");
            name = scan.next();
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes");
            System.out.println("(2) No, I want to change my name");
            int input = readInt("-> ", 2);
            if(input==1){
                nameSet = true;
                clearConsole();
                printHeading("Please select your gender");
                System.out.println("(1) Male");
                System.out.println("(2) Female");
                int select = readInt("-> ", 2);
                if(select == 1){
                    gender = "Male";
                }
                else{
                    gender = "Female";
                }
            }
        }while(!nameSet);
        
        player = new Player(name, gender);
        
        player.chooseStats();
        chooseStartWeapon();
        
        Story.printStoryIntro();
        
        gameLoop();
    }
    
    //choose a starter weaopn for the player. used in newGame()
    public static void chooseStartWeapon(){
        Logic.clearConsole();
        Logic.printHeading("Please select a starter weapon: ");
        for(int i = 0; i < starters.length; i++){
            System.out.println("(" + (i+1) + ") " + starters[i].name);
        }
        int input = readInt("-> ", starters.length+1);
        starters[input-1].printItem();
        System.out.println("");
        System.out.println("Confirm weapon?");
        System.out.println("(1) Yes");
        System.out.println("(2) No, let me choose a different one");
        int confirm = readInt("-> ", 2);
        if(confirm == 2){
            chooseStartWeapon();
        }
        else{
            player.weapon = starters[input-1];
            System.out.println("You chose the " + player.weapon.name);
        }
    }
    
    //continue game 
    private static void continueGame() {
        SaveManager.loadSave();
        
        gameLoop();
    }
    
    //prints user options menu
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an Action: ");
        printSeparator(20);
        System.out.println("(1) Explore");
        System.out.println("(2) Change Location");
        System.out.println("(3) Character Info");
        System.out.println("(4) Inventory Options");
        System.out.println("(5) Go to Shop");
        System.out.println("(6) Save Game");
        System.out.println("(7) Exit Game");
    }
    
    //main gameloop. prints menu and handles the input. 
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> ", 7);
            switch(input){
                case 1:
                    continueJourney();
                    break;
                case 2:
                    changeLocation();
                    break;
                case 3:
                    checkCharacterInfo();
                    break;
                case 4:
                    inventoryOptions();
                    break;
                case 5:
                    Shop.enterShop(player);
                    break;
                case 6:
                    save();
                    break;
                case 7:
                    exit();
                    break;
            }
        }
    }
    
    //prints player info. current stats 
    private static void checkCharacterInfo() {
        player.printPlayer();
        gamePauser();
    }

    //if place is elven city, the checkAct method is called for elf dialogue and story progression. else explore is called. 
    private static void continueJourney() {
        clearConsole();
        printHeading(places[place]);
        if(place == 0){ //in in elven city
            checkAct();
        }
        else{
            explore();
        }        
    }

    //explores the dungeon. exploration rate is used to calculate the exploration of dungeon
    // random battles occur. and at 100% explorate, the boss battle is called.
    private static void explore() {
        if(exploration < 100){
            exploration += (int)(Math.random()*10) + 1;
            if(exploration >= 100){
                exploration = 100;
            }
            System.out.println("Exploration Rate: " + exploration + "/100");
            gamePauser();
            double encounter = Math.random();
            if(encounter <= 0.2){
                randomBattle();
            }
        }
        else {
            System.out.println("Exploration Rate: " + exploration + "/100");
            gamePauser();
            bossBattle();
        }
    }
    
    //random enemy generated. battle() is called
    private static void randomBattle(){
        int maxLevel = player.lvl+3;
        int minLevel = (player.lvl-3>=1 ? player.lvl-3 : 1);
        clearConsole();
        printHeading("You encountered an Enemy! You will have to fight it... ");
        gamePauser();
        String enemyEncountered = enemyTypes.get(places[place])[(int)(Math.random()*enemyTypes.get(places[place]).length)];
        battle(new Enemy(enemyEncountered, maxLevel, minLevel, false));
    }
    
    //boss enemy generated. battle() is called
    private static void bossBattle(){
        clearConsole();
        printHeading("You have encountered the area boss.");
        gamePauser();
        String enemyEncountered = bossTypes.get(places[place]);
        battle(new Enemy(enemyEncountered, player.lvl+5, player.lvl-3, true));
        player.inventory.addItem(clearRewards[act-1]);
        exploration = 0;
    }
    
    //controls the battle system of the game. 
    private static void battle(Enemy enemy){
        player.setInBattle(true);
        player.currEnemy = enemy;
        player.currEnemy.weapon = enemyWeapon.get(enemy.name);
        while(true){
            clearConsole();
            printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP);
            System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);
            printSeparator(20);
            System.out.println("Choose an Action");
            System.out.println("(1) Fight");
            System.out.println("(2) Use an Item");
            System.out.println("(3) Change Weapon");
            System.out.println("(4) Run");
            int input = readInt("-> ", 4);
            if(input == 1){
                if((player.spd + player.weapon.getSpd()) > (enemy.spd + enemy.weapon.getSpd())){
                    System.out.println("You will attack first!");
                    gamePauser();
                    clearConsole();
                    printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP + " LVL: " + enemy.lvl);
                    System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);  
                    printSeparator(20);
                    int damageDealt = (int)((player.atk + player.weapon.getAtk())/((enemy.def+100+enemy.weapon.getDef())/100));
                    enemy.hp -= damageDealt;
                    System.out.println("You dealt " + damageDealt + " damage to the enemy.");
                    gamePauser();
                    clearConsole();
                    printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP + " LVL: " + enemy.lvl);
                    System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);
                    printSeparator(20);
                    if(enemy.hp <= 0){
                        System.out.println("You defeated the enemy!");
                        int expGain = (int) Math.pow(player.lvl, 1.2) * (enemy.lvl/player.lvl);
                        player.xpNow += expGain;
                        System.out.println("You gained " + expGain + " exp.");
                        int money = enemy.lvl*10;
                        System.out.println("You obtained " + money + " gold.");
                        player.money += money;
                        gamePauser();
                        clearConsole();
                        player.levelCheck();
                        if(enemy.isBoss){
                            System.out.println("You obtained the " + enemy.weapon.name);
                            player.inventory.addItem(enemy.weapon);
                            gamePauser();
                        }
                        else{
                            double drop = Math.random();
                            if(drop<=0.2){
                                System.out.println("The enemy dropped the " + enemy.weapon.name);
                                player.inventory.addItem(enemy.weapon);
                                gamePauser();
                            }
                        }
                        break;
                    }
                    else {
                        System.out.println("The enemy strikes.");
                        int damageTaken = (int)((enemy.atk+enemy.weapon.getAtk())/((player.def+100+player.weapon.getDef())/100));
                        player.hp -= damageTaken;
                        gamePauser();
                        clearConsole();
                        printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP + " LVL: " + enemy.lvl);
                        System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);
                        printSeparator(20);
                        System.out.println("You took " + damageTaken + " damage from the enemy.");
                        gamePauser();
                        clearConsole();
                        if(player.hp <= 0){
                            playerDied();
                            break;
                        }
                    }
                }
                else {
                    System.out.println("The enemy strikes first!");
                    gamePauser();
                    clearConsole();
                    printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP + " LVL: " + enemy.lvl);
                    System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);
                    printSeparator(20);
                    int damageTaken = (int)((enemy.atk+enemy.weapon.getAtk())/((player.def+100+player.weapon.getDef())/100));
                    player.hp -= damageTaken;
                    System.out.println("You took " + damageTaken + " damage from the enemy.");
                    gamePauser();
                    clearConsole();
                    printHeading(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHP + " LVL: " + enemy.lvl);
                    System.out.println(player.name + " HP: " + player.hp + "/" + player.maxHP + " Weapon: " + player.weapon.name);
                    printSeparator(20);
                    if(player.hp <= 0){
                        playerDied();
                        break;
                    }
                    else {
                        System.out.println("Your turn to strike.");
                        int damageDealt = (int)((player.atk + player.weapon.getAtk())/((enemy.def+100+enemy.weapon.getDef())/100));
                        enemy.hp -= damageDealt;
                        System.out.println("You dealt " + damageDealt + " damage to the enemy.");
                        gamePauser();
                        clearConsole();
                        if(enemy.hp <= 0){
                            System.out.println("You defeated the enemy!");
                            int expGain = (int) Math.pow(player.lvl, 1.2) * (enemy.lvl/player.lvl);
                            player.xpNow += expGain;
                            System.out.println("You gained " + expGain + " exp.");
                            int money = enemy.lvl*10;
                            System.out.println("You obtained " + money + " gold.");
                            player.money += money;
                            gamePauser();
                            clearConsole();
                            player.levelCheck();
                            if(enemy.isBoss){
                                System.out.println("You obtained the " + enemy.weapon.name);
                                player.inventory.addItem(enemy.weapon);
                                gamePauser();
                            }
                            else{
                                double drop = Math.random();
                                if(drop<=0.2){
                                    System.out.println("The enemy dropped the " + enemy.weapon.name);
                                    player.inventory.addItem(enemy.weapon);
                                    gamePauser();
                                }
                            }
                            break;
                        }
                    }
                }
            }
            else if(input == 2){
                useItem();
                if(enemy.hp <= 0){
                    System.out.println("You defeated the enemy!");
                    int expGain = (int) Math.pow(player.lvl, 1.2) * (enemy.lvl/player.lvl);
                    player.xpNow += expGain;
                    System.out.println("You gained " + expGain + " exp.");
                    int money = enemy.lvl*10;
                    System.out.println("You obtained " + money + " gold.");
                    player.money += money;
                    gamePauser();
                    clearConsole();
                    player.levelCheck();
                    if(enemy.isBoss){
                        System.out.println("You obtained the " + enemy.weapon);
                        player.inventory.addItem(enemy.weapon);
                        gamePauser();
                    }
                    else{
                        double drop = Math.random();
                        if(drop<=0.2){
                            System.out.println("The enemy dropped the " + enemy.weapon);
                            player.inventory.addItem(enemy.weapon);
                            gamePauser();
                        }
                    }
                    break;
                }
            }
            else if(input == 3){
                player.changeWeapon();
            }else{
                if(!enemy.isBoss){
                    if((player.spd+player.weapon.getSpd()) < (enemy.spd+enemy.weapon.getSpd())){
                        System.out.println("The enemy is faster than you! You can't run away!");
                        gamePauser();
                    }
                    else {
                        double run = Math.random();
                        if(run<=0.2){
                            System.out.println("You successfully ran away!");
                            gamePauser();
                            break;
                        }
                        else {
                            System.out.println("You failed to run away!");
                            gamePauser();
                            clearConsole();
                            System.out.println("The enemy strikes!");
                            gamePauser();
                            clearConsole();
                            int damageTaken = (int)((enemy.atk+enemy.weapon.getAtk())/((player.def+100+player.weapon.getDef())/100));
                            System.out.println("You took " + damageTaken + " damage from the enemy.");
                            gamePauser();
                            clearConsole();
                            if(player.hp <= 0){
                                playerDied();
                                break;
                            }
                        }
                    }
                }
                else {
                    System.out.println("You can't escape from the boss fight!");
                    gamePauser();
                }
            }
        }
        player.setInBattle(false);
        player.currEnemy = null;
    }
    
    //used in battle system and inventory options menu. allows the user to choose an item and use it or not. 
    private static void useItem(){
        clearConsole();
        printHeading("What Item would you like to use?");
        System.out.println("(1) Healing Item");
        System.out.println("(2) Damage Item");
        System.out.println("(3) Go Back");
        int input = readInt("-> ", 3);
        if(input == 1){
            clearConsole();
            printHeading("What healing item would you like to use?");
            ArrayList<HealItem> ownedHeals = player.inventory.getHealItems();
            for (int i = 0; i < ownedHeals.size(); i++) {
                System.out.println("{" + (i+1) + ") " + ownedHeals.get(i).name + " Heals: " + ownedHeals.get(i).heal);
            }
            System.out.println("(" + (ownedHeals.size()+1) + ") Go Back");
            int heal = readInt("-> ", (ownedHeals.size()+1));
            if(heal == (ownedHeals.size()+1)){
                useItem();
            }
            else {
                player.useItem(ownedHeals.get(heal-1));
            }
        }
        else if(input == 2){
            clearConsole();
            printHeading("What damage item would you like to use?");
            ArrayList<DmgItem> ownedDmg = player.inventory.getDmgItems();
            for (int i = 0; i < ownedDmg.size(); i++) {
                System.out.println("{" + (i+1) + ") " + ownedDmg.get(i).name + " Damage: " + ownedDmg.get(i).dmg);
            }
            System.out.println("(" + (ownedDmg.size()+1) + ") Go Back");
            int dmg = readInt("-> ", (ownedDmg.size()+1));
            if(dmg == (ownedDmg.size()+1)){
                useItem();
            }
            else {
                player.useItem(ownedDmg.get(dmg-1));
            }
        }
    }
    
    //updates the current act/elf npc logic. if player has specific questitems, the act changes.
    private static void checkAct() {
        if(player.inventory.containsQuestItem(clearRewards[5])){
            act = 6;
            Story.printActFiveOutro();
            Story.printStoryOutro();
            gamePauser();
            SaveManager.addToHallOfFame(player);
            ArrayList<String> hof = SaveManager.getHallOfFame();
            clearConsole();
            printHeading("Hall Of Fame - Players who cleared the game.");
            for(String pName : hof){
                System.out.println(" - " + pName);
            }
            gamePauser();
            clearConsole();
            System.out.println("That concludes this simple RPG. Thank you for playing!");
            SaveManager.saveAll();
            isRunning = false;
        }
        else if(player.inventory.containsQuestItem(clearRewards[3])){
            act = 5;
            Story.printActFourOutro();
            Elf.printAct5ElfDialogue();
            Story.printActFiveIntro();
        }
        else if(player.inventory.containsQuestItem(clearRewards[2])){
            act = 4;
            Story.printActThreeOutro();
            Elf.printAct4ElfDialogue();
            Story.printActFourIntro();
        }
        else if(player.inventory.containsQuestItem(clearRewards[1])){
            act = 3;
            Story.printActTwoOutro();
            Elf.printAct3ElfDialogue();
            Story.printActThreeIntro();
        }
        else if(player.inventory.containsQuestItem(clearRewards[0])){
            act = 2;
            Story.printActOneOutro();
            Elf.printAct2ElfDialogue();
            Story.printActTwoIntro();
        }
        else {
            act = 1;
            Elf.printAct1ElfDialogue();
            Story.printActOneIntro();
        }
    }

    //handles when player dies. 
    private static void playerDied() {
        clearConsole();
        printHeading("You died...");
        if(SaveManager.saveExist() && !(newPlay)){
            System.out.println("Would you like to revert back to the last save?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int input = readInt("-> ", 2);
            if(input == 1){
                continueGame();
            }
        }
        System.out.println("Thanks for playing my game!");
        isRunning = false;
    }

    //change current location of character. changes the location options depending on current act.
    private static void changeLocation() {
        int count = 1;
        clearConsole();
        printHeading("Where would you like to go?");
        System.out.println("(1) Elven City");
        if(act == 1){
            System.out.println("(2) Goblin Forest");
            count+=act;
        }
        if(act == 2){
            System.out.println("(2) Goblin Forest");
            System.out.println("(3) Ancient Tomb");
            count+=act;
        }
        if(act == 3){
            System.out.println("(2) Goblin Forest");
            System.out.println("(3) Ancient Tomb");
            System.out.println("(4) Minotaur Labyrinth");
            count+=act;
        }
        if(act == 4){
            System.out.println("(2) Goblin Forest");
            System.out.println("(3) Ancient Tomb");
            System.out.println("(4) Minotaur Labyrinth");
            System.out.println("(5) Death Mountain");
            count+=act;
        }
        if(act == 5){
            System.out.println("(2) Goblin Forest");
            System.out.println("(3) Ancient Tomb");
            System.out.println("(4) Minotaur Labyrinth");
            System.out.println("(5) Death Mountain");
            System.out.println("(6) Demon Castle");
            count+=act;
        }
        if(act == 6){
            System.out.println("(2) Goblin Forest");
            System.out.println("(3) Ancient Tomb");
            System.out.println("(4) Minotaur Labyrinth");
            System.out.println("(5) Death Mountain");
            System.out.println("(6) Demon Castle");
            System.out.println("(7) ???");
            count+=act;
        }
        System.out.println("(" + (count+1) + ") Stay at current location.");
        int input = readInt("-> ", count+1);
        if(!(input == (count+1))){
            place = input-1;
            exploration = 0;
        }
    }

    //prints the inventory options.
    private static void inventoryOptions() {
        Logic.clearConsole();
        Logic.printHeading("Inventory");
        System.out.println("What would you like to do? ");
        System.out.println("(1) Change Weapon");
        System.out.println("(2) Use Item");
        System.out.println("(3) Check Inventory");
        System.out.println("(4) Go Back");
        int input = readInt("-> ", 4);
        switch(input){
            case 1:
                player.changeWeapon();
                break;
            case 2:
                useItem();
                break;
            case 3:
                player.inventory.printInventory();
                break;
            case 4:
                break;
        }
    }
    
    //save game
    private static void save(){
        clearConsole();
        printHeading("Save");
        System.out.println("Would you like to save the game?");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int input = readInt("-> ", 2);
        if(input == 1){
            SaveManager.saveAll();
        }
    }
    
    //exit game
    private static void exit() {
        clearConsole();
        printHeading("Exit");
        System.out.println("Would you like to save the game before exiting? ");
        System.out.println("(1) Yes");
        System.out.println("(2) No");
        int input = readInt("-> ", 2);
        if(input == 2){
            System.out.println("Exiting without saving...");
            System.out.println("Thanks for playing my game!");
            isRunning = false;
        }
        else{
            SaveManager.saveAll();
            isRunning = false;
        }
    }
}
