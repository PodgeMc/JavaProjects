package Calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatorGUI
{

    private JFrame frame;
    private JTextField display;
    private String currentInput = "";

    public static void main(String[] args)
    {
        // Run the program
        EventQueue.invokeLater(() ->
        {
            try
            {
                calculatorGUI window = new calculatorGUI();
                window.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public calculatorGUI()
    {
        initialize();
    }

    private void initialize()
    {
        // Make a new window for the calculator
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setBounds(100, 100, 300, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Make a space at the top to show what we are typing
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setEditable(false); // You can't type here directly
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.getContentPane().add(display, BorderLayout.NORTH);

        // Create a grid for the calculator buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // All the buttons for the calculator
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "(", ")", "C", "CE"
        };

        // Create buttons and add them to the calculator
        for (String text : buttons)
        {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
    }

    // What happens when you click a button
    private class ButtonClickListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton source = (JButton) e.getSource(); // Find out which button was clicked
            String command = source.getText(); // Get the text on the button

            // If the button is a number or period, add it to what we typed
            if ("0123456789.".contains(command))
            {
                currentInput += command;
                display.setText(currentInput);
            }
            // If the button is an operation (+, -, *, /), add it to the input
            else if ("+-*/".contains(command))
            {
                currentInput += " " + command + " ";
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
            // If the button is "=", calculate the result
            else if ("=".equals(command))
            {
                try
                {
                    currentInput = evaluateExpression(currentInput);
                    display.setText(currentInput);
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

        // This is how we do the math
        private String evaluateExpression(String expr)
        {
            try
            {
                // Break the input into numbers and operations
                String[] tokens = expr.split(" ");
                double num1 = Double.parseDouble(tokens[0]);
                String operator = tokens[1];
                double num2 = Double.parseDouble(tokens[2]);

                // Do the math based on the operation
                double result = 0;
                switch (operator)
                {
                    case "+":
                        result = num1 + num2; // Add
                        break;
                    case "-":
                        result = num1 - num2; // Subtract
                        break;
                    case "*":
                        result = num1 * num2; // Multiply
                        break;
                    case "/":
                        if (num2 != 0)
                        {
                            result = num1 / num2; // Divide
                        }
                        else
                        {
                            throw new ArithmeticException("Division by zero");
                        }
                        break;
                }
                return String.valueOf(result); // Show the result
            }
            catch (Exception e)
            {
                return "Error"; // If something goes wrong, show "Error"
            }
        }
    }
}
