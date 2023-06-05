package testingarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class PanelNavigationExample extends JFrame {

    private Stack<JPanel> panelStack;

    public PanelNavigationExample() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create panels
        JPanel panel1 = createPanel("Panel 1", Color.RED);
        JPanel panel2 = createPanel("Panel 2", Color.GREEN);
        JPanel panel3 = createPanel("Panel 3", Color.BLUE);

        // Initialize panel stack
        panelStack = new Stack<>();
        panelStack.push(panel1);  // Add initial panel to the stack
        panelStack.push(panel2);
        panelStack.push(panel3);
        
        // Create buttons
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Button action listeners
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousPanel();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextPanel();
            }
        });

        // Add components to the frame
        setLayout(new BorderLayout());
        add(panelStack.peek(), BorderLayout.CENTER);
        add(previousButton, BorderLayout.WEST);
        add(nextButton, BorderLayout.EAST);
    }

    private JPanel createPanel(String name, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.add(new JLabel(name));
        return panel;
    }

    private void showPreviousPanel() {
        if (panelStack.size() > 1) {
            JPanel currentPanel = panelStack.pop();
            JPanel previousPanel = panelStack.peek();
            remove(currentPanel);
            add(previousPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    private void showNextPanel() {
        // Check if there is a next panel
        if (panelStack.size() < 3) {
            JPanel currentPanel = panelStack.peek();
            JPanel nextPanel = null;

            // Determine the next panel based on the current panel
            if (currentPanel.equals(panelStack.get(0))) {
                nextPanel = panelStack.get(1);
            } else if (currentPanel.equals(panelStack.get(1))) {
                nextPanel = panelStack.get(2);
            }

            if (nextPanel != null) {
                remove(currentPanel);
                add(nextPanel, BorderLayout.CENTER);
                panelStack.push(nextPanel);
                revalidate();
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PanelNavigationExample frame = new PanelNavigationExample();
            frame.setVisible(true);
        });
    }
}
