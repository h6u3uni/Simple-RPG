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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class ConfirmView extends JPanel {

    private JLabel textLabel;

    public ConfirmView() {
        setLayout(new BorderLayout());

        // Add padding on the top with an EmptyBorder
        int topPadding = 20; // Adjust the value as needed
        setBorder(new EmptyBorder(topPadding, 0, 0, 0));

        // Create the JLabel for the dynamic text
        textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textLabel, BorderLayout.NORTH);

        // Create the buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Create a JPanel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void setDynamicText(String text) {
        textLabel.setText(text);
    }

//    public static void main(String[] args) {
//        ConfirmView confirmView = new ConfirmView();
//        confirmView.setDynamicText("Dynamic Text");
//
//        // Create a JFrame to test the ConfirmView
//        javax.swing.JFrame frame = new javax.swing.JFrame("Confirm View Test");
//        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(confirmView);
//        frame.setPreferredSize(new Dimension(300, 200));
//        frame.pack();
//        frame.setVisible(true);
//    }
}
