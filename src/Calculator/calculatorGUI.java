package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatorGUI
{
    private JFrame frame; // This is the window where the calculator will appear
    private JTextField display; // This is where the calculator shows what you're typing
    private String currentInput = ""; // This holds what you're typing

    public static void main(String[] args)
    {
        // Start the program
        EventQueue.invokeLater(() ->
        {
            try
            {
                // Create and show the calculator window
                calculatorGUI window = new calculatorGUI();
                window.frame.setVisible(true);
            }
            
            catch (Exception e)
            {
                e.printStackTrace(); // If something goes wrong, print the error
            }
        });
    }

    public calculatorGUI()
    {
        initialize(); // Set everything up for the calculator
    }

    private void initialize()
    {
        // Make a new window for the calculator
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setBounds(100, 100, 300, 450); // Size and position of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close when you click the X button
        frame.getContentPane().setLayout(new BorderLayout()); // Layout for the window

        // This is the place where numbers and results will be shown
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 20)); // Set text size
        display.setEditable(false); // You can't type directly in this area
        display.setHorizontalAlignment(SwingConstants.RIGHT); // Align text to the right
        frame.getContentPane().add(display, BorderLayout.NORTH);

        // Create a space for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5)); // Create a grid of buttons (5 rows, 4 columns)
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // The buttons you will see in the calculator
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*","1", "2", "3", "-","0", ".", "=", "+","C", "CE","(", ")",};

        // Create buttons and add them to the panel (grid of buttons)
        for (String text : buttons)
        {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font size for each button
            button.addActionListener(new ButtonClickListener()); // When you click, do something
            panel.add(button); // Add button to the grid
        }
    }

    // What happens when you press a button
    private class ButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton source = (JButton) e.getSource(); // Get the button you clicked
            String command = source.getText(); // Get the text of the button

            // If the button is a number or a period, add it to the current input
            if ("0123456789.".contains(command))
            {
                currentInput += command;
                display.setText(currentInput); // Update what you see on the screen
            }
            // If the button is an operation (+, -, *, /), add it to the input
            else if ("+-*/".contains(command))
            {
                currentInput += " " + command + " "; // Add space for better readability
                display.setText(currentInput);
            }
            // If the button is "(", add it to the input
            else if ("(".equals(command))
            {
                currentInput += "(";
                display.setText(currentInput);
            }
            // If the button is ")", add it to the input
            else if (")".equals(command))
            {
                currentInput += ")";
                display.setText(currentInput);
            }
            // If the button is "=", calculate and show the result
            else if ("=".equals(command))
            {
                try
                {
                    currentInput = evaluateExpression(currentInput); // Try to calculate
                    display.setText(currentInput); // Show the result
                }
                catch (Exception ex)
                {
                    display.setText("Error"); // If something goes wrong, show "Error"
                    currentInput = ""; // Clear the input
                }
            }
            // If the button is "C", clear everything
            else if ("C".equals(command))
            {
                currentInput = "";
                display.setText(currentInput);
            }
            // If the button is "CE", remove the last character typed
            else if ("CE".equals(command))
            {
                if (currentInput.length() > 0)
                {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput);
                }
            }
        }

        // This function does the math for the calculator
        private String evaluateExpression(String expr)
        {
            try
            {
                // Split the input into numbers and operators
                String[] tokens = expr.split(" ");
                double num1 = Double.parseDouble(tokens[0]); // First number
                String operator = tokens[1]; // The operator (+, -, *, /)
                double num2 = Double.parseDouble(tokens[2]); // Second number

                // Do the math based on the operator
                double result = 0;
                switch (operator)
                {
                    case "+":
                        result = num1 + num2; // Add numbers
                        break;
                    case "-":
                        result = num1 - num2; // Subtract numbers
                        break;
                    case "*":
                        result = num1 * num2; // Multiply numbers
                        break;
                    case "/":
                        if (num2 != 0)
                        {
                            result = num1 / num2; // Divide numbers
                        }       
                        else
                        {
                            throw new ArithmeticException("Division by zero");
                        }
                        break;
                }
                return String.valueOf(result); // Return the result as a string
            }
            
            catch (Exception e)
            {
                return "Error"; // If something goes wrong, return "Error"
            }
        }
    }
}
