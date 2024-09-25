package practiceGUI;

import javax.swing.*; // For GUI components
import java.awt.event.*; // For handling button clicks

public class SimpleGuiExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple GUI"); // Create a window
        JButton button = new JButton("Click Me!"); // Create a button

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button Clicked!"); // Show a message when clicked
            }
        });

        // Set up the frame
        frame.setSize(300, 200); // Set size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when closed
        frame.add(button); // Add button to the window
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true); // Show the window
    }
}
