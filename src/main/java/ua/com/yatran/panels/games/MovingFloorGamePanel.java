package ua.com.yatran.panels.games;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Objects;

public class MovingFloorGamePanel extends AbstractGamePanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int SCENE_SHIFT_X = 50;
    private final int SCENE_SHIFT_Y = 150;

    private final int FLOOR_HEIGHT = 20;
    private final int FLOOR_WIDTH = Constants.Common.MAIN_WINDOW_WIDTH - (SCENE_SHIFT_X * 2);
    private int xFloor;
    private int yFloor;
    private int widthFloor;

    private final int HERO_HEIGHT = 50;
    private final int HERO_WIDTH = 45;
    private Image heroImage;
    private int xHero;
    private int yHero;

    private final int LETTER_BLOCK_HEIGHT = 40;
    private final int LETTER_BLOCK_WIDTH = 40;
    private final int LETTER_BLOCK_X = 3 * SCENE_SHIFT_X + HERO_WIDTH;
    private final int LETTER_BLOCK_Y = SCENE_SHIFT_Y - LETTER_BLOCK_HEIGHT;

    private Timer timer;
    private Image backgroundImage;

    public MovingFloorGamePanel(GamePanel mainPanel, String[] letters) {
        super(mainPanel, letters);

        xFloor = SCENE_SHIFT_X;
        yFloor = SCENE_SHIFT_Y;
        widthFloor = FLOOR_WIDTH;
        xHero = SCENE_SHIFT_X;
        yHero = SCENE_SHIFT_Y - HERO_HEIGHT;

        setLayout(null);

        timer = new Timer(40, e -> {
            moveFloor();
            moveHero();
            repaint();
        });

        GUI();
    }

    /**
     * Start the game
     */
    @Override
    public void startGame() {
        timer.start();
    }

    /**
     * Stops the game
     */
    @Override
    public void stopGame() {
        timer.stop();
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
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.png"))).getImage();
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
     * Moves the Hero element
     */
    private void moveHero() {
        if (xHero + HERO_WIDTH > xFloor) {
            //The Hero still has ground under his legs
            if (xHero < Constants.Common.MAIN_WINDOW_WIDTH - SCENE_SHIFT_X) {
                //The Hero hasn't gone all the way yet
                if (canHeroMoveOn(xHero + HERO_WIDTH, letterBlockArray)) {
                    //The way is free - move on
                    xHero = xHero + Constants.Game.MOVING_FLOOR_SPEED + 1;
                } else {
                    //The way is not free - do nothing and just wait
                }
            } else {
                //The Hero has gone all the way already
                timer.stop();
                mainPanel.nextLevel();
            }

        } else {
            //The Hero loses ground under his legs - showing the Hero's fall
            yHero = yHero + (Constants.Game.MOVING_FLOOR_SPEED * 3);
            if (yHero > (yFloor + FLOOR_HEIGHT * 2)) {
                timer.stop();
                mainPanel.stopGame();
            }
        }
    }

    /**
     * Returns a decision about the Hero's ability to move forward considering the obstructions' state on his way
     *
     * @param heroXFront   the Hero's ultimate right position by X coordinate (X + width)
     * @param obstructions the set of obstructions on the Hero's way
     */
    private boolean canHeroMoveOn(int heroXFront, JButton[] obstructions) {
        for (JButton block : obstructions) {
            if (block.isVisible()) {
                int distance = block.getX() - heroXFront;
                return distance > 1; //The Hero can move on if the distance to the nearest (displayed) obstruction is more than 1
            }
        }
        //The Hero passes all obstructions and can move on
        return true;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Background image
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, super.getWidth(), super.getHeight(), null);

        //Left bridge column
        g2d.setColor(Color.GRAY);
        int[] x_left = new int[]{0, 0, SCENE_SHIFT_X / 2, SCENE_SHIFT_X, SCENE_SHIFT_X};
        int[] y_left = new int[]{SCENE_SHIFT_Y, Constants.Common.MAIN_WINDOW_HEIGHT, Constants.Common.MAIN_WINDOW_HEIGHT, SCENE_SHIFT_Y + FLOOR_HEIGHT, SCENE_SHIFT_Y};
        g2d.fillPolygon(new Polygon(x_left, y_left, x_left.length));

        //Right bridge column
        g2d.setColor(Color.GRAY);
        int[] x_right = new int[]{SCENE_SHIFT_X + FLOOR_WIDTH, SCENE_SHIFT_X + FLOOR_WIDTH, SCENE_SHIFT_X + FLOOR_WIDTH + (SCENE_SHIFT_X / 2), SCENE_SHIFT_X + FLOOR_WIDTH + SCENE_SHIFT_X, SCENE_SHIFT_X + FLOOR_WIDTH + SCENE_SHIFT_X};
        int[] y_right = new int[]{SCENE_SHIFT_Y, SCENE_SHIFT_Y + FLOOR_HEIGHT, Constants.Common.MAIN_WINDOW_HEIGHT, Constants.Common.MAIN_WINDOW_HEIGHT, SCENE_SHIFT_Y};
        g2d.fillPolygon(new Polygon(x_right, y_right, x_right.length));

        //Bridge deck (floor)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(xFloor, yFloor, widthFloor, FLOOR_HEIGHT);

        //Hero animation
        g2d.drawImage(heroImage, xHero, yHero, HERO_WIDTH, HERO_HEIGHT, null);
        g2d.dispose();
    }
}
