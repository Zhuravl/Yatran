package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.helpers.GameContext;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.games.MovingFloorGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private InfoBarPanel infoBarPanel;
    private AbstractGamePanel gameSubPanel;
    private KeyboardPanel keyboardPanel;
    private String[] letters;
    private int currentLetterIndex;

    private RankingPanel rankingPanel;

    public GamePanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;
        this.setLayout(null);
        registerKeyboardAction(
                e -> {
                    checkKeyPressed(" ");
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_G, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data and starts the game
     */
    public void startGame() {
        setLetters(GameContext.getRandomLettersForLevel());
        setCurrentLetterIndex(0);
        infoBarPanel.refreshGUI();
        keyboardPanel.refreshGUI();
        GameContext.logLevelStartTime();
        setGameSubPanel(new MovingFloorGamePanel(this, letters));
    }

    /**
     * Starts the next game level or triggers game stopping
     */
    public void nextLevel() {
        int nextLevel = GameContext.getSettings().getLevel() + 1;
        int lastLevel = GameContext.getMaxLevel();
        if (lastLevel >= nextLevel) {
            //The next level is available - continue the game
            GameContext.getSettings().setLevel(nextLevel);
            startGame();
        } else {
            //It was the last level - stop the game
            stopGame();
        }
    }

    /**
     * Stops the game, saves the results and switches to the next frame
     */
    public void stopGame() {
        GameContext.getRecord().setScore(GameContext.getSettings().getScore());
        GameContext.getRecord().setLevel(GameContext.getSettings().getLevel());
        GameContext.getRecord().setSpeed(GameContext.getTypingSpeed());
        GameContext.getRecord().setMistakes(GameContext.getSettings().getMistakes());
        GameContext.getRecord().setDate(Calendar.getInstance());
        GameContext.saveRecordToDisk();
        rankingPanel.refreshGUI();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, Constants.Screen.RANKING);
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        infoBarPanel = new InfoBarPanel();
        infoBarPanel.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 8);
        this.add(infoBarPanel);

        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setBounds(0, (Constants.Common.MAIN_WINDOW_HEIGHT / 2) + (Constants.Common.MAIN_WINDOW_HEIGHT / 8), Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 2);
        this.add(keyboardPanel);
    }

    /**
     * Sets the defined letters array for the panel
     *
     * @param letters letters array to set
     */
    public void setLetters(String[] letters) {
        this.letters = letters;
    }

    /**
     * Sets the defined index as current for the panel
     *
     * @param currentLetterIndex index to set as current
     */
    public void setCurrentLetterIndex(int currentLetterIndex) {
        this.currentLetterIndex = currentLetterIndex;
    }

    /**
     * Sets a Game Sub Panel to the panel
     *
     * @param gameSubPanel Game Sub Panel to set
     */
    private void setGameSubPanel(AbstractGamePanel gameSubPanel) {
        this.gameSubPanel = gameSubPanel;
        this.add(gameSubPanel);
    }

    /**
     * Checks if the correct key was pressed and adds score or mistake
     *
     * @param key the key pressed by the gamer
     */
    private void checkKeyPressed(String key) {
        if (letters[currentLetterIndex].equals(key)) {
            //The correct key was pressed - add scores
            gameSubPanel.hideBlock(currentLetterIndex);
            currentLetterIndex++;
            int score = GameContext.getSettings().getScore();
            GameContext.getSettings().setScore(score + GameContext.getSettings().getLevel());
            infoBarPanel.setScoreField(score);
            keyboardPanel.highlightButton(letters[currentLetterIndex]);
        } else {
            //The incorrect key was pressed - add mistakes
            int mistakes = GameContext.getSettings().getMistakes();
            mistakes++;
            GameContext.getSettings().setMistakes(mistakes);
            infoBarPanel.setMistakesBar(mistakes);
            if (mistakes >= GameContext.getMaxMistakes()) {
                //The player has made too many mistakes - stopping the game
                stopGame();
            }
        }
    }
}
