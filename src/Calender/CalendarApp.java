package Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarApp
{

    private JFrame frame;
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private JButton[] dayButtons;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;

    private int selectedYear;
    private int selectedMonth;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                CalendarApp window = new CalendarApp();
                window.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public CalendarApp()
    {
        initialize();
    }

    private void initialize()
    {
        frame = new JFrame("Simple Calendar");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Title label with month and year
        JPanel headerPanel = new JPanel();
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new FlowLayout());

        monthLabel = new JLabel("Month: ");
        headerPanel.add(monthLabel);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthComboBox = new JComboBox<>(months);
        headerPanel.add(monthComboBox);

        // Create a dropdown to select year
        String[] years = new String[101];
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        
        for (int i = 0; i < 101; i++)
        {
            years[i] = String.valueOf(currentYear - 50 + i); // Years from 50 years before to 50 years after
        }
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setSelectedItem(String.valueOf(currentYear));
        headerPanel.add(yearComboBox);

        // Add listeners for month and year change
        monthComboBox.addActionListener(e -> updateCalendar());
        yearComboBox.addActionListener(e -> updateCalendar());

        // Panel for displaying the calendar
        calendarPanel = new JPanel();
        frame.getContentPane().add(calendarPanel, BorderLayout.CENTER);
        calendarPanel.setLayout(new GridLayout(7, 7)); // 7 rows and 7 columns (including the day names row)

        // Add the days of the week as the first row
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek)
        {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            calendarPanel.add(label);
        }

        dayButtons = new JButton[42]; // A month can have a maximum of 42 days (6 weeks)

        // Create buttons for each day of the month
        for (int i = 0; i < 42; i++)
        {
            dayButtons[i] = new JButton("");
            dayButtons[i].setEnabled(false);
            calendarPanel.add(dayButtons[i]);
        }

        // Set the current month
        updateCalendar();
    }

    // This method updates the calendar based on the selected month and year
    private void updateCalendar()
    {
        selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
        selectedMonth = monthComboBox.getSelectedIndex();

        // Set the label for the selected month
        monthLabel.setText("Month: " + monthComboBox.getSelectedItem() + " " + selectedYear);

        // Get the first day of the month and number of days in the month
        Calendar calendar = new GregorianCalendar(selectedYear, selectedMonth, 1);
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK); // Day of the week for 1st of the month
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Clear previous month data
        for (int i = 0; i < 42; i++)
        {
            dayButtons[i].setText(""); // Reset button text
            dayButtons[i].setEnabled(false); // Disable the button
        }

        // Fill the calendar grid with day numbers
        int dayOfWeek = firstDayOfMonth - 1; // Adjust for 0-based index
        for (int day = 1; day <= numberOfDaysInMonth; day++)
        {
            dayButtons[dayOfWeek].setText(String.valueOf(day));
            dayButtons[dayOfWeek].setEnabled(true);
            dayButtons[dayOfWeek].setBackground(Color.white);
            dayButtons[dayOfWeek].addActionListener(new DayButtonListener(day)); // Add listener for each day
            dayOfWeek++;
        }
    }

    // Action listener for the day buttons
    private class DayButtonListener implements ActionListener
    {
        private int day;

        public DayButtonListener(int day)
        {
            this.day = day;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(frame, "You selected " + day + " " + monthComboBox.getSelectedItem() + " " + selectedYear);
        }
    }
}
