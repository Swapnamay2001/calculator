import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    // Create frame
    private JFrame frame;
    // Create text field for displaying results
    private JTextField textField;
    // Store the operands and result
    private double num1, num2, result;
    // Store the operator
    private String operator;

    // Constructor
    public CalculatorGUI() {
        // Set up the frame
        frame = new JFrame("Simple Calculator");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create the text field
        textField = new JTextField();
        textField.setBounds(50, 50, 300, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(textField);

        // Create the number buttons and operator buttons
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Positioning for buttons
        int x = 50, y = 120, width = 60, height = 60;
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons[i]);
            button.setBounds(x, y, width, height);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            frame.add(button);

            // Update position for next button
            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 50;
                y += 70;
            }
        }

        // Show the frame
        frame.setVisible(true);
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) == '=') {
            // Calculate the result based on the operator
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
            operator = "";
        } else if (command.charAt(0) == 'C') {
            // Clear the display
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if ("0123456789.".indexOf(command) >= 0) {
            // Handle digits and decimal point
            textField.setText(textField.getText() + command);
        } else {
            // Handle operator
            if (operator.isEmpty()) {
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
            operator = command;
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
