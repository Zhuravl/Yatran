package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Settings;
import ua.com.yatran.enums.Language;
import ua.com.yatran.helpers.GameContext;

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
     * Sets the defined level
     *
     * @param level level to set as the current
     */
    public void selectLevel(int level) {
        levelBox.setSelectedItem(String.valueOf(level));
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
            Settings settings = new Settings();
            settings.setLanguage(Language.getByKeyboardName((String) keyboardBox.getSelectedItem()));
            settings.setLevel(levelBox.getSelectedIndex() + 1);
            settings.setScore(0);
            settings.setMistakes(0);
            settings.setSoundOn(rb.getString("sound_on_label").equals(soundBox.getSelectedItem()));
            GameContext.setSettings(settings);
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.BRIEFING);
        }));
        this.add(startButton);

        keyboardLabel = new JLabel(rb.getString("keyboard_label"));
        keyboardLabel.setFont(Constants.Common.FONT_MAIN);
        keyboardLabel.setBounds(startButton.getX(), startButton.getY() + startButton.getHeight() + 20, 150, 50);
        this.add(keyboardLabel);

        keyboardBox = new JComboBox(GameContext.getAvailableKeyboards());
        keyboardBox.setSelectedItem(Language.getByLocale(locale).getKeyboardName());
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
