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
import java.util.ArrayList;
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
    
    
    
    
    
    
    //main saving method for use in game. calls private methods to save individual aspects. 
    public static void saveAll(){
        savePlayer();
        saveInventory();
        saveStory();
    }
    
    //checks if save for game exists. Calls private methods. 
    public static boolean saveExist(){
        boolean playerSaveExists = checkPlayerSave();
        boolean inventorySaveExists = checkInventorySave();
        boolean storySaveExists = checkStorySave();
        if(playerSaveExists && inventorySaveExists && storySaveExists){
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
        loadStorySave();
    }
    
    //there is a hof file where the names of players who completed the game will be recorded.
    //this method returns an arraylist of the names of the players who cleared the game.
//    public static ArrayList<String> getHallOfFame(){
//        ArrayList<String> hof = new ArrayList<>();
//        String pName = "";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("./saves/hof.txt"));
//            try {
//                while((pName = br.readLine()) != null){
//                    hof.add(pName);
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return hof;
//    }
    
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
            String sql = "INSERT INTO HALLOFFAME (PLAYERNAME, PLAYERID, LVL, DATEOFCOMPLETION) VALUES (" + player.name + ", " + player.;
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //saves the player data to a file.
    private static void savePlayer() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/player.txt"));
            writer.write("Name: " + Logic.player.name);
            writer.newLine();
            writer.write("HP Stat: " + Logic.player.hpStat);
            writer.newLine();
            writer.write("Max HP: " + Logic.player.maxHP);
            writer.newLine();
            writer.write("HP: " + Logic.player.hp);
            writer.newLine();
            writer.write("XP Now: " + Logic.player.xpNow);
            writer.newLine();
            writer.write("XP Needed: " + Logic.player.xpNeeded);
            writer.newLine();
            writer.write("Level: " + Logic.player.lvl);
            writer.newLine();
            writer.write("Attack: " + Logic.player.atk);
            writer.newLine();
            writer.write("Defense: " + Logic.player.def);
            writer.newLine();
            writer.write("Speed: " + Logic.player.spd);
            writer.newLine();
            writer.write("Stat Point: " + Logic.player.statPoint);
            writer.newLine();
            writer.write("Money: " + Logic.player.money);
            writer.newLine();
            writer.write("Weapon: " + saveWeapon(Logic.player.weapon));
            writer.newLine();
            writer.write("Gender: " + Logic.player.gender);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //saves the player inventory to a file.
    private static void saveInventory() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/inventory.txt"));
            ArrayList<Weapon> weapons = Logic.player.inventory.getWeapons();
            ArrayList<HealItem> healItems = Logic.player.inventory.getHealItems();
            ArrayList<DmgItem> dmgItems = Logic.player.inventory.getDmgItems();
            ArrayList<QuestItem> questItems = Logic.player.inventory.getQuestItems();
            String out = "";
            for(Weapon w : weapons){
                out += "WEAPON-" + saveWeapon(w) + "\n";
            }
            for(HealItem h : healItems){
                out += "HEAL-" + saveHeal(h) + "\n";
            }
            for(DmgItem d : dmgItems){
                out += "DMG-" + saveDmg(d) + "\n";
            }
            for(QuestItem q : questItems){
                out += "QUEST-" + saveQuest(q) + "\n";
            }
            writer.write(out);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //saves the story act and current player location to a file.
    private static void saveStory() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./saves/story.txt"));
            String out = "";
            out += "ACT:" + Logic.act + "\n";
            out += "PLACE:" + Logic.place;
            writer.write(out);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //checks if a player save exists.
    private static boolean checkPlayerSave() {
        File file = new File("./saves/player.txt");
        return file.exists();
    }

    //checks if a inventory save exists.
    private static boolean checkInventorySave() {
        File file = new File("./saves/inventory.txt");
        return file.exists();
    }

    //checks if a story save exists.
    private static boolean checkStorySave() {
        File file = new File("./saves/story.txt");
        return file.exists();
    }
    
    //loads in the player save and returns a new player object in which loadSave will assign it to gameplayer.
    private static Player loadPlayerSave(){
        Player player = new Player("temp", "temp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./saves/player.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String field = parts[0];
                String value = parts[1];
                switch (field) {
                    case "Name":
                        player.name = value;
                        break;
                    case "HP Stat":
                        player.hpStat = Integer.parseInt(value);
                        break;
                    case "Max HP":
                        player.maxHP = Integer.parseInt(value);
                        break;
                    case "HP":
                        player.hp = Integer.parseInt(value);
                        break;
                    case "XP Now":
                        player.xpNow = Integer.parseInt(value);
                        break;
                    case "XP Needed":
                        player.xpNeeded = Integer.parseInt(value);
                        break;
                    case "Level":
                        player.lvl = Integer.parseInt(value);
                        break;
                    case "Attack":
                        player.atk = Integer.parseInt(value);
                        break;
                    case "Defense":
                        player.def = Integer.parseInt(value);
                        break;
                    case "Speed":
                        player.spd = Integer.parseInt(value);
                        break;
                    case "Stat Point":
                        player.statPoint = Integer.parseInt(value);
                        break;
                    case "Money":
                        player.money = Integer.parseInt(value);
                        break;
                    case "Weapon":
                        player.weapon = Weapon.parseWeapon(value);
                        break;
                    case "Gender":
                        player.gender = value;
                        break;
                    default:
                        // Ignore unknown fields
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return player;
    }
    
    //loads the inventory save and returns a new inventory object. loadsSave() will assign it to the player inventory.
    private static Inventory loadInventorySave(){
        Inventory inventory = new Inventory();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./saves/inventory.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String field = parts[0];
                String value = parts[1];
                switch (field) {
                    case "WEAPON":
                        inventory.addItem(Weapon.parseWeapon(value));
                        break;
                    case "HEAL":
                        inventory.addItem(HealItem.parseHeal(value));
                        break;
                    case "DMG":
                        inventory.addItem(DmgItem.parseDmg(value));
                        break;
                    case "QUEST":
                        inventory.addItem(QuestItem.parseQuest(value));
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inventory;
    }
    
    //loads the story save file. 
    private static void loadStorySave() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./saves/story.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String field = parts[0];
                String value = parts[1];
                switch (field) {
                    case "ACT":
                        Logic.act = Integer.parseInt(value);
                        break;
                    case "PLACE":
                        Logic.place = Integer.parseInt(value);
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    //converts weapon data to a string for saving in saveInventory
    private static String saveWeapon(Weapon weapon) {
        String out = "";
        out += weapon.name + ":";
        out += weapon.getAtk() + ":";
        out += weapon.getDef() + ":";
        out += weapon.getSpd() + ":";
        out += weapon.price;
        return out;
    }

    //converts healitem data to a string for saving in saveInventory
    private static String saveHeal(HealItem h) {
        String out = "";
        out += h.name + ":";
        out += h.heal + ":";
        out += h.price;
        return out;
    }

    //converts dmgitem data to a string for saving in saveInventory
    private static String saveDmg(DmgItem d) {
        String out = "";
        out += d.name + ":";
        out += d.dmg + ":";
        out += d.price;
        return out;
    }

    //converts questitem data to a string for saving in saveInventory
    private static String saveQuest(QuestItem q) {
        String out = "";
        out += q.name + ":";
        out += q.description;
        return out;
    }
}
