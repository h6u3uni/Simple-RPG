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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import rpggame.Player;

public class RPGGameGUI extends JFrame {
    TitleView tView;
    NewGameView nGView;
    StatChooseView sCView;
    WeaponChooseView wCView;
    
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

//    public static void main(String[] args) {
//        // Create an instance of the MyFrame class
//        RPGGameGUI frame = new RPGGameGUI();
//
//        // Make the JFrame visible
//        frame.setVisible(true);
//    }
    
    public void showNewGameView(){
        nGView = new NewGameView();
        removeAllPanels();
        add(nGView);
        revalidate();
        repaint();
    }
    
    public void showStatChooseView(Player player, boolean initial){
        sCView = new StatChooseView(player, initial);
        removeAllPanels();
        add(sCView);
        revalidate();
        repaint();
    }
    
    public void showWeaponChooseView(){
        wCView = new WeaponChooseView();
        removeAllPanels();
        add(wCView);
        revalidate();
        repaint();
    }
    
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

}
