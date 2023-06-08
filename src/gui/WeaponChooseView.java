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
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import rpggame.Logic;
import rpggame.Player;

// panel for choosing a weapon
public class WeaponChooseView extends JPanel {

    private JList<Weapon> weaponList;
    private JButton chooseButton;
    private JButton cancelButton; // Added new button

    public WeaponChooseView(ArrayList<Weapon> weapons, Player player, boolean initial) {
        setLayout(new BorderLayout());

        // Create the header label
        String header = "";
        if(initial){
            header = "Select a Weapon";
        }
        else{
            header = "Select a Weapon to Switch from " + player.weapon.getName();
        }
        JLabel headerLabel = new JLabel(header);
        headerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        // Create the weapon list model
        DefaultListModel<Weapon> listModel = new DefaultListModel<>();
        for (Weapon weapon : weapons) {
            listModel.addElement(weapon);
        }

        // Create the weapon list
        weaponList = new JList<>(listModel);
        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the weapon list to it
        JScrollPane scrollPane = new JScrollPane(weaponList);
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        // Create the choose button
        chooseButton = new JButton("Choose");
        buttonPanel.add(chooseButton);
        
        // Create the another button
        cancelButton = new JButton("Cancel");
        if(!initial){
            buttonPanel.add(cancelButton);
        }
        
        // Add the button panel to the south of the main panel
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listener to the Choose button
        chooseButton.addActionListener(e -> {
            int selectedIndex = weaponList.getSelectedIndex();
            if (selectedIndex != -1) {
                Weapon selectedWeapon = (Weapon) weaponList.getModel().getElementAt(selectedIndex);
                if (Logic.newPlay) {
                    Logic.frame.showConfirmView(player, selectedWeapon, weapons);
                } else {
                    Logic.frame.gView.goNext(new ConfirmView(player, selectedWeapon, weapons));
                }
            }
        });

        // Add action listener to the Another button
        cancelButton.addActionListener(e -> {
            Logic.frame.gView.goBack();
        });
    }
}
