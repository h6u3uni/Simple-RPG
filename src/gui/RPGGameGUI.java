/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author haruk
 */
import items.Weapon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rpggame.Player;

public class RPGGameGUI extends JFrame {
    TitleView tView;
    NewGameView nGView;
    StatChooseView sCView;
    WeaponChooseView wCView;
    public GameView gView;
    /**
     * Constructor
     *
     * @param none
     * @return none
     */
    public RPGGameGUI() {
        // Set the title of the JFrame
        setTitle("Simple RPG");

        // Set the size of the JFrame
        setSize(new Dimension(400, 300));

        // Specify how the JFrame should be closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager (optional)
        // Here, we're using the default BorderLayout
        // You can choose any other layout manager based on your requirements
        setLayout(new BorderLayout());

//        // Add components to the JFrame
//        // Here, we're adding a JLabel to the center of the JFrame
//        JLabel label = new JLabel("Hello, World!");
//        add(label, BorderLayout.CENTER);
        tView = new TitleView();
        add(tView, BorderLayout.CENTER);
    }

    /*
     * Method to show the NewGameView
     * @param none
     * @return none
     */
    public void showNewGameView(){
        nGView = new NewGameView();
        removeAllPanels();
        add(nGView);
        revalidate();
        repaint();
    }
    
    /*
     * Method to show the StatChooseView
     * @param player - the player
     * @param initial - if it is the initial stat choose
     * @return none
     */
    public void showStatChooseView(Player player, boolean initial){
        sCView = new StatChooseView(player, initial);
        removeAllPanels();
        add(sCView);
        revalidate();
        repaint();
    }
    
    //use for new player only
    /*
     * Method to show the WeaponChooseView
     * @param weapons - list of weapons
     * @param player - the player
     * @param initial - if it is the initial weapon choose
     * @return none
     */
    public void showWeaponChooseView(ArrayList<Weapon> weapons, Player player, boolean initial){
        wCView = new WeaponChooseView(weapons, player, true);
        removeAllPanels();
        add(wCView);
        revalidate();
        repaint();
    }
    
    /*
     * Method to show the GameView
     * @param text - text to be displayed
     * @param end - if it is the end of the game
     * @param cont - if the game is to be continued
     */
    public void showGameView(String[] text, boolean end, boolean cont){
        gView = new GameView(text, end, cont);
        removeAllPanels();
        setSize(800,600);
        add(gView);
        revalidate();
        repaint();
    }
    
    //only used for start of game dont use elsewhere
    /*
     * Method to show the confirm view
     * @param player - the player
     * @param selectedWeapon - the selected weapon
     * @param weapons - list of weapons
     * @return none
     */
    public void showConfirmView(Player player, Weapon selectedWeapon, ArrayList<Weapon> weapons){
        ConfirmView confirmView = new ConfirmView(player, selectedWeapon, weapons);
        removeAllPanels();
        add(confirmView);
        revalidate();
        repaint();
    }
    
    /*
     * Method to remove all panels
     */
    private void removeAllPanels() {
        Component[] components = getContentPane().getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                getContentPane().remove(component);
            }
        }

        revalidate();
        repaint();
    }

    /*
     * Method to show the continue game view
     * @param saves - list of saves
     */
    public void showContinueGameView(ArrayList<Player> saves) {
        ContinueGameView continueView = new ContinueGameView(saves);
        removeAllPanels();
        add(continueView);
        revalidate();
        repaint();
    }

}
