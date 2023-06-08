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
import dialogue.Dialogue;
import items.DmgItem;
import items.HealItem;
import items.Item;
import items.Weapon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import rpggame.Logic;
import rpggame.Player;

public class ConfirmView extends JPanel {

    // Declare the components
    private JLabel textLabel;
    private JButton yesButton;
    private JButton noButton;

    /*
     * Constructor
     * @param query The query to display
     * @return none
     */
    public ConfirmView(String query) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));

        // Create the JLabel for the dynamic text
        textLabel = new JLabel(query, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);

        // Create the buttons
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    /*
     * Constructor
     * @param player The player
     * @param selectedWeapon The selected weapon
     * @param weapons The list of weapons
     * @return none
     */
    public ConfirmView(Player player, Weapon selectedWeapon, ArrayList<Weapon> weapons) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));
        
        String separator = Logic.createSeparator(selectedWeapon.name.length()*2);
        
        // Create the JLabel for the dynamic text
        textLabel = new JLabel("<html>Confirm weapon? <br/><br/>" + separator + "<br/>" + selectedWeapon.name + "<br/>" + separator + "<br/>Attack Boost: " + selectedWeapon.getAtk() + "<br/>Defense Boost: " + selectedWeapon.getDef() + "<br/>Speed Boost: " + selectedWeapon.getSpd() + "</html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);

        // Create the buttons
        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "Yes" is clicked
                if(Logic.newPlay){
                    player.weapon = selectedWeapon;
                    Logic.frame.showGameView(Dialogue.getDialogueIntro(), false, false);
                }
                else{
                    player.inventory.addItem(player.weapon);
                    player.weapon = selectedWeapon;
                    player.inventory.removeItem(selectedWeapon);
                    Logic.frame.gView.goBack();
                    Logic.frame.gView.goBack();
                }
            }
        });
        
        // Create a JPanel to hold the buttons
        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "No" is clicked
                if(Logic.newPlay){
                    Logic.frame.showWeaponChooseView(weapons, player, Logic.newPlay);
                }
                else {
                    Logic.frame.gView.goBack();
                }
                
            }
        });
        
        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    /*
     * Constructor
     * @param Item The item being used
     * @param player The player
     * @param boolean Whether the item is being sold
     * @return none
     */
    public ConfirmView(Item item, Player player, Boolean sell) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));
        
        String separator = Logic.createSeparator(item.getName().length());
        
        // Create the JLabel for the dynamic text
        String itemDetails = "";
        if(item instanceof Weapon){
            Weapon selectedWeapon = (Weapon)item;
            int price = selectedWeapon.getPrice();
            if(sell){
                price = selectedWeapon.getPrice()/2;
            }
            itemDetails = "<br/>Attack Boost: " + selectedWeapon.getAtk() + "<br/>Defense Boost: " + selectedWeapon.getDef() + "<br/>Speed Boost: " + selectedWeapon.getSpd() + "<br/>Price: " + price;
        } 
        else if(item instanceof HealItem){
            HealItem selectedHeal = (HealItem)item;
            int price = selectedHeal.getPrice();
            if(sell){
                price = selectedHeal.getPrice()/2;
            }
            itemDetails = "<br/>Healing: " + selectedHeal.heal + "<br/>Price: " + price;
        }
        else if(item instanceof DmgItem){
            DmgItem selectedDmg = (DmgItem)item;
            int price = selectedDmg.getPrice();
            if(sell){
                price = selectedDmg.getPrice()/2;
            }
            itemDetails = "<br/>Damage: " + selectedDmg.dmg + "<br/>Price: " + price;
        }
        
        // Create the JLabel for the dynamic text
        String option = "Buy?";
        if(sell){
            option = "Sell?";
        }
        textLabel = new JLabel("<html>" + option +"<br/><br/>" + separator + "<br/>" + item.getName() + "<br/>" + separator + itemDetails  + "</html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);
        
        
        // Create the buttons
        yesButton = new JButton("Yes");
        if(item.getPrice() > player.money && !sell){
            yesButton.setEnabled(false);
        }
        else{
            yesButton.setEnabled(true);
        }
        
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "Yes" is clicked
                if(item instanceof Weapon){
                    Weapon selectedWeapon = (Weapon)item;
                    if(sell){
                        player.money += selectedWeapon.getPrice()/2;
                    }
                    else{
                        player.inventory.addItem(selectedWeapon);
                        player.money -= selectedWeapon.getPrice();
                    }
                    confirmed("Confirmed");
                } 
                else if(item instanceof HealItem){
                    HealItem selectedHeal = (HealItem)item;
                    if(sell){
                        player.money += selectedHeal.getPrice()/2;
                    }
                    else{
                        player.inventory.addItem(selectedHeal);
                        player.money -= selectedHeal.getPrice();
                    }
                    confirmed("Confirmed");
                }
                else if(item instanceof DmgItem){
                    DmgItem selectedDmg = (DmgItem)item;
                    if(sell){
                        player.money += selectedDmg.getPrice()/2;
                    }
                    else{
                        player.inventory.addItem(selectedDmg);
                        player.money -= selectedDmg.getPrice();
                    }
                    confirmed("Confirmed");
                }
            }
        });
        
        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "No" is clicked
                Logic.frame.gView.goBack();
            }
        });
        
        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
    
    //use item confirmation
    /*
     * Constructor
     * @param Item The item being used
     * @param player The player
     * @param boolean whether the player is in battle
     */
    public ConfirmView(Player player, Item item, Boolean inBattle) {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));
        
        String separator = Logic.createSeparator(item.getName().length());
        
        // Create the JLabel for the dynamic text
        String itemDetails = "";
        if(item instanceof HealItem){
            HealItem selectedHeal = (HealItem)item;
            itemDetails = "<br/>Healing: " + selectedHeal.heal;
        }
        else if(item instanceof DmgItem){
            DmgItem selectedDmg = (DmgItem)item;
            itemDetails = "<br/>Damage: " + selectedDmg.dmg;
        }
        
        // Create the JLabel for the dynamic text
        String option = "Use item?";
        textLabel = new JLabel("<html>" + option +"<br/><br/>" + separator + "<br/>" + item.getName() + "<br/>" + separator + itemDetails  + "</html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);
        
        
        // Create the buttons
        yesButton = new JButton("Yes");
        if(item instanceof HealItem && player.hp == player.maxHP){
            yesButton.setEnabled(false);
        }
        else{
            yesButton.setEnabled(true);
        }
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "Yes" is clicked
                if(item instanceof HealItem){
                    HealItem selectedHeal = (HealItem)item;
                    if(inBattle){
                        player.hp += selectedHeal.heal;
                        if(player.hp > player.maxHP){
                            player.hp = player.maxHP;
                        }
                    }
                    else{
                        player.hp += selectedHeal.heal;
                        if(player.hp > player.maxHP){
                            player.hp = player.maxHP;
                        }
                    }
                    player.inventory.removeItem(selectedHeal);
                    confirmed("Used " + selectedHeal.getName());
                }
                else if(item instanceof DmgItem){
                    DmgItem selectedDmg = (DmgItem)item;
                    if(inBattle){
                        player.currEnemy.hp -= selectedDmg.dmg;
                        Logic.addGUIText("\nThe item dealth " + selectedDmg.dmg + " damage to the enemy!");
                        player.inventory.removeItem(selectedDmg);
                        confirmed("Used " + selectedDmg.getName());
                        if(player.currEnemy.hp <= 0){
                            Logic.battleFin(true, player.currEnemy);
                        }
                    }
                    else{
                        player.hp-= selectedDmg.dmg;
                        player.inventory.removeItem(selectedDmg);
                        confirmed("Used " + selectedDmg.getName() + ". You took damage!");
                    }
                }
            }
        });
        
        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the desired action when "No" is clicked
                Logic.frame.gView.goBack();
            }
        });
        
        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
    
    public JButton getYesButton(){
        return yesButton;
    }
    
    public JButton getNoButton(){
        return noButton;
    }
    
    /*
     * Method to confirm the action
     * @param label The label to display
     * @return none
     */
    public void confirmed(String label){
        removeAllContent();
        textLabel = new JLabel(label);
        JButton okButton = new JButton("Ok");
        okButton.addActionListener((ActionEvent e) -> {
            if(Logic.player.hp <= 0){
                Logic.playerDied();
            }
            Logic.frame.gView.goBack();
        });
        add(textLabel, BorderLayout.NORTH);
        add(okButton, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    /*
     * Method to remove all content from the panel
     * @return none
     */
    private void removeAllContent() {
        Component[] components = getComponents();
        for (Component component : components) {
            remove(component);
        }
        revalidate();
        repaint();
    }

}
