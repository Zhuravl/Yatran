package ua.com.yatran.panels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsPanel extends JPanel {

    private JPanel contentPane;
    private JButton startButton, exitButton;
    private JLabel keyboardLabel, levelLabel, soundLabel;
    private JComboBox keyboardBox, levelBox, soundBox;

    public SettingsPanel(JPanel contentPane) {
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

        startButton = new JButton(rb.getString("start_button"));
        startButton.setFont(Constants.Common.FONT_MAIN);
        startButton.setBounds(10, 10, 400, 100);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            GameContext.getSettings().setLanguage(Language.getByKeyboardName((String) keyboardBox.getSelectedItem()));
            GameContext.getSettings().setLevel(levelBox.getSelectedIndex() + 1);
            GameContext.getSettings().setSoundOn(rb.getString("sound_on_label").equals(soundBox.getSelectedItem()));
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.BRIEFING);
        }));
        this.add(startButton);

        keyboardLabel = new JLabel(rb.getString("keyboard_label"));
        keyboardLabel.setFont(Constants.Common.FONT_MAIN);
        keyboardLabel.setBounds(startButton.getX(), startButton.getY() + startButton.getHeight() + 20, 150, 50);
        this.add(keyboardLabel);

        keyboardBox = new JComboBox(GameContext.getAvailableKeyboards());
        keyboardBox.setBounds(keyboardLabel.getX() + keyboardLabel.getWidth() + 20, keyboardLabel.getY(), 150, keyboardLabel.getHeight());
        keyboardBox.addActionListener(e -> {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(GameContext.getAvailableLevels(Language.getByKeyboardName((String) keyboardBox.getSelectedItem())));
            levelBox.setModel(model);
        });
        this.add(keyboardBox);

        levelLabel = new JLabel(rb.getString("level_label"));
        levelLabel.setFont(Constants.Common.FONT_MAIN);
        levelLabel.setBounds(keyboardLabel.getX(), keyboardLabel.getY() + keyboardLabel.getHeight() + 20, 150, 50);
        this.add(levelLabel);

        levelBox = new JComboBox(GameContext.getAvailableLevels(Language.getByKeyboardName((String) keyboardBox.getSelectedItem())));
        levelBox.setBounds(levelLabel.getX() + levelLabel.getWidth() + 20, levelLabel.getY(), 150, levelLabel.getHeight());
        this.add(levelBox);

        soundLabel = new JLabel(rb.getString("sound_label"));
        soundLabel.setFont(Constants.Common.FONT_MAIN);
        soundLabel.setBounds(levelLabel.getX(), levelLabel.getY() + levelLabel.getHeight() + 20, 150, 50);
        this.add(soundLabel);

        soundBox = new JComboBox(GameContext.getSoundOptions());
        soundBox.setBounds(soundLabel.getX() + soundLabel.getWidth() + 20, soundLabel.getY(), 150, soundLabel.getHeight());
        this.add(soundBox);

        exitButton = new JButton(rb.getString("exit_button"));
        exitButton.setFont(Constants.Common.FONT_MAIN);
        exitButton.setBounds(soundLabel.getX(), soundLabel.getY() + soundLabel.getHeight() + 20, 400, 100);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            System.exit(0);
        }));
        this.add(exitButton);
    }
}
