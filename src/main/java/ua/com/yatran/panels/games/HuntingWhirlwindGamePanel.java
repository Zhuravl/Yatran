package ua.com.yatran.panels.games;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Objects;

public class HuntingWhirlwindGamePanel extends AbstractGamePanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int WHIRLWIND_HEIGHT = 150;
    private static final int WHIRLWIND_WIDTH = 100;

    private Image whirlwindImage;
    private int xWhirlwind;
    private int yWhirlwind;

    public HuntingWhirlwindGamePanel(GamePanel mainPanel, String[] letters) {
        super(mainPanel, letters);

        setLayout(null);

        xWhirlwind = SCENE_SHIFT_X - WHIRLWIND_WIDTH;
        yWhirlwind = SCENE_SHIFT_Y - WHIRLWIND_HEIGHT;

        GUI();
    }

    /**
     * Moves the influence
     */
    @Override
    protected void moveInfluence() {
        if (xWhirlwind < SCENE_SHIFT_X + FLOOR_WIDTH) {
            xWhirlwind = xWhirlwind + Constants.Game.INFLUENCE_SPEED;
        }
    }

    /**
     * Moves the Hero element
     */
    @Override
    protected void moveHero() {
        final int BANGS_LENGTH = HERO_WIDTH / 3;
        if (xHero + BANGS_LENGTH >= xWhirlwind + WHIRLWIND_WIDTH) {
            //The Hero still has not been overtaken by a whirlwind
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
            //The Hero was overtaken by a whirlwind - showing the Hero's takeoff
            if (yHero == (SCENE_SHIFT_Y - HERO_HEIGHT)) {
                //Play the Round Lose sound 'at the beginning of the end' and only once
                mainPanel.playRoundLoseSound();
            }
            yHero = yHero - (Constants.Game.INFLUENCE_SPEED * 3);
            if ((yHero + HERO_HEIGHT) < (yFloor - FLOOR_HEIGHT * 2)) {
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

        //Whirlwind animation
        g2d.drawImage(whirlwindImage, xWhirlwind, yWhirlwind, WHIRLWIND_WIDTH, WHIRLWIND_HEIGHT, null);

        g2d.dispose();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background_autumn.png"))).getImage();
        whirlwindImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/whirlwind.gif"))).getImage();
    }
}
