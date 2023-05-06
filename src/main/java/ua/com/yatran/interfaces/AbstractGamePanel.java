package ua.com.yatran.interfaces;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class AbstractGamePanel extends JPanel {

    protected final int TIMER_DELAY = 40;
    protected final int HERO_HEIGHT = 50;
    protected final int HERO_WIDTH = 45;
    protected final int LETTER_BLOCK_HEIGHT = 40;
    protected final int LETTER_BLOCK_WIDTH = 40;
    protected final int SCENE_SHIFT_X = 50;
    protected final int SCENE_SHIFT_Y = 150;
    protected final int FLOOR_HEIGHT = 20;
    protected final int FLOOR_WIDTH = Constants.Common.MAIN_WINDOW_WIDTH - (SCENE_SHIFT_X * 2);

    protected GamePanel mainPanel; //The main panel to call 'start/stop game' methods on
    protected JButton[] letterBlockArray; //The array with letters block to display while the game
    protected String[] letters; //The array with letters to set as text for the 'letterBlockArray' objects

    protected Image heroImage; //The Hero view
    protected int xHero; //The Hero X coordinate
    protected int yHero; //The Hero Y coordinate
    protected int LETTER_BLOCK_X; //The Letters Block X coordinate
    protected int LETTER_BLOCK_Y; //The Letters Block Y coordinate
    protected int xFloor; //The Floor X coordinate
    protected int yFloor; //The Floor Y coordinate
    protected int widthFloor; //The Floor width
    protected Timer timer; //The timer instance to move the game items
    protected Image backgroundImage; //The scene background image

    public AbstractGamePanel(GamePanel mainPanel, String[] letters) {
        this.mainPanel = mainPanel;
        this.letters = letters;
        setBounds(0, Constants.Common.MAIN_WINDOW_HEIGHT / 8, Constants.Common.MAIN_WINDOW_WIDTH, (Constants.Common.MAIN_WINDOW_HEIGHT - (Constants.Common.MAIN_WINDOW_HEIGHT / 5) - (Constants.Common.MAIN_WINDOW_HEIGHT / 8) - Constants.Common.ELEMENTS_CLEARANCE));

        xHero = SCENE_SHIFT_X;
        yHero = SCENE_SHIFT_Y - HERO_HEIGHT;

        LETTER_BLOCK_X = 3 * SCENE_SHIFT_X + HERO_WIDTH;
        LETTER_BLOCK_Y = SCENE_SHIFT_Y - LETTER_BLOCK_HEIGHT;

        xFloor = SCENE_SHIFT_X;
        yFloor = SCENE_SHIFT_Y;
        widthFloor = FLOOR_WIDTH;

        timer = new Timer(TIMER_DELAY, e -> {
            moveInfluence();
            moveHero();
            repaint();
        });

        GUI();
    }

    /**
     * Start the game
     */
    public void startGame() {
        timer.start();
    }

    /**
     * Stops the game
     */
    public void stopGame() {
        timer.stop();
    }

    /**
     * Hides a letter block with the defined index to allow the Hero to move forward
     *
     * @param index block index to use
     */
    public void hideBlock(int index) {
        if (letterBlockArray != null && letterBlockArray.length > index && letterBlockArray[index] != null) {
            letterBlockArray[index].setVisible(false);
        }
    }

    /**
     * Moves the influence
     */
    protected abstract void moveInfluence();

    /**
     * Moves the Hero element
     */
    protected abstract void moveHero();

    /**
     * Returns a decision about the Hero's ability to move forward considering the obstructions' state on his way
     */
    protected boolean canHeroMoveOn() {
        for (JButton block : letterBlockArray) {
            if (block.isVisible()) {
                int distance = block.getX() - (xHero + HERO_WIDTH);
                return distance > 1; //The Hero can move on if the distance to the nearest (displayed) obstruction is more than 1
            }
        }
        //The Hero passes all obstructions and can move on
        return true;
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        final int BLOCK_INTERVAL = 2;
        int xBlock = LETTER_BLOCK_X;
        letterBlockArray = new JButton[Constants.Game.LEVEL_CHARACTER_SIZE];
        for (int i = 0; i < Constants.Game.LEVEL_CHARACTER_SIZE; i++) {
            JButton letterBlock = new JButton();
            letterBlock.setText(letters[i]);
            letterBlock.setBounds(xBlock, LETTER_BLOCK_Y, LETTER_BLOCK_WIDTH, LETTER_BLOCK_HEIGHT);
            letterBlock.setFont(Constants.Common.FONT_LETTER_BLOCK);
            letterBlock.setBackground(Color.ORANGE);
            letterBlock.setForeground(Color.BLUE);
            letterBlock.setOpaque(true);
            letterBlock.setBorderPainted(false);
            letterBlock.setRolloverEnabled(false);

            letterBlockArray[i] = letterBlock;
            this.add(letterBlock);

            xBlock = xBlock + LETTER_BLOCK_WIDTH + BLOCK_INTERVAL;
        }

        heroImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cossack.gif"))).getImage();
    }
}
