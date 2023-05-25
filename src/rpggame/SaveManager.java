/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame;

import items.DmgItem;
import items.HealItem;
import items.Weapon;
import items.QuestItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haruk
 */
public class SaveManager {    
    private static final String USER_NAME = "pdc"; //your DB username
    private static final String PASSWORD = "rpg"; //your DB password
    private static final String URL = "jdbc:derby:databases/gameDB; create=true";  //url of the DB host

    static Connection conn;
    static Statement statement;
    
    public SaveManager() {
        establishConnection();
    }

//    public static void main(String[] args) {
//        SaveManager sManager = new SaveManager();
//        System.out.println(sManager.getConnection());
//        try {
//            ResultSet rs = sManager.statement.executeQuery("SELECT * FROM HEALITEM");
//            while(rs.next()){
//                String name = rs.getString("ITEMNAME");
//                int heal = rs.getInt("HEAL");
//                int price = rs.getInt("PRICE");
//                String item = name + ":" + Integer.toString(heal) + ":" + Integer.toString(price);
//                System.out.println(item);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                //System.out.println(URL + " Get Connected Successfully ....");
                statement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static HealItem[] getShopHeals(){
        ArrayList<HealItem> arrHeal = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM HEALITEM");
            while(rs.next()){
                String name = rs.getString("ITEMNAME");
                int heal = rs.getInt("HEAL");
                int price = rs.getInt("PRICE");
                String item = name + ":" + Integer.toString(heal) + ":" + Integer.toString(price);
                arrHeal.add(HealItem.parseHeal(item));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HealItem[] ret = new HealItem[arrHeal.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrHeal.get(i);
        }
        
        return ret;
    }
    
    public static DmgItem[] getShopDmgs(){
        ArrayList<DmgItem> arrDmg = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM DMGITEM");
            while(rs.next()){
                String name = rs.getString("ITEMNAME");
                int dmg = rs.getInt("DMG");
                int price = rs.getInt("PRICE");
                String item = name + ":" + Integer.toString(dmg) + ":" + Integer.toString(price);
                arrDmg.add(DmgItem.parseDmg(item));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DmgItem[] ret = new DmgItem[arrDmg.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrDmg.get(i);
        }
        
        return ret;
    }
    
    public static Weapon[] getShopWeapons(){
        ArrayList<Weapon> arrWeapon = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM WEAPON WHERE TYPE = 'SHOP'");
            while(rs.next()){
                String name = rs.getString("WEAPONNAME");
                int atk = rs.getInt("ATK");
                int def = rs.getInt("DEF");
                int spd = rs.getInt("SPD");
                int price = rs.getInt("PRICE");
                String item = name + ":" + Integer.toString(atk) + ":" + Integer.toString(def) + ":" + Integer.toString(spd) + ":" + Integer.toString(price);
                arrWeapon.add(Weapon.parseWeapon(item));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Weapon[] ret = new Weapon[arrWeapon.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrWeapon.get(i);
        }
        
        return ret;
    }
    
    public static Weapon[] getStarterWeapons(){
        ArrayList<Weapon> arrWeapon = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM WEAPON WHERE TYPE = 'START'");
            while(rs.next()){
                String name = rs.getString("WEAPONNAME");
                int atk = rs.getInt("ATK");
                int def = rs.getInt("DEF");
                int spd = rs.getInt("SPD");
                int price = rs.getInt("PRICE");
                String item = name + ":" + Integer.toString(atk) + ":" + Integer.toString(def) + ":" + Integer.toString(spd) + ":" + Integer.toString(price);
                arrWeapon.add(Weapon.parseWeapon(item));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Weapon[] ret = new Weapon[arrWeapon.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrWeapon.get(i);
        }
        
        return ret;
    }
    
    public static QuestItem[] getQuestItems(){
        ArrayList<QuestItem> arrQuest = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM QUESTITEM");
            while(rs.next()){
                String name = rs.getString("ITEMNAME");
                String description = rs.getString("DESCRIPTION");
                String item = name + ":" + description;
                arrQuest.add(QuestItem.parseQuest(item));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        QuestItem[] ret = new QuestItem[arrQuest.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrQuest.get(i);
        }
        
        return ret;
    }
    
    public static String[] getPlaces(){
        ArrayList<String> arrPlaces = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM PLACE");
            while(rs.next()){
                String name = rs.getString("PLACENAME");
                arrPlaces.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] ret = new String[arrPlaces.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrPlaces.get(i);
        }
        
        return ret;
    }
    
    public static HashMap<String, String[]> getEnemyTypes(){
        String[] places = getPlaces();
        HashMap<String, String[]> ret = new HashMap<>();
        
        for(String place : places){
            String[] enemies = enemyTypeHelper(place);
            ret.put(place, enemies);
        }
        
        return ret;
    }
    
    private static String[] enemyTypeHelper(String place){
        ArrayList<String> arrEnemies = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT ENEMY.ENEMYNAME FROM ENEMY, PLACE WHERE ENEMY.LOCATIONID = PLACE.PLACEID AND PLACE.PLACENAME = '" + place + "' AND ENEMY.BOSS = FALSE");
            while(rs.next()){
                String name = rs.getString("ENEMYNAME");
                arrEnemies.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] ret = new String[arrEnemies.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrEnemies.get(i);
        }
        
        return ret;
    }
    
    public static HashMap<String, double[]> getEnemyStats(){
        HashMap<String, double[]> ret = new HashMap<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT ENEMYNAME, HPSTAT, ATKSTAT, DEFSTAT, SPDSTAT FROM ENEMY");
            while(rs.next()){
                double[] stats = new double[4];
                String name = rs.getString("ENEMYNAME");
                stats[0] = rs.getDouble("HPSTAT");
                stats[1] = rs.getDouble("ATKSTAT");
                stats[2] = rs.getDouble("DEFSTAT");
                stats[3] = rs.getDouble("SPDSTAT");
                ret.put(name, stats);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
    
    public static HashMap<String, Weapon> getEnemyWeapon(){
        HashMap<String, Weapon> ret = new HashMap<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT ENEMY.ENEMYNAME, WEAPON.WEAPONNAME, WEAPON.ATK, WEAPON.DEF, WEAPON.SPD, WEAPON.PRICE FROM ENEMY, WEAPON WHERE ENEMY.WEAPONID = WEAPON.ITEMID");
            while(rs.next()){
                String name = rs.getString("ENEMYNAME");
                String weapName = rs.getString("WEAPONNAME");
                int weapAtk = rs.getInt("ATK");
                int weapDef = rs.getInt("DEF");
                int weapSpd = rs.getInt("SPD");
                int weapPrice = rs.getInt("PRICE");
                Weapon weap = new Weapon(weapName, weapAtk, weapDef, weapSpd, weapPrice);
                ret.put(name, weap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
    
    public static HashMap<String, String> getBossTypes(){
        String[] places = getPlaces();
        HashMap<String, String> ret = new HashMap<>();
        
        for(String place : places){
            try {
                ResultSet rs = statement.executeQuery("SELECT ENEMY.ENEMYNAME FROM ENEMY, PLACE WHERE ENEMY.LOCATIONID = PLACE.PLACEID AND PLACE.PLACENAME = '" + place + "' AND ENEMY.BOSS = TRUE");
                while(rs.next()){
                    String name = rs.getString("ENEMYNAME");
                    ret.put(place, name);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ret;
    }
    
    public static ArrayList<String> getHallOfFame(){
        ArrayList<String> hof = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM HALLOFFAME");
            while(rs.next()){
                String name = rs.getString("PLAYERNAME");
                int lvl = rs.getInt("LVL");
                Date dateOfComplete = rs.getDate("DATEOFCOMPLETION");
                String entry = "Player Name: " + name + " | Player LVL: " + Integer.toString(lvl) + " | Date of Completion: " + dateOfComplete;
                hof.add(entry);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hof;
    }
    
    //method to add player to the hall of fame
    public static void addToHallOfFame(Player player){
        try {
            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Convert LocalDate to java.util.Date
            java.util.Date utilDate = java.util.Date.from(currentDate.atStartOfDay()
                                                    .atZone(java.time.ZoneId.systemDefault())
                                                    .toInstant());

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String sql = "INSERT INTO HALLOFFAME (PLAYERID, PLAYERNAME, LVL, DATEOFCOMPLETION) VALUES (" + player.getId() + ", " + player.name + ", " + player.lvl + ", " + sqlDate + ") ON DUPLICATE KEY UPDATE PLAYERNAME = VALUES(" + player.name + "), LVL = VALUES(" + player.lvl + "), DATEOFCOMPLETION = VALUES(" + sqlDate + ")";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //main saving method for use in game. calls private methods to save individual aspects. 
    public static void saveAll(){
        savePlayer();
        saveInventory();
    }
    
    //checks if save for game exists. Calls private methods. 
    public static boolean saveExist(){
        boolean playerSaveExists = checkPlayerSave();
        boolean inventorySaveExists = checkInventorySave();
        if(playerSaveExists && inventorySaveExists){
            return true;
        }
        else{
            return false;
        }
    }
    
    //loads the save 
    public static void loadSave(){
        Logic.player = loadPlayerSave();
        Logic.player.inventory = loadInventorySave();
    }
    
    private static boolean checkPlayerSave(){
        int ret = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM PLAYER");
            while(rs.next()){
                ret = rs.getInt("1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ret == 0){
            return false;
        }
        else {
            return true;
        }
    }
    
    private static boolean checkInventorySave(){
        int ret = 0;
        try {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM INVENTORY");
            while(rs.next()){
                ret = rs.getInt("1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ret == 0){
            return false;
        }
        else {
            return true;
        }
    }
    
    

    
    
    
    
    
    
//    //saves the player data to a file.
//    private static void savePlayer() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/player.txt"));
//            writer.write("Name: " + Logic.player.name);
//            writer.newLine();
//            writer.write("HP Stat: " + Logic.player.hpStat);
//            writer.newLine();
//            writer.write("Max HP: " + Logic.player.maxHP);
//            writer.newLine();
//            writer.write("HP: " + Logic.player.hp);
//            writer.newLine();
//            writer.write("XP Now: " + Logic.player.xpNow);
//            writer.newLine();
//            writer.write("XP Needed: " + Logic.player.xpNeeded);
//            writer.newLine();
//            writer.write("Level: " + Logic.player.lvl);
//            writer.newLine();
//            writer.write("Attack: " + Logic.player.atk);
//            writer.newLine();
//            writer.write("Defense: " + Logic.player.def);
//            writer.newLine();
//            writer.write("Speed: " + Logic.player.spd);
//            writer.newLine();
//            writer.write("Stat Point: " + Logic.player.statPoint);
//            writer.newLine();
//            writer.write("Money: " + Logic.player.money);
//            writer.newLine();
//            writer.write("Weapon: " + saveWeapon(Logic.player.weapon));
//            writer.newLine();
//            writer.write("Gender: " + Logic.player.gender);
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    //saves the player inventory to a file.
//    private static void saveInventory() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/inventory.txt"));
//            ArrayList<Weapon> weapons = Logic.player.inventory.getWeapons();
//            ArrayList<HealItem> healItems = Logic.player.inventory.getHealItems();
//            ArrayList<DmgItem> dmgItems = Logic.player.inventory.getDmgItems();
//            ArrayList<QuestItem> questItems = Logic.player.inventory.getQuestItems();
//            String out = "";
//            for(Weapon w : weapons){
//                out += "WEAPON-" + saveWeapon(w) + "\n";
//            }
//            for(HealItem h : healItems){
//                out += "HEAL-" + saveHeal(h) + "\n";
//            }
//            for(DmgItem d : dmgItems){
//                out += "DMG-" + saveDmg(d) + "\n";
//            }
//            for(QuestItem q : questItems){
//                out += "QUEST-" + saveQuest(q) + "\n";
//            }
//            writer.write(out);
//            writer.close();
//        } catch (IOException ex) {
//            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//
//    //saves the story act and current player location to a file.
//    private static void saveStory() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/story.txt"));
//            String out = "";
//            out += "ACT:" + Logic.act + "\n";
//            out += "PLACE:" + Logic.place;
//            writer.write(out);
//            writer.close();
//        } catch (IOException ex) {
//            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    //checks if a player save exists.
//    private static boolean checkPlayerSave() {
//        File file = new File("./saves/player.txt");
//        return file.exists();
//    }
//
//    //checks if a inventory save exists.
//    private static boolean checkInventorySave() {
//        File file = new File("./saves/inventory.txt");
//        return file.exists();
//    }
//
//    //checks if a story save exists.
//    private static boolean checkStorySave() {
//        File file = new File("./saves/story.txt");
//        return file.exists();
//    }
//    
//    //loads in the player save and returns a new player object in which loadSave will assign it to gameplayer.
//    private static Player loadPlayerSave(){
//        Player player = new Player("temp", "temp");
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("./saves/player.txt"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(": ");
//                String field = parts[0];
//                String value = parts[1];
//                switch (field) {
//                    case "Name":
//                        player.name = value;
//                        break;
//                    case "HP Stat":
//                        player.hpStat = Integer.parseInt(value);
//                        break;
//                    case "Max HP":
//                        player.maxHP = Integer.parseInt(value);
//                        break;
//                    case "HP":
//                        player.hp = Integer.parseInt(value);
//                        break;
//                    case "XP Now":
//                        player.xpNow = Integer.parseInt(value);
//                        break;
//                    case "XP Needed":
//                        player.xpNeeded = Integer.parseInt(value);
//                        break;
//                    case "Level":
//                        player.lvl = Integer.parseInt(value);
//                        break;
//                    case "Attack":
//                        player.atk = Integer.parseInt(value);
//                        break;
//                    case "Defense":
//                        player.def = Integer.parseInt(value);
//                        break;
//                    case "Speed":
//                        player.spd = Integer.parseInt(value);
//                        break;
//                    case "Stat Point":
//                        player.statPoint = Integer.parseInt(value);
//                        break;
//                    case "Money":
//                        player.money = Integer.parseInt(value);
//                        break;
//                    case "Weapon":
//                        player.weapon = Weapon.parseWeapon(value);
//                        break;
//                    case "Gender":
//                        player.gender = value;
//                        break;
//                    default:
//                        // Ignore unknown fields
//                        break;
//                }
//            }
//            reader.close();
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return player;
//    }
//    
//    //loads the inventory save and returns a new inventory object. loadsSave() will assign it to the player inventory.
//    private static Inventory loadInventorySave(){
//        Inventory inventory = new Inventory();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("./saves/inventory.txt"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("-");
//                String field = parts[0];
//                String value = parts[1];
//                switch (field) {
//                    case "WEAPON":
//                        inventory.addItem(Weapon.parseWeapon(value));
//                        break;
//                    case "HEAL":
//                        inventory.addItem(HealItem.parseHeal(value));
//                        break;
//                    case "DMG":
//                        inventory.addItem(DmgItem.parseDmg(value));
//                        break;
//                    case "QUEST":
//                        inventory.addItem(QuestItem.parseQuest(value));
//                        break;
//                }
//            }
//            reader.close();
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return inventory;
//    }
//    
//    //loads the story save file. 
//    private static void loadStorySave() {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("./saves/story.txt"));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(":");
//                String field = parts[0];
//                String value = parts[1];
//                switch (field) {
//                    case "ACT":
//                        Logic.act = Integer.parseInt(value);
//                        break;
//                    case "PLACE":
//                        Logic.place = Integer.parseInt(value);
//                        break;
//                }
//            }
//            reader.close();
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        
//    }
//
//    //converts weapon data to a string for saving in saveInventory
//    private static String saveWeapon(Weapon weapon) {
//        String out = "";
//        out += weapon.name + ":";
//        out += weapon.getAtk() + ":";
//        out += weapon.getDef() + ":";
//        out += weapon.getSpd() + ":";
//        out += weapon.price;
//        return out;
//    }
//
//    //converts healitem data to a string for saving in saveInventory
//    private static String saveHeal(HealItem h) {
//        String out = "";
//        out += h.name + ":";
//        out += h.heal + ":";
//        out += h.price;
//        return out;
//    }
//
//    //converts dmgitem data to a string for saving in saveInventory
//    private static String saveDmg(DmgItem d) {
//        String out = "";
//        out += d.name + ":";
//        out += d.dmg + ":";
//        out += d.price;
//        return out;
//    }
//
//    //converts questitem data to a string for saving in saveInventory
//    private static String saveQuest(QuestItem q) {
//        String out = "";
//        out += q.name + ":";
//        out += q.description;
//        return out;
//    }
}
