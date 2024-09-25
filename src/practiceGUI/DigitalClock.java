package practiceGUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock {
    public static void main(String[] args) {
        // Create the frame (window)
        JFrame frame = new JFrame("Digital Clock");

        // Create a label to display the time
        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 100)); // Set font size and style
        clockLabel.setForeground(Color.WHITE); // Set the clock text color to white
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

        // Set the background color of the window
        frame.getContentPane().setBackground(Color.BLACK); // Set the window background to black
        frame.add(clockLabel); // Add the clock label to the frame

        // Set up the frame (window)
        frame.setSize(800, 400); // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true); // Make the window visible

        // Create a Timer to update the clock every second
        Timer timer = new Timer(1000, e -> {
            // Get the current time and format it as "HH:mm:ss"
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            clockLabel.setText(currentTime); // Update the label with the current time
        });

        // Start the timer
        timer.start();
    }
}
