package ua.com.yatran.panels.games;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

public class ScaryCloudGamePanel extends AbstractGamePanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int CLOUD_HEIGHT = 150;
    private static final int CLOUD_WIDTH = 150;

    private Image cloudImage;
    private int xCloud;
    private int yCloud;

    public ScaryCloudGamePanel(GamePanel mainPanel, String[] letters) {
        super(mainPanel, letters);

        setLayout(null);

        xCloud = SCENE_SHIFT_X - CLOUD_WIDTH;
        yCloud = SCENE_SHIFT_Y - CLOUD_HEIGHT;

        GUI();
    }

    /**
     * Moves the influence
     */
    @Override
    protected void moveInfluence() {
        if (xCloud < SCENE_SHIFT_X + FLOOR_WIDTH) {
            xCloud = xCloud + Constants.Game.INFLUENCE_SPEED;
        }
    }

    /**
     * Moves the Hero element
     */
    @Override
    protected void moveHero() {
        final int BANGS_LENGTH = HERO_WIDTH / 3;
        if (xHero + BANGS_LENGTH >= xCloud + CLOUD_WIDTH) {
            //The Hero still has not hit by the cloud
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

        } else {
            //The cloud has hit the Hero - showing the Hero's fall
            if (yHero == (SCENE_SHIFT_Y - HERO_HEIGHT)) {
                //Play the Round Lose sound 'at the beginning of the end' and only once
                mainPanel.playRoundLoseSound();
            }
            yHero = yHero + (Constants.Game.INFLUENCE_SPEED * 3);
            if (yHero > (yFloor + FLOOR_HEIGHT * 2)) {
                timer.stop();
                mainPanel.stopGame();
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

        //Cloud animation
        g2d.drawImage(cloudImage, xCloud, yCloud, CLOUD_WIDTH, CLOUD_HEIGHT, null);

        g2d.dispose();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        try {
            BufferedImage bimg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/background_spring.png")));
            backgroundImage = bimg.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_SMOOTH);
            cloudImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cloud.gif"))).getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
