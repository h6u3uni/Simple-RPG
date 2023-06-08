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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author haruk
 */
public class SaveManagerTest {
    
    public SaveManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class SaveManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        SaveManager instance = new SaveManager();
        assertTrue(instance.getConnection() != null);
    }

    /**
     * Test of establishConnection method, of class SaveManager.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        SaveManager instance = new SaveManager();
        instance.establishConnection();
        // Add assertions here to verify the expected behavior of this test case.
        assertTrue(instance.getConnection() != null);
    }
    
    

    /**
     * Test of getShopHeals method, of class SaveManager.
     */
    @Test
    public void testGetShopHeals() {
        System.out.println("getShopHeals");
        HealItem[] expResult = {new HealItem("Small Potion", 20, 100), new HealItem("Medium Potion", 50, 200), new HealItem("High Potion", 150, 500), new HealItem("Elixir", 1000000, 1000)};
        SaveManager instance = new SaveManager();
        HealItem[] result = SaveManager.getShopHeals();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getShopDmgs method, of class SaveManager.
     */
    @Test
    public void testGetShopDmgs() {
        System.out.println("getShopDmgs");
        DmgItem[] expResult = {new DmgItem("Small Bomb", 20, 100), new DmgItem("Medium Bomb", 50, 200), new DmgItem("High Bomb", 150, 500)};
        SaveManager instance = new SaveManager();
        DmgItem[] result = SaveManager.getShopDmgs();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getStarterWeapons method, of class SaveManager.
     */
    @Test
    public void testGetStarterWeapons() {
        System.out.println("getStarterWeapons");
        Weapon[] expResult = {new Weapon("Basic Dagger", 1, 0, 1, 10), new Weapon("Basic Sword", 2, 0, 0, 10), new Weapon("Basic Axe", 3, 0, -1, 10), new Weapon("Basic Shield", 1, 2, -1, 10)};
        SaveManager instance = new SaveManager();
        ArrayList<Weapon> result = SaveManager.getStarterWeapons();
        Weapon[] resultArray = new Weapon[result.size()];
        for(int i = 0; i < result.size(); i++){
            resultArray[i] = result.get(i);
        }
        assertArrayEquals(expResult, resultArray);
    }

    /**
     * Test of getPlaces method, of class SaveManager.
     */
    @Test
    public void testGetPlaces() {
        System.out.println("getPlaces");
        String[] expResult = {"Elven City", "Goblin Forest", "Ancient Tomb", "Minotaur Labyrinth", "Death Mountain", "Demon Castle", "hidden grove"};
        SaveManager instance = new SaveManager();
        String[] result = SaveManager.getPlaces();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of saveExist method, of class SaveManager.
     */
    @Test
    public void testSaveExist() {
        System.out.println("saveExist");
        boolean expResult = true; //true since save file exists in database as of right now. will be false if no saves exist
        SaveManager instance = new SaveManager();
        boolean result = SaveManager.saveExist();
        assertEquals(expResult, result);
    }
    
    
}
