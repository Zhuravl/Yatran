package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.JTextFieldLimit;
import ua.com.yatran.entities.Record;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterPanel extends JPanel {

    private JPanel contentPane;
    private JButton button;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private Record record;

    public RegisterPanel(JPanel panel, Record record) {
        this.record = record;
        contentPane = panel;
        setLayout(null);

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.LOCALE_PREFIX, locale);

        usernameLabel = new JLabel(rb.getString("username_label"));
        usernameLabel.setFont(Constants.FONT_MAIN);
        usernameLabel.setBounds(120, 140, 400, 20);
        this.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(usernameLabel.getX(), usernameLabel.getY() + 30, 150, 20);
        usernameField.addActionListener(e -> button.doClick());
        usernameField.setDocument(new JTextFieldLimit(15));
        this.add(usernameField);

        button = new JButton(rb.getString("continue_button"));
        button.setFont(Constants.FONT_MAIN);
        button.setBounds(usernameField.getX(), usernameField.getY() + 30, 400, 100);
        button.setFocusPainted(false);
        button.addActionListener(e -> EventQueue.invokeLater(() -> {
            record.setUsername(usernameField.getText());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.next(contentPane);
        }));
        this.add(button);
    }
}
