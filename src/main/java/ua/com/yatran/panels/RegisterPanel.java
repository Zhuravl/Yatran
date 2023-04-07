package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.JTextFieldLimit;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.helpers.GameContext;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterPanel extends JPanel {

    private JPanel contentPane;
    private JButton button;
    private JLabel usernameLabel, usernameHintLabel;
    private JTextField usernameField;

    public RegisterPanel(JPanel contentPane) {
        this.contentPane = contentPane;
        this.setLayout(null);

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);

        usernameLabel = new JLabel(rb.getString("username_label"));
        usernameLabel.setFont(Constants.Common.FONT_MAIN);
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBounds(0, Constants.Common.ELEMENTS_CLEARANCE * 5, Constants.Common.MAIN_WINDOW_WIDTH, 40);
        this.add(usernameLabel);

        usernameHintLabel = new JLabel(String.format(rb.getString("username_hint"), Constants.Common.USERNAME_MIN, Constants.Common.USERNAME_MAX));
        usernameHintLabel.setFont(Constants.Common.FONT_HINT);
        usernameHintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameHintLabel.setBounds(0, usernameLabel.getY() + usernameLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_WIDTH, 30);
        this.add(usernameHintLabel);

        usernameField = new JTextField();
        usernameField.setBounds(((Constants.Common.MAIN_WINDOW_WIDTH / 2) - (Constants.Common.BUTTON_WIDTH / 2)), usernameHintLabel.getY() + usernameHintLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT / 2);
        usernameField.setFont(Constants.Common.FONT_MAIN);
        usernameField.addActionListener(e -> button.doClick());
        usernameField.setDocument(new JTextFieldLimit(Constants.Common.USERNAME_MAX));
        usernameField.setText(GameContext.getRecord() != null ? GameContext.getRecord().getUsername() : "");
        this.add(usernameField);

        button = new JButton(rb.getString("continue_button"));
        button.setFont(Constants.Common.FONT_MAIN);
        button.setBounds(Constants.Common.MAIN_WINDOW_WIDTH - Constants.Common.BUTTON_WIDTH - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_HEIGHT - Constants.Common.BUTTON_HEIGHT - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
        button.addActionListener(e -> EventQueue.invokeLater(() -> {
            if (usernameField.getText() != null && usernameField.getText().length() >= Constants.Common.USERNAME_MIN) {
                GameContext.setRecord(new RankingRecord(usernameField.getText()));
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, Constants.Screen.SETTINGS);
            }
        }));
        this.add(button);
    }
}
