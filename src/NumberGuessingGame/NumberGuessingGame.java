package NumberGuessingGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NumberGuessingGame
{

    private JFrame frame;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private int targetNumber;
    private int attempts;
    private final int lowerBound = 1;
    private final int upperBound = 100;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                NumberGuessingGame window = new NumberGuessingGame();
                window.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public NumberGuessingGame()
    {
        initialize();
    }

    private void initialize()
    {
        // Set up the frame
        frame = new JFrame("Number Guessing Game");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Create panel to hold components
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout());

        // Create and add components
        JLabel instructionLabel = new JLabel("Guess the number between " + lowerBound + " and " + upperBound + ":");
        panel.add(instructionLabel);

        guessField = new JTextField(5); // Text field for user input
        panel.add(guessField);

        guessButton = new JButton("Guess");
        panel.add(guessButton);

        messageLabel = new JLabel("");
        panel.add(messageLabel);

        // Generate random number
        Random random = new Random();
        targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;

        // Add button action listener
        guessButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                handleGuess();
            }
        });
    }

    private void handleGuess()
    {
        try
        {
            // Get the guess from the text field
            String input = guessField.getText();
            int userGuess = Integer.parseInt(input);

            // Check if the guess is within the valid range
            if (userGuess < lowerBound || userGuess > upperBound)
            {
                throw new IllegalArgumentException("Guess must be between " + lowerBound + " and " + upperBound);
            }

            attempts++;

            // Compare guess to the target number
            if (userGuess < targetNumber)
            {
                messageLabel.setText("Too low! Try again.");
            }
            else if (userGuess > targetNumber)
            {
                messageLabel.setText("Too high! Try again.");
            }
            else
            {
                messageLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
            }

        }
        catch (NumberFormatException ex)
        {
            messageLabel.setText("Invalid input! Please enter a number.");
        }
        catch (IllegalArgumentException ex)
        {
            messageLabel.setText(ex.getMessage()); // Show range error
        }
    }
}
