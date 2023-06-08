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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import rpggame.Logic;
import rpggame.Player;
import rpggame.SaveManager;

public class GameView extends JPanel {
    private Stack<JPanel> panelStack;
    public JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel dynamicContentPanel;
    private JButton exploreButton;
    private JButton changeLocationButton;
    private JButton characterInfoButton;
    private JButton inventoryOptionsButton;
    private JButton goToShopButton;
    private JButton saveGameButton;
    private JButton exitGameButton;
    private JButton storyButton;
    private int index;
    private boolean end;
    
    public GameView(String[] dialogue, boolean end, boolean cont) {
        setLayout(new BorderLayout());
        
        panelStack = new Stack<>();
        index = 1;
        this.end = end;
        // Create the text area
        textArea = new JTextArea(dialogue[0]);
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.NORTH);

        // Create the dynamic content panel
        dynamicContentPanel = new JPanel();
        dynamicContentPanel.setPreferredSize(new Dimension(600,320));
        
        if(!cont){
            storyButton = new JButton("Continue");
            storyButton.addActionListener(e -> contDialogue(dialogue, index));
            dynamicContentPanel.add(storyButton);
        }
        dynamicContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(dynamicContentPanel, BorderLayout.CENTER);

        // Create the main menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the menu buttons
        exploreButton = new JButton("Explore");
        exploreButton.addActionListener(e -> Logic.continueJourney());
        
        changeLocationButton = new JButton("Change Location");
        changeLocationButton.addActionListener(e -> changeLocation());
        
        characterInfoButton = new JButton("Character Info");
        characterInfoButton.addActionListener(e -> showCharInfoPanel(Logic.player));
        
        inventoryOptionsButton = new JButton("Inventory Options");
        inventoryOptionsButton.addActionListener(e -> openInventory());
        
        goToShopButton = new JButton("Go to Shop");
        goToShopButton.addActionListener(e -> openShop());
        
        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(e -> saveGame());
        
        exitGameButton = new JButton("Exit Game");
        exitGameButton.addActionListener(e -> exitGame());
        
        // Add the buttons to the menu panel
        menuPanel.add(exploreButton);
        menuPanel.add(changeLocationButton);
        menuPanel.add(characterInfoButton);
        menuPanel.add(inventoryOptionsButton);
        menuPanel.add(goToShopButton);
        menuPanel.add(saveGameButton);
        menuPanel.add(exitGameButton);
        
        if(!cont){
            disableButtons();
        }
        
        // Add the menu panel to the right side of the main panel
        add(menuPanel, BorderLayout.EAST);
    }
    
    public void disableButtons(){
        exploreButton.setEnabled(false);
        changeLocationButton.setEnabled(false);
        characterInfoButton.setEnabled(false);
        inventoryOptionsButton.setEnabled(false);
        goToShopButton.setEnabled(false);
        saveGameButton.setEnabled(false);
        exitGameButton.setEnabled(false);
    }
    
    public void enableButtons(){
        exploreButton.setEnabled(true);
        changeLocationButton.setEnabled(true);
        characterInfoButton.setEnabled(true);
        inventoryOptionsButton.setEnabled(true);
        goToShopButton.setEnabled(true);
        saveGameButton.setEnabled(true);
        exitGameButton.setEnabled(true);
    }
    
    public void goBack(){
        if(panelStack.size() <= 1){
            resetDynamicPanel();
            revalidate();
            repaint();
        }
        else if (panelStack.size() > 1) {
            JPanel currentPanel = panelStack.pop();
            JPanel previousPanel = panelStack.peek();
            dynamicContentPanel.remove(currentPanel);
            dynamicContentPanel.add(previousPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
    
    public void goNext(JPanel panel){
        if(panelStack.size() > 0){
            JPanel previousPanel = panelStack.peek();
            dynamicContentPanel.remove(previousPanel);
        }
        panelStack.push(panel);
        JPanel newPanel = panelStack.peek();
        dynamicContentPanel.add(newPanel);
        revalidate();
        repaint();
    }

    public void resetDynamicPanel() {
        panelStack.clear();
        removeAllContentInDynamicPanel();
    }
    
    private void showCharInfoPanel(Player player) {
        resetDynamicPanel();
        CharInfoView cInfo = new CharInfoView(player);
        dynamicContentPanel.add(cInfo);
        revalidate();
        repaint();
    }
    
    private void removeAllContentInDynamicPanel() {
        Component[] components = dynamicContentPanel.getComponents();

        for (Component component : components) {
            dynamicContentPanel.remove(component);
        }

        revalidate();
        repaint();
    }

    private void exitGame() {
        resetDynamicPanel();
        ExitView eView = new ExitView();
        dynamicContentPanel.add(eView);
        revalidate();
        repaint();
    }

    private void saveGame() {
        resetDynamicPanel();
        String query = "";
        if(SaveManager.saveExist()){
            query = "Only one save file can exist for each player. Overwrite previous save?";
        }
        else{
            query = "Save game?";
        }
        ConfirmView saveView = new ConfirmView(query);
        JButton yButton = saveView.getYesButton();
        JButton nButton = saveView.getNoButton();
        nButton.setText("Cancel");
        yButton.addActionListener((ActionEvent e) -> {
            SaveManager.saveAll();
            resetDynamicPanel();
            JLabel confirm = new JLabel("Saved!");
            dynamicContentPanel.add(confirm);
            revalidate();
            repaint();
        });
        nButton.addActionListener(e -> resetDynamicPanel());
        
        dynamicContentPanel.add(saveView);
        revalidate();
        repaint();
    }

    public void openShop() {
        resetDynamicPanel();
        ShopView shopView = new ShopView(Logic.player);
        goNext(shopView);
        revalidate();
        repaint();
    }

    public void openInventory() {
        resetDynamicPanel();
        InventoryView iView = new InventoryView(Logic.player);
        goNext(iView);
        revalidate();
        repaint();
    }
    
    private void showHallOfFame() {
        resetDynamicPanel();
        HallOfFameView hofView = new HallOfFameView();
        dynamicContentPanel.add(hofView);
        revalidate();
        repaint();
    }

    private void contDialogue(String[] dialogue, int i) {
        index++;
        if(i < dialogue.length){
            Logic.setGUIText(dialogue[i]);
        }
        else{
            if(end){
                SaveManager.addToHallOfFame(Logic.player);
                enableButtons();
                showHallOfFame();
            }
            else{
                Logic.setGUIText(Logic.getCurrentLocationText());
                resetDynamicPanel();
                enableButtons();
            }
        }
    }

    private void changeLocation() {
        resetDynamicPanel();
        ChangeLocationView clView = new ChangeLocationView(Logic.act);
        dynamicContentPanel.add(clView);
        revalidate();
        repaint();
    }
    
    public void showDeadView(){
        resetDynamicPanel();
        disableButtons();
        DeadView dView = new DeadView();
        dynamicContentPanel.add(dView);
        revalidate();
        repaint();
    }

    public void addGUIText(String text) {
        textArea.append(text);
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }

    public void showBattleView(Player player) {
        resetDynamicPanel();
        disableButtons();
        BattleView bView = new BattleView(player);
        goNext(bView);
        revalidate();
        repaint();
    }

    public void showStatChooseView() {
        resetDynamicPanel();
        disableButtons();
        StatChooseView sCView = new StatChooseView(Logic.player, false);
        goNext(sCView);
        revalidate();
        repaint();
    }
}
