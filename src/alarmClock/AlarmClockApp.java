package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class AlarmClockApp
{

    private JFrame frame;
    private JTextField hourField, minuteField, secondField;
    private JLabel timeLabel, statusLabel;
    private JButton setButton;
    private Timer timer;
    private int alarmHour, alarmMinute, alarmSecond;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                AlarmClockApp window = new AlarmClockApp();
                window.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public AlarmClockApp()
    {
        initialize();
    }

    private void initialize()
    {
        frame = new JFrame("Simple Alarm Clock");
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout());

        // Input fields for setting the alarm time
        JLabel hourLabel = new JLabel("Hour:");
        panel.add(hourLabel);

        hourField = new JTextField(2);
        panel.add(hourField);

        JLabel minuteLabel = new JLabel("Minute:");
        panel.add(minuteLabel);

        minuteField = new JTextField(2);
        panel.add(minuteField);

        JLabel secondLabel = new JLabel("Second:");
        panel.add(secondLabel);

        secondField = new JTextField(2);
        panel.add(secondField);

        // Set alarm button
        setButton = new JButton("Set Alarm");
        panel.add(setButton);
        setButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setAlarm();
            }
        });

        // Label to show current time
        timeLabel = new JLabel();
        frame.getContentPane().add(timeLabel, BorderLayout.NORTH);

        // Label for alarm status
        statusLabel = new JLabel("Alarm Status: Not Set");
        frame.getContentPane().add(statusLabel, BorderLayout.SOUTH);

        // Start a timer to update the current time every second
        startClock();
    }

    private void startClock()
    {
        timer = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Get the current time
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                // Display the current time
                String currentTime = String.format("%02d:%02d:%02d", hour, minute, second);
                timeLabel.setText("Current Time: " + currentTime);

                // Check if the current time matches the alarm time
                if (hour == alarmHour && minute == alarmMinute && second == alarmSecond)
                {
                    triggerAlarm();
                }
            }
        });
        timer.start();
    }

    private void setAlarm()
    {
        try
        {
            // Get the alarm time from the input fields
            alarmHour = Integer.parseInt(hourField.getText());
            alarmMinute = Integer.parseInt(minuteField.getText());
            alarmSecond = Integer.parseInt(secondField.getText());

            // Update status label
            statusLabel.setText("Alarm Set for: " + String.format("%02d:%02d:%02d", alarmHour, alarmMinute, alarmSecond));

        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter valid numbers for hour, minute, and second.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void triggerAlarm()
    {
        // When the alarm time is reached, show a message and play a sound
        JOptionPane.showMessageDialog(frame, "ALARM! Time to wake up!", "Alarm", JOptionPane.INFORMATION_MESSAGE);
        Toolkit.getDefaultToolkit().beep(); // Play a simple beep sound
    }
}
