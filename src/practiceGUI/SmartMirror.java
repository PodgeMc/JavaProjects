package practiceGUI;

import javax.swing.*;  // Import tools to make windows and buttons
import java.awt.*;     // Import tools to set colors and fonts
import java.awt.event.ActionEvent;  // Import tool for handling key actions
import java.text.SimpleDateFormat; // Import tool to format the time and date
import java.util.Date; // Import tool to get the current date and time

public class SmartMirror
{
    public static void main(String[] args)
    {
        // Create a window (frame)
        JFrame frame = new JFrame();

        // Make the window undecorated (no borders, no title bar)
        frame.setUndecorated(true);

        // Create a label (text) to show the time at the top
        JLabel clockLabel = new JLabel();
        // Set the text size and style to be big and bold
        clockLabel.setFont(new Font("Arial", Font.BOLD, 50));
        // Set the text color to white
        clockLabel.setForeground(Color.WHITE);
        // Center the text horizontally
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create another label to show the date at the bottom
        JLabel dateLabel = new JLabel();
        // Set the text size and style to be big and bold
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        // Set the text color to white
        dateLabel.setForeground(Color.WHITE);
        // Center the date text horizontally
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        // Set the window background color to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Use BorderLayout to place the clock at the top center and the date at the bottom
        frame.setLayout(new BorderLayout());
        frame.add(clockLabel, BorderLayout.NORTH); // Add clock to the top center
        frame.add(dateLabel, BorderLayout.SOUTH);  // Add date to the bottom center

        // Make the window fill the whole screen in full-screen mode
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // If the window is closed, the program will stop
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a KeyBinding to close the app when the Esc key is pressed
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
             .put(KeyStroke.getKeyStroke("ESCAPE"), "closeWindow");
        
        // What happens when 'Esc' is pressed
        frame.getRootPane().getActionMap().put("closeWindow", new AbstractAction()
        {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0); // Exit the program
            }
        }
        );

        // Make the window visible
        frame.setVisible(true);

        // Create a timer to update both the time and the date every second (1000 milliseconds = 1 second)
        Timer timer = new Timer(1000, e ->
        {
            String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            // Set the time in the label to show it on the screen
            clockLabel.setText(currentTime);

            // Get the current date and format it as "EEEE, MMMM dd, yyyy" (e.g., Tuesday, September 24, 2024)
            String currentDate = new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(new Date());
            // Set the date in the label to show it on the screen
            dateLabel.setText(currentDate);
        }
        );

        // Start the timer, so it keeps updating the time and date
        timer.start();
    }
}
