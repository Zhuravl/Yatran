package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Settings;
import ua.com.yatran.enums.Keyboard;
import ua.com.yatran.enums.Layout;
import ua.com.yatran.helpers.GameContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsPanel extends JPanel {

    private JPanel contentPane;
    private JButton continueButton, exitButton;
    private JLabel settingsLabel, settingsHintLabel, keyboardLabel, layoutLabel, levelLabel, soundLabel;
    private JComboBox keyboardBox, layoutBox, levelBox, soundBox;

    public SettingsPanel(JPanel contentPane) {
        this.contentPane = contentPane;
        this.setLayout(null);
        registerKeyboardAction(
                e -> continueButton.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

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

        settingsLabel = new JLabel(rb.getString("settings_label"));
        settingsLabel.setFont(Constants.Common.FONT_MAIN);
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setBounds(0, Constants.Common.ELEMENTS_CLEARANCE * 5, Constants.Common.MAIN_WINDOW_WIDTH, 40);
        this.add(settingsLabel);

        settingsHintLabel = new JLabel(rb.getString("settings_hint"));
        settingsHintLabel.setFont(Constants.Common.FONT_HINT);
        settingsHintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsHintLabel.setBounds(0, settingsLabel.getY() + settingsLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_WIDTH, 30);
        this.add(settingsHintLabel);

        keyboardLabel = new JLabel(rb.getString("keyboard_label"));
        keyboardLabel.setFont(Constants.Common.FONT_MAIN);
        keyboardLabel.setBounds((Constants.Common.MAIN_WINDOW_WIDTH / 2) - (Constants.Common.BUTTON_WIDTH / 2), settingsHintLabel.getY() + settingsHintLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(keyboardLabel);

        keyboardBox = new JComboBox(Keyboard.getAllKeyboardNames());
        keyboardBox.setSelectedItem(Keyboard.getDefault(locale).getName());
        keyboardBox.setBounds(keyboardLabel.getX() + keyboardLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, keyboardLabel.getY(), Constants.Common.BUTTON_WIDTH / 2, keyboardLabel.getHeight());
        keyboardBox.addActionListener(e -> {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(Layout.getAllLayoutNamesForType(Keyboard.getByName((String) keyboardBox.getSelectedItem())));
            layoutBox.setModel(model);
            layoutBox.setSelectedItem(Layout.getDefault(locale, Keyboard.getByName((String) keyboardBox.getSelectedItem())).getName());
        });
        this.add(keyboardBox);

        layoutLabel = new JLabel(rb.getString("layout_label"));
        layoutLabel.setFont(Constants.Common.FONT_MAIN);
        layoutLabel.setBounds((Constants.Common.MAIN_WINDOW_WIDTH / 2) - (Constants.Common.BUTTON_WIDTH / 2), keyboardLabel.getY() + keyboardLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(layoutLabel);

        layoutBox = new JComboBox(Layout.getAllLayoutNamesForType(Keyboard.getByName((String) keyboardBox.getSelectedItem())));
        layoutBox.setSelectedItem(Layout.getDefault(locale, Keyboard.getByName((String) keyboardBox.getSelectedItem())).getName());
        layoutBox.setBounds(layoutLabel.getX() + layoutLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, layoutLabel.getY(), Constants.Common.BUTTON_WIDTH / 2, layoutLabel.getHeight());
        layoutBox.addActionListener(e -> {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(GameContext.getAvailableLevels(Layout.getByName(Keyboard.getByName((String) keyboardBox.getSelectedItem()), (String) layoutBox.getSelectedItem()).getKeys()));
            levelBox.setModel(model);
        });
        this.add(layoutBox);

        levelLabel = new JLabel(rb.getString("level_label"));
        levelLabel.setFont(Constants.Common.FONT_MAIN);
        levelLabel.setBounds(layoutLabel.getX(), layoutLabel.getY() + layoutLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(levelLabel);

        levelBox = new JComboBox(GameContext.getAvailableLevels(Layout.getByName(Keyboard.getByName((String) keyboardBox.getSelectedItem()), (String) layoutBox.getSelectedItem()).getKeys()));
        levelBox.setBounds(levelLabel.getX() + levelLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, levelLabel.getY(), Constants.Common.BUTTON_WIDTH / 2, levelLabel.getHeight());
        this.add(levelBox);

        soundLabel = new JLabel(rb.getString("sound_label"));
        soundLabel.setFont(Constants.Common.FONT_MAIN);
        soundLabel.setBounds(levelLabel.getX(), levelLabel.getY() + levelLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(soundLabel);

        soundBox = new JComboBox(GameContext.getSoundOptions());
        soundBox.setBounds(soundLabel.getX() + soundLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, soundLabel.getY(), Constants.Common.BUTTON_WIDTH / 2, soundLabel.getHeight());
        this.add(soundBox);

        exitButton = new JButton(rb.getString("exit_button"));
        exitButton.setFont(Constants.Common.FONT_MAIN);
        exitButton.setBounds(Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_HEIGHT - Constants.Common.BUTTON_HEIGHT - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT);
        exitButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            System.exit(0);
        }));
        this.add(exitButton);

        continueButton = new JButton(rb.getString("start_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(Constants.Common.MAIN_WINDOW_WIDTH - Constants.Common.BUTTON_WIDTH - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_HEIGHT - Constants.Common.BUTTON_HEIGHT - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            Settings settings = new Settings();
            settings.setLayout(Layout.getByName(Keyboard.getByName((String) keyboardBox.getSelectedItem()), (String) layoutBox.getSelectedItem()));
            settings.setLevel(levelBox.getSelectedIndex() + 1);
            settings.setScore(0);
            settings.setMistakes(0);
            settings.setSoundOn(rb.getString("sound_on_label").equals(soundBox.getSelectedItem()));
            GameContext.setSettings(settings);
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.BRIEFING);
        }));
        this.add(continueButton);
    }
}
