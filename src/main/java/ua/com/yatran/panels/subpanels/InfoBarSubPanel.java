package ua.com.yatran.panels.subpanels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;

import javax.swing.*;
import java.io.Serial;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class InfoBarSubPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel keyboardLabel, levelLabel, scoresLabel, mistakesLabel;
    private JTextField keyboardField, levelField, scoresField;
    private JProgressBar mistakesBar;

    public InfoBarSubPanel() {
        this.setLayout(null);

        GUI();
    }

    public void setLevelField(int level) {
        this.levelField.setText(String.valueOf(level));
    }

    public void setScoresField(int scores) {
        this.scoresField.setText(String.valueOf(scores));
    }

    public void setMistakesBar(int mistakes) {
        this.mistakesBar.setValue(mistakes);
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI() {
        keyboardField.setText(GameContext.getSettings().getLanguage().getKeyboardName());
        levelField.setText(String.valueOf(GameContext.getSettings().getLevel()));
        scoresField.setText(String.valueOf(new Random().nextInt(200)));
        mistakesBar.setValue(new Random().nextInt(Constants.Common.MISTAKES_MAX));
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);

        keyboardLabel = new JLabel(rb.getString("keyboard_label") + ":");
        keyboardLabel.setFont(Constants.Common.FONT_MAIN);
        keyboardLabel.setBounds(20, 20, 80, 50);
        this.add(keyboardLabel);

        keyboardField = new JTextField();
        keyboardField.setBounds(keyboardLabel.getX() + keyboardLabel.getWidth() + 20, keyboardLabel.getY(), 70, keyboardLabel.getHeight());
        keyboardField.setEnabled(false);
        this.add(keyboardField);

        levelLabel = new JLabel(rb.getString("level_label") + ":");
        levelLabel.setFont(Constants.Common.FONT_MAIN);
        levelLabel.setBounds(keyboardField.getX() + keyboardField.getWidth() + 20, keyboardField.getY(), 70, keyboardField.getHeight());
        this.add(levelLabel);

        levelField = new JTextField();
        levelField.setBounds(levelLabel.getX() + levelLabel.getWidth() + 20, levelLabel.getY(), 70, levelLabel.getHeight());
        levelField.setEnabled(false);
        this.add(levelField);

        scoresLabel = new JLabel(rb.getString("scores_label") + ":");
        scoresLabel.setFont(Constants.Common.FONT_MAIN);
        scoresLabel.setBounds(levelField.getX() + levelField.getWidth() + 20, levelField.getY(), 70, levelField.getHeight());
        this.add(scoresLabel);

        scoresField = new JTextField();
        scoresField.setBounds(scoresLabel.getX() + scoresLabel.getWidth() + 20, scoresLabel.getY(), 70, scoresLabel.getHeight());
        scoresField.setEnabled(false);
        this.add(scoresField);

        mistakesLabel = new JLabel(rb.getString("mistakes_label") + ":");
        mistakesLabel.setFont(Constants.Common.FONT_MAIN);
        mistakesLabel.setBounds(scoresField.getX() + scoresField.getWidth() + 20, scoresField.getY(), 70, scoresField.getHeight());
        this.add(mistakesLabel);

        mistakesBar = new JProgressBar(Constants.Common.MISTAKES_MIN, Constants.Common.MISTAKES_MAX);
        mistakesBar.setBounds(mistakesLabel.getX() + mistakesLabel.getWidth() + 20, mistakesLabel.getY(), 70, mistakesLabel.getHeight());
        mistakesBar.setStringPainted(true);
        this.add(mistakesBar);
    }
}
