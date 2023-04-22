package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.helpers.GameContext;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.games.MovingFloorGamePanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

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
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                checkKeyPressed(actionEvent.getActionCommand());
            }
        };

        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_QUOTE, 0), "Key `", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "Key 1", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "Key 2", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "Key 3", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "Key 4", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "Key 5", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "Key 6", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "Key 7", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "Key 8", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "Key 9", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "Key 0", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "Key -", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "Key =", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "Key q", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "Key w", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "Key e", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "Key r", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "Key t", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "Key y", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0), "Key u", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "Key i", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), "Key o", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "Key p", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), "Key [", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), "Key ]", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "Key Backslash", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "Key a", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "Key s", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "Key d", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "Key f", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "Key g", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "Key h", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0), "Key j", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), "Key k", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "Key l", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SEMICOLON, 0), "Key ;", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_QUOTE, 0), "Key '", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "Key z", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "Key x", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "Key c", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), "Key v", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "Key b", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "Key n", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "Key m", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0), "Key ,", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), "Key .", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "Key /", actionListener);

        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Key Space", actionListener);

        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_QUOTE, InputEvent.SHIFT_DOWN_MASK), "Key ~", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_DOWN_MASK), "Key !", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.SHIFT_DOWN_MASK), "Key @", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.SHIFT_DOWN_MASK), "Key #", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.SHIFT_DOWN_MASK), "Key $", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.SHIFT_DOWN_MASK), "Key %", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.SHIFT_DOWN_MASK), "Key ^", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK), "Key &", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), "Key *", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK), "Key (", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK), "Key )", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.SHIFT_DOWN_MASK), "Key _", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK), "Key +", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.SHIFT_DOWN_MASK), "Key Q", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.SHIFT_DOWN_MASK), "Key W", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK), "Key E", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK), "Key R", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK), "Key T", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.SHIFT_DOWN_MASK), "Key Y", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_DOWN_MASK), "Key U", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.SHIFT_DOWN_MASK), "Key I", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK), "Key O", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_DOWN_MASK), "Key P", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, InputEvent.SHIFT_DOWN_MASK), "Key {", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, InputEvent.SHIFT_DOWN_MASK), "Key }", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, InputEvent.SHIFT_DOWN_MASK), "Key |", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK), "Key A", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK), "Key S", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.SHIFT_DOWN_MASK), "Key D", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.SHIFT_DOWN_MASK), "Key F", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.SHIFT_DOWN_MASK), "Key G", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.SHIFT_DOWN_MASK), "Key H", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.SHIFT_DOWN_MASK), "Key J", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.SHIFT_DOWN_MASK), "Key K", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.SHIFT_DOWN_MASK), "Key L", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SEMICOLON, InputEvent.SHIFT_DOWN_MASK), "Key :", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_QUOTE, InputEvent.SHIFT_DOWN_MASK), "Key Double Quote", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.SHIFT_DOWN_MASK), "Key Z", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_DOWN_MASK), "Key X", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_DOWN_MASK), "Key C", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.SHIFT_DOWN_MASK), "Key V", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.SHIFT_DOWN_MASK), "Key B", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK), "Key N", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.SHIFT_DOWN_MASK), "Key M", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.SHIFT_DOWN_MASK), "Key <", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, InputEvent.SHIFT_DOWN_MASK), "Key >", actionListener);
        registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, InputEvent.SHIFT_DOWN_MASK), "Key ?", actionListener);

        try {
            AudioInputStream audioInputStreamCorrect = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/keyCorrect.wav").getAbsoluteFile());
            correctKeySound = AudioSystem.getClip();
            correctKeySound.open(audioInputStreamCorrect);

            AudioInputStream audioInputStreamWrong = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/keyWrong.wav").getAbsoluteFile());
            wrongKeySound = AudioSystem.getClip();
            wrongKeySound.open(audioInputStreamWrong);

            AudioInputStream audioInputStreamWin = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/roundWin.wav").getAbsoluteFile());
            roundWinSound = AudioSystem.getClip();
            roundWinSound.open(audioInputStreamWin);

            AudioInputStream audioInputStreamLose = AudioSystem.getAudioInputStream(new File("src/main/resources/sounds/roundLose.wav").getAbsoluteFile());
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
        setGameSubPanel(new MovingFloorGamePanel(this, letters));
        gameSubPanel.startGame();
    }

    /**
     * Starts the next game level or triggers game stopping
     */
    public void nextLevel() {
        int nextLevel = GameContext.getSettings().getLevel() + 1;
        int lastLevel = GameContext.getMaxLevel();
        if (GameContext.getSettings().isSoundOn()) {
            roundWinSound.setMicrosecondPosition(0);
            roundWinSound.start();
        }
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
        infoBarPanel.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 8);
        this.add(infoBarPanel);

        keyboardPanel = new KeyboardPanel();
        keyboardPanel.setBounds(Constants.Common.ELEMENTS_CLEARANCE / 4, Constants.Common.MAIN_WINDOW_HEIGHT - (Constants.Common.MAIN_WINDOW_HEIGHT / 6) - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_WIDTH - (Constants.Common.ELEMENTS_CLEARANCE / 2), Constants.Common.MAIN_WINDOW_HEIGHT / 5);
        this.add(keyboardPanel);
    }

    /**
     * Registers key binding
     *
     * @param keyStroke key name
     * @param name      binding comment
     * @param action    action to perform
     */
    private void registerKeyBinding(KeyStroke keyStroke, String name, Action action) {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(keyStroke, name);
        am.put(name, action);
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
            //There are keys to press existing
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
                    keyboardPanel.highlightButton(letters[currentLetterIndex]);
                } else {
                    keyboardPanel.resetButtonHighlighting();
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
}
