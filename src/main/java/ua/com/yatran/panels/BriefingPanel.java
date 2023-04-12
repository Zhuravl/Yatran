package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class BriefingPanel extends JPanel {

    private JPanel contentPane;
    private JLabel briefingPicture, briefingLabel1, briefingLabel2;
    private GamePanel gamePanel;

    public BriefingPanel(JPanel contentPane, GamePanel gamePanel) {
        this.contentPane = contentPane;
        this.gamePanel = gamePanel;
        this.setLayout(null);
        registerKeyboardAction(
                e -> startGame(),
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);

        briefingLabel1 = new JLabel(rb.getString("briefing_label_1"));
        briefingLabel1.setFont(Constants.Common.FONT_MAIN);
        briefingLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        briefingLabel1.setBounds(0, Constants.Common.ELEMENTS_CLEARANCE * 5, Constants.Common.MAIN_WINDOW_WIDTH, 40);
        this.add(briefingLabel1);

        briefingLabel2 = new JLabel(rb.getString("briefing_label_2"));
        briefingLabel2.setFont(Constants.Common.FONT_MAIN);
        briefingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        briefingLabel2.setBounds(0, briefingLabel1.getY() + briefingLabel1.getHeight(), Constants.Common.MAIN_WINDOW_WIDTH, 40);
        this.add(briefingLabel2);

        briefingPicture = new JLabel();
        try {
            InputStream stream = getClass().getResourceAsStream("/images/keyboard.png");
            briefingPicture.setIcon(new ImageIcon(ImageIO.read(stream)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        briefingPicture.setHorizontalAlignment(SwingConstants.CENTER);
        briefingPicture.setBounds(0, briefingLabel2.getY() + briefingLabel2.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_WIDTH, 400);
        this.add(briefingPicture);
    }

    /**
     * Switches to the Game Screen and starts the game
     */
    private void startGame() {
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, Constants.Screen.GAME);
        gamePanel.startGame();
    }
}
