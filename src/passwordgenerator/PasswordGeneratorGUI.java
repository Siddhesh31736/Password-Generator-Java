package passwordgenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

public class PasswordGeneratorGUI extends JFrame {

    JTextField passwordField;
    JSpinner lengthSpinner;
    JCheckBox upperCase, lowerCase, numbers, symbols;
    JButton generateBtn, copyBtn;

    public PasswordGeneratorGUI() {
        setTitle("Password Generator");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setFont(font);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 30, 1));
        lengthSpinner.setFont(font);

        upperCase = new JCheckBox("Include Uppercase", true);
        lowerCase = new JCheckBox("Include Lowercase", true);
        numbers = new JCheckBox("Include Numbers", true);
        symbols = new JCheckBox("Include Symbols", true);

        upperCase.setBackground(Color.WHITE);
        lowerCase.setBackground(Color.WHITE);
        numbers.setBackground(Color.WHITE);
        symbols.setBackground(Color.WHITE);

        upperCase.setFont(font);
        lowerCase.setFont(font);
        numbers.setFont(font);
        symbols.setFont(font);

        generateBtn = new JButton("Generate Password");
        copyBtn = new JButton("Copy Password");

        generateBtn.setFont(font);
        copyBtn.setFont(font);

        generateBtn.setBackground(new Color(0, 120, 215));
        generateBtn.setForeground(Color.BLACK);

        copyBtn.setBackground(new Color(40, 167, 69));
        copyBtn.setForeground(Color.BLACK);

        panel.add(new JLabel("Generated Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Password Length:"));
        panel.add(lengthSpinner);
        panel.add(upperCase);
        panel.add(lowerCase);
        panel.add(numbers);
        panel.add(symbols);
        panel.add(generateBtn);
        panel.add(copyBtn);

        add(panel);

        generateBtn.addActionListener(e -> generatePassword());

        copyBtn.addActionListener(e -> {
            String password = passwordField.getText();

            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(password), null);

            JOptionPane.showMessageDialog(this, "Password Copied!");
        });

        setVisible(true);
    }

    private void generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String sym = "!@#$%^&*";

        String chars = "";

        if (upperCase.isSelected()) chars += upper;
        if (lowerCase.isSelected()) chars += lower;
        if (numbers.isSelected()) chars += num;
        if (symbols.isSelected()) chars += sym;

        if (chars.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select at least one option!");
            return;
        }

        int length = (int) lengthSpinner.getValue();

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        passwordField.setText(password.toString());
    }

    public static void main(String[] args) throws Exception {
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new PasswordGeneratorGUI();
    }
}