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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class WeaponChooseView extends JPanel {

    private JList<Weapon> weaponList;
    private JButton chooseButton;

    public WeaponChooseView(ArrayList<Weapon> weapons, Player player) {
        setLayout(new BorderLayout());

        // Create the header label
        JLabel headerLabel = new JLabel("Select a Weapon");
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

        // Create the choose button
        chooseButton = new JButton("Choose");
        add(chooseButton, BorderLayout.SOUTH);

        // Add action listener to the Choose button
        chooseButton.addActionListener(e -> {
            int selectedIndex = weaponList.getSelectedIndex();
            if (selectedIndex != -1) {
                Weapon selectedWeapon = (Weapon) weaponList.getModel().getElementAt(selectedIndex);
//                player.setWeapon(selectedWeapon);
                if(Logic.newPlay){
                    Logic.frame.showConfirmView(player, selectedWeapon, weapons);
                }
                else{
                    Logic.frame.gView.goNext(new ConfirmView(player, selectedWeapon, weapons));
                }
                
            }
        });
    }
}
