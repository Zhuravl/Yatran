package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.enums.Language;
import ua.com.yatran.helpers.GameContext;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.games.FallingCeilingGamePanel;
import ua.com.yatran.panels.games.HuntingWhirlwindGamePanel;
import ua.com.yatran.panels.games.MovingFloorGamePanel;
import ua.com.yatran.panels.games.ScaryCloudGamePanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private InfoBarPanel infoBarPanel;
    private AbstractGamePanel gameSubPanel;
    private KeyboardPanel keyboardPanel;
    private String[] letters;
    private int currentLetterIndex;
    private Clip correctKeySound, wrongKeySound, roundWinSound, roundLoseSound;

    private RankingPanel rankingPanel;

    public GamePanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;
        this.setLayout(null);

        registerKeysBinding();

        try {
            AudioInputStream audioInputStreamCorrect = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sounds/keyCorrect.wav")));
            correctKeySound = AudioSystem.getClip();
            correctKeySound.open(audioInputStreamCorrect);

            AudioInputStream audioInputStreamWrong = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sounds/keyWrong.wav")));
            wrongKeySound = AudioSystem.getClip();
            wrongKeySound.open(audioInputStreamWrong);

            AudioInputStream audioInputStreamWin = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sounds/roundWin.wav")));
            roundWinSound = AudioSystem.getClip();
            roundWinSound.open(audioInputStreamWin);

            AudioInputStream audioInputStreamLose = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/sounds/roundLose.wav")));
            roundLoseSound = AudioSystem.getClip();
            roundLoseSound.open(audioInputStreamLose);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data and starts the game
     */
    public void startGame() {
        letters = GameContext.getRandomLettersForLevel();
        currentLetterIndex = 0;
        infoBarPanel.refreshGUI();
        keyboardPanel.refreshGUI();
        keyboardPanel.highlightButton(letters[currentLetterIndex]);
        setGameSubPanel(getNextGameSkin());
        gameSubPanel.startGame();
    }

    /**
     * Starts the next game, increasing the level (if it's available)
     */
    public void nextLevel() {
        int currentLevel = GameContext.getSettings().getLevel();
        int lastLevel = GameContext.getMaxLevel();
        if (lastLevel > currentLevel) {
            //The next level is available - switch to the next level
            GameContext.getSettings().setLevel(currentLevel + 1);
        }
        startGame();
    }

    /**
     * Stops the game, saves the results and switches to the next frame
     */
    public void stopGame() {
        gameSubPanel.stopGame();
        GameContext.getRecord().setScore(GameContext.getSettings().getScore());
        GameContext.getRecord().setLevel(GameContext.getSettings().getLevel());
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
        infoBarPanel.setName("infoBarPanel");
        infoBarPanel.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 8);
        this.add(infoBarPanel);

        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setName("keyboardPanel");
        keyboardPanel.setBounds(Constants.Common.ELEMENTS_CLEARANCE / 4, Constants.Common.MAIN_WINDOW_HEIGHT - (Constants.Common.MAIN_WINDOW_HEIGHT / 6) - 35, Constants.Common.MAIN_WINDOW_WIDTH - (Constants.Common.ELEMENTS_CLEARANCE / 2), Constants.Common.MAIN_WINDOW_HEIGHT / 5);
        this.add(keyboardPanel);
    }

    /**
     * Registers key bindings for all needed keys
     */
    private void registerKeysBinding() {
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                checkKeyPressed(actionEvent.getActionCommand());
            }
        };

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        for (Language language : GameContext.getAvailableLanguages()) {
            for (char key : GameContext.getAllKeys(language)) {
                KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
                if (im.get(keyStroke) == null) {
                    String name = "Key '" + key + "'";
                    im.put(keyStroke, name);
                    am.put(name, actionListener);
                }
            }
        }

        //Add the Space key separately as it doesn't present in the key list
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Key Space");
        am.put("Key Space", actionListener);

        //Add a hotkey for skipping the game (for testing purposes)
        Action skipListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                skipGame();
            }
        };

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.SHIFT_DOWN_MASK), "Key Shift + Escape");
        am.put("Key Shift + Escape", skipListener);
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
     * Plays the Round Lose sound if the sound preferences is set to on
     */
    public void playRoundLoseSound() {
        if (GameContext.getSettings().isSoundOn()) {
            roundLoseSound.setMicrosecondPosition(0);
            roundLoseSound.start();
        }
    }

    /**
     * Checks if the correct key was pressed and adds score or mistake
     *
     * @param key the key pressed by the gamer
     */
    private void checkKeyPressed(String key) {
        if (letters.length > currentLetterIndex) {
            //The pressed key is existing
            if (letters[currentLetterIndex].equals(key)) {
                //The correct key was pressed - add scores
                if (GameContext.getSettings().isSoundOn()) {
                    correctKeySound.setMicrosecondPosition(0);
                    correctKeySound.start();
                }
                gameSubPanel.hideBlock(currentLetterIndex);
                int score = GameContext.getSettings().getScore();
                int level = GameContext.getSettings().getLevel();
                score = score + level; //Level-based score multiplication to make the higher level more valuable compared with the same effort spent
                GameContext.getSettings().setScore(score);
                infoBarPanel.setScoreField(score);
                currentLetterIndex++;
                if (letters.length > currentLetterIndex) {
                    //Highlight the next key to continue the round
                    keyboardPanel.highlightButton(letters[currentLetterIndex]);
                } else {
                    //All keys were successfully hit - user wins the round
                    keyboardPanel.resetButtonHighlighting();
                    if (GameContext.getSettings().isSoundOn()) {
                        roundWinSound.setMicrosecondPosition(0);
                        roundWinSound.start();
                    }
                }
            } else {
                //The incorrect key was pressed - add mistakes
                if (GameContext.getSettings().isSoundOn()) {
                    wrongKeySound.setMicrosecondPosition(0);
                    wrongKeySound.start();
                }
                int mistakes = GameContext.getSettings().getMistakes();
                mistakes++;
                GameContext.getSettings().setMistakes(mistakes);
                infoBarPanel.setMistakesBar(mistakes);
                if (mistakes >= GameContext.getMaxMistakes()) {
                    //The player has made too many mistakes - stopping the game
                    playRoundLoseSound();
                    stopGame();
                }
            }
        }
    }

    /**
     * Returns the instance of the next game skin, based on the current one.
     * The main idea is to move through the seasons (summer -> autumn -> winter -> spring -> summer)
     */
    private AbstractGamePanel getNextGameSkin() {
        if (gameSubPanel instanceof MovingFloorGamePanel) {
            return new HuntingWhirlwindGamePanel(this, letters);
        } else if (gameSubPanel instanceof HuntingWhirlwindGamePanel) {
            return new FallingCeilingGamePanel(this, letters);
        } else if (gameSubPanel instanceof FallingCeilingGamePanel) {
            return new ScaryCloudGamePanel(this, letters);
        } else {
            return new MovingFloorGamePanel(this, letters);
        }
    }

    /**
     * Skips the game, replaces the current result with the last saved and switches to the next frame.
     * The method is created for testing purposes only!
     */
    private void skipGame() {
        gameSubPanel.stopGame();
        List<RankingRecord> recordList = GameContext.getRecordList();
        RankingRecord lastRecord = recordList.get(recordList.size() - 1);
        GameContext.setRecord(lastRecord);
        rankingPanel.refreshGUI();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, Constants.Screen.RANKING);
    }
}
