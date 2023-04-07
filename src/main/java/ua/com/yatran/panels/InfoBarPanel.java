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
        keyboardLabel.setFont(Constants.Common.FONT_HINT);
        keyboardLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        keyboardLabel.setBounds(Constants.Common.ELEMENTS_CLEARANCE * 2, Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(keyboardLabel);

        keyboardField = new JTextField();
        keyboardField.setBounds(keyboardLabel.getX() + keyboardLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, keyboardLabel.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        keyboardField.setEnabled(false);
        this.add(keyboardField);

        levelLabel = new JLabel(rb.getString("level_label") + ":");
        levelLabel.setFont(Constants.Common.FONT_HINT);
        levelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        levelLabel.setBounds(keyboardField.getX() + keyboardField.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, keyboardLabel.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(levelLabel);

        levelField = new JTextField();
        levelField.setBounds(levelLabel.getX() + levelLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, levelLabel.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        levelField.setEnabled(false);
        this.add(levelField);

        scoresLabel = new JLabel(rb.getString("score_label") + ":");
        scoresLabel.setFont(Constants.Common.FONT_HINT);
        scoresLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        scoresLabel.setBounds(levelField.getX() + levelField.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, levelField.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(scoresLabel);

        scoreField = new JTextField();
        scoreField.setBounds(scoresLabel.getX() + scoresLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, scoresLabel.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        scoreField.setEnabled(false);
        this.add(scoreField);

        mistakesLabel = new JLabel(rb.getString("mistakes_label") + ":");
        mistakesLabel.setFont(Constants.Common.FONT_HINT);
        mistakesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        mistakesLabel.setBounds(scoreField.getX() + scoreField.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, scoreField.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        this.add(mistakesLabel);

        mistakesBar = new JProgressBar(MISTAKES_MIN, MISTAKES_MAX);
        mistakesBar.setBounds(mistakesLabel.getX() + mistakesLabel.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, mistakesLabel.getY(), Constants.Common.BUTTON_WIDTH / 4, Constants.Common.BUTTON_HEIGHT / 2);
        mistakesBar.setStringPainted(true);
        this.add(mistakesBar);
    }
}
