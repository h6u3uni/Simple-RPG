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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRemovalExample extends JFrame {

    private JPanel mainPanel;

    public PanelRemovalExample() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        mainPanel = new JPanel(new BorderLayout());

        // Create a button to add a new panel
        JButton addButton = new JButton("Add Panel");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPanel();
            }
        });

        mainPanel.add(addButton, BorderLayout.NORTH);

        getContentPane().add(mainPanel);
    }

    private void addNewPanel() {
        JPanel panel = new JPanel();
        JButton removeButton = new JButton("Remove Panel");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the parent container (panel) of the button
                JPanel parentPanel = (JPanel) removeButton.getParent();

                // Remove the parent panel from the main panel
                mainPanel.remove(parentPanel);

                // Update the layout
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        panel.add(removeButton);
        mainPanel.add(panel, BorderLayout.CENTER);

        // Update the layout
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        PanelRemovalExample example = new PanelRemovalExample();
        example.setVisible(true);
    }
}
