package ua.com.yatran.panels.games;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.util.Objects;
import java.util.Random;

public class FallingCeilingGamePanel extends AbstractGamePanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private final int ICICLE_WIDTH = 20;
    private final int ICICLES_NUMBER = FLOOR_WIDTH / ICICLE_WIDTH;

    private Polygon[] icicles;

    public FallingCeilingGamePanel(GamePanel mainPanel, String[] letters) {
        super(mainPanel, letters);

        setLayout(null);

        GUI();
    }

    /**
     * Moves the influence
     */
    @Override
    protected void moveInfluence() {
        final int ICICLES_IMMERSION_DEPTH = 5;
        final int FALLING_DISTANCE_COMPENSATION = 5; //The compensation coefficient to make the level speed the same with other levels. Have the direct correlation with 'icicleHeight' value
        for (int i = 0; i < ICICLES_NUMBER; i++) {
            int[] yCoordinates = icicles[i].ypoints;
            if (yCoordinates[1] <= SCENE_SHIFT_Y + ICICLES_IMMERSION_DEPTH) {
                int fallingSpeed = Constants.Game.INFLUENCE_SPEED * FALLING_DISTANCE_COMPENSATION;
                yCoordinates[0] = yCoordinates[0] + fallingSpeed;
                yCoordinates[1] = yCoordinates[1] + fallingSpeed;
                yCoordinates[2] = yCoordinates[2] + fallingSpeed;
                return; //This needs to have only one move for one iteration
            }
        }
    }

    /**
     * Moves the Hero element
     */
    @Override
    protected void moveHero() {
        if (isHeroHit()) {
            //An icicle has hit the Hero - showing the Hero's fall
            if (yHero == (SCENE_SHIFT_Y - HERO_HEIGHT)) {
                //Play the Round Lose sound 'at the beginning of the end' and only once
                mainPanel.playRoundLoseSound();
            }
            yHero = yHero + (Constants.Game.INFLUENCE_SPEED * 3);
            if (yHero > (yFloor + FLOOR_HEIGHT * 2)) {
                timer.stop();
                mainPanel.stopGame();
            }
        } else {
            //The Hero still has not hit by an icicle
            if (xHero < Constants.Common.MAIN_WINDOW_WIDTH - SCENE_SHIFT_X) {
                //The Hero hasn't gone all the way yet
                if (canHeroMoveOn()) {
                    //The way is free - move on
                    xHero = xHero + Constants.Game.INFLUENCE_SPEED + 1;
                } else {
                    //The way is not free - do nothing and just wait
                }
            } else {
                //The Hero has gone all the way already
                timer.stop();
                mainPanel.nextLevel();
            }
        }
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

        //Icicles
        drawIcicles(g2d);

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

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        icicles = createIcicles();
        try {
            BufferedImage bimg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/background_winter.png")));
            backgroundImage = bimg.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the icicles array with random height
     */
    private Polygon[] createIcicles() {
        Polygon[] resultArray = new Polygon[ICICLES_NUMBER];
        for (int i = 0; i < ICICLES_NUMBER; i++) {
            int icicleHeight = new Random().nextInt(50, 70);
            int[] x_triangle = new int[]{(i * ICICLE_WIDTH) + SCENE_SHIFT_X, (i * ICICLE_WIDTH) + SCENE_SHIFT_X + (ICICLE_WIDTH / 2), (i * ICICLE_WIDTH) + SCENE_SHIFT_X + ICICLE_WIDTH};
            int[] y_triangle = new int[]{0, icicleHeight, 0};
            resultArray[i] = new Polygon(x_triangle, y_triangle, x_triangle.length);
        }
        return resultArray;
    }

    /**
     * Draws icicles using the defined Graphics2D instance and Y coordinate set
     *
     * @param g2d Graphics2D instance to draw using by
     */
    private void drawIcicles(Graphics2D g2d) {
        for (int i = 0; i < ICICLES_NUMBER; i++) {
            g2d.setColor(new Color(110, 159, 208));
            g2d.fillPolygon(icicles[i]);
        }
    }

    /**
     * Checks is Hero is hit by an icicle
     */
    private boolean isHeroHit() {
        final int BANGS_LENGTH = HERO_WIDTH / 3;
        boolean result = false;
        for (int i = 0; i < ICICLES_NUMBER; i++) {
            int[] xsIcicle = icicles[i].xpoints;
            int[] ysIcicle = icicles[i].ypoints;
            if (xsIcicle[0] < (xHero + HERO_WIDTH) && xsIcicle[2] > (xHero + BANGS_LENGTH)) {
                //The icicle is above the Hero (based on the X coordinate) and potentially could hit him - check the Y coordinate
                if (ysIcicle[1] >= yHero || (yHero + HERO_HEIGHT) > yFloor) {
                    //The icicle has hit the Hero OR Hero has already hit and is falling down
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
