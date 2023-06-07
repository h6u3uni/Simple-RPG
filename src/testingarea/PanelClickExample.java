/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingarea;

/**
 *
 * @author haruk
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelClickExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel Click Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JPanel parentPanel = new JPanel();
            parentPanel.setLayout(new BorderLayout());
            parentPanel.addMouseListener(new PanelMouseListener());
            
            JPanel childPanel = new JPanel();
            childPanel.setBackground(Color.RED);
            
            parentPanel.add(childPanel, BorderLayout.CENTER);
            frame.getContentPane().add(parentPanel);
            
            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
    
    static class PanelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel panel = (JPanel) e.getSource();
            Container parent = panel.getParent();
            
            if (parent instanceof JFrame) {
                JFrame frame = (JFrame) parent;
                System.out.println("Clicked on JFrame: " + frame.getTitle());
            } else if (parent instanceof JPanel) {
                JPanel parentPanel = (JPanel) parent;
                System.out.println("Clicked on JPanel");
            }
        }
    }
}