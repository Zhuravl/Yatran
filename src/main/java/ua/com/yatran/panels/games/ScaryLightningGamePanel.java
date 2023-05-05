package ua.com.yatran.panels.games;

import ua.com.yatran.interfaces.AbstractGamePanel;
import ua.com.yatran.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Objects;

public class ScaryLightningGamePanel extends AbstractGamePanel {

    @Serial
    private static final long serialVersionUID = 1L;

    public ScaryLightningGamePanel(GamePanel mainPanel, String[] letters) {
        super(mainPanel, letters);

        setLayout(null);

        GUI();
    }

    /**
     * Moves the influence
     */
    @Override
    protected void moveInfluence() {
//        hereis
    }

    /**
     * Moves the Hero element
     */
    @Override
    protected void moveHero() {
//        hereis
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        objects
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background_spring.png"))).getImage();
    }
}
