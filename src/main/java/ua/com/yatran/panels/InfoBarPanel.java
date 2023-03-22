package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.helpers.GameContext;

import javax.swing.*;
import java.io.Serial;
import java.util.Locale;
import java.util.ResourceBundle;

public class InfoBarPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int MISTAKES_MIN = 0;
    private static final int MISTAKES_MAX = 10;

    private JLabel keyboardLabel, levelLabel, scoresLabel, mistakesLabel;
    private JTextField keyboardField, levelField, scoreField;
    private JProgressBar mistakesBar;

    public InfoBarPanel() {
        this.setLayout(null);

        GUI();
    }

    public void setScoreField(int score) {
        this.scoreField.setText(String.valueOf(score));
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
        scoreField.setText(String.valueOf(GameContext.getSettings().getScore()));
        mistakesBar.setMaximum(GameContext.getMaxMistakes());
        mistakesBar.setValue(GameContext.getSettings().getMistakes());
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

        scoresLabel = new JLabel(rb.getString("score_label") + ":");
        scoresLabel.setFont(Constants.Common.FONT_MAIN);
        scoresLabel.setBounds(levelField.getX() + levelField.getWidth() + 20, levelField.getY(), 70, levelField.getHeight());
        this.add(scoresLabel);

        scoreField = new JTextField();
        scoreField.setBounds(scoresLabel.getX() + scoresLabel.getWidth() + 20, scoresLabel.getY(), 70, scoresLabel.getHeight());
        scoreField.setEnabled(false);
        this.add(scoreField);

        mistakesLabel = new JLabel(rb.getString("mistakes_label") + ":");
        mistakesLabel.setFont(Constants.Common.FONT_MAIN);
        mistakesLabel.setBounds(scoreField.getX() + scoreField.getWidth() + 20, scoreField.getY(), 70, scoreField.getHeight());
        this.add(mistakesLabel);

        mistakesBar = new JProgressBar(MISTAKES_MIN, MISTAKES_MAX);
        mistakesBar.setBounds(mistakesLabel.getX() + mistakesLabel.getWidth() + 20, mistakesLabel.getY(), 70, mistakesLabel.getHeight());
        mistakesBar.setStringPainted(true);
        this.add(mistakesBar);
    }
}
