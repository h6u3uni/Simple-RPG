import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentChildPanelExample extends JFrame {

    public ParentChildPanelExample() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create parent panel
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());

        // Create child panel
        JPanel childPanel = new JPanel();
        childPanel.setLayout(new FlowLayout());

        // Create button in child panel
        JButton button = new JButton("Call Parent Method");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Access parent panel's method
                getParentPanel().parentMethod();
            }
        });

        // Add button to the child panel
        childPanel.add(button);

        // Add child panel to the parent panel
        parentPanel.add(childPanel, BorderLayout.CENTER);

        // Add parent panel to the frame
        add(parentPanel);
    }

    public void parentMethod() {
        System.out.println("Parent method called");
    }

    private ParentChildPanelExample getParentPanel() {
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParentChildPanelExample frame = new ParentChildPanelExample();
            frame.setVisible(true);
        });
    }
}
