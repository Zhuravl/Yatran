package ua.com.yatran.panels.subpanels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.panels.RankingPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.Calendar;
import java.util.Random;

public class GameSubPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private JButton[] letterBlockArray;
    private RankingPanel rankingPanel;
    private JPanel contentPane;

    private final int SCENE_SHIFT_X = 50;
    private final int SCENE_SHIFT_Y = 150;

    private final int FLOOR_HEIGHT = 20;
    private final int FLOOR_WIDTH = Constants.Common.MAIN_WINDOW_WIDTH - (SCENE_SHIFT_X * 2);
    private int xFloor = SCENE_SHIFT_X;
    private int yFloor = SCENE_SHIFT_Y;
    private int widthFloor = FLOOR_WIDTH;

    private final int CHARACTER_HEIGHT = 30;
    private final int CHARACTER_WIDTH = 30;
    private int xCharacter = SCENE_SHIFT_X;
    private int yCharacter = SCENE_SHIFT_Y - CHARACTER_HEIGHT;

    private final int LETTER_BLOCK_HEIGHT = 40;
    private final int LETTER_BLOCK_WIDTH = 40;
    private final int LETTER_BLOCK_X = SCENE_SHIFT_X + CHARACTER_WIDTH;
    private final int LETTER_BLOCK_Y = SCENE_SHIFT_Y - LETTER_BLOCK_HEIGHT;

    private BufferedImage image;
    private Timer timer;

    public GameSubPanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;

        this.setLayout(null);

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/Pfnu.gif"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.timer = new Timer(40, e -> {
            moveFloor();
            moveCharacter();
            repaint();
        });

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI(String[] letters) {
        updateLetterBlock(letters);
        timer.start();
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
    }

    /**
     * Moves the Floor element
     */
    private void moveFloor() {
        if (xFloor < SCENE_SHIFT_X + FLOOR_WIDTH) {
            xFloor = xFloor + Constants.Game.MOVING_FLOOR_SPEED;
            widthFloor = widthFloor - Constants.Game.MOVING_FLOOR_SPEED;
        }
    }

    /**
     * Moves the Character element
     */
    private void moveCharacter() {
        if (xCharacter + CHARACTER_WIDTH > xFloor) {
            //The Character still has ground under his legs
            if (xCharacter < Constants.Common.MAIN_WINDOW_WIDTH - SCENE_SHIFT_X - 600) {
                xCharacter = xCharacter + Constants.Game.MOVING_FLOOR_SPEED + 1;
            }

        } else {
            //The Character loses ground under his legs - showing the character's fall
            yCharacter = yCharacter + (Constants.Game.MOVING_FLOOR_SPEED * 3);
            if (yCharacter > (yFloor + FLOOR_HEIGHT * 2)) {
                stopGame();
            }
        }
    }

    /**
     * Stops the game, saves the results and switches to the next frame
     */
    private void stopGame() {
        GameContext.getRecord().setScore(new Random().nextInt(200));
        GameContext.getRecord().setLevel(new Random().nextInt(20));
        GameContext.getRecord().setSpeed(new Random().nextInt(100));
        GameContext.getRecord().setMistakes(new Random().nextInt(Constants.Game.MISTAKES_MAX));
        GameContext.getRecord().setDate(Calendar.getInstance());
        GameContext.saveRecordToDisk();
        rankingPanel.refreshGUI();
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, Constants.Screen.RANKING);
        timer.stop();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D movingFloor = (Graphics2D) g.create();
        movingFloor.setColor(Color.GRAY);
        movingFloor.fillRect(xFloor, yFloor, widthFloor, FLOOR_HEIGHT);
        movingFloor.dispose();

        Graphics2D mainCharacter = (Graphics2D) g.create();
        mainCharacter.setColor(Color.RED);
        mainCharacter.fillOval(xCharacter, yCharacter, CHARACTER_WIDTH, CHARACTER_HEIGHT);
        mainCharacter.dispose();
    }

    /**
     * Updates the existing letters block with the defined set of letters
     *
     * @param letters set of letter to use for the letters block
     */
    private void updateLetterBlock(String[] letters) {
        for (int i = 0; i < Constants.Game.LEVEL_CHARACTER_SIZE; i++) {
            letterBlockArray[i].setText(letters[i]);
        }
    }
}
