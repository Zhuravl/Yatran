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
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class GameSubPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private RankingPanel rankingPanel;
    private JPanel contentPane;

    private int x = 0;
    private int y = 0;
    private BufferedImage image;
    private Timer timer;

    private JButton continueButton; //technical button for development purposes only

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
            moveBall();
            repaint();
        });

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI() {
        timer.start();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);

        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(100, 100, 400, 100);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            GameContext.getRecord().setScore(new Random().nextInt(200));
            GameContext.getRecord().setLevel(new Random().nextInt(20));
            GameContext.getRecord().setSpeed(new Random().nextInt(100));
            GameContext.getRecord().setMistakes(new Random().nextInt(Constants.Common.MISTAKES_MAX));
            GameContext.getRecord().setDate(Calendar.getInstance());
            GameContext.saveRecordToDisk();
            rankingPanel.refreshGUI();
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.RANKING);
        }));
        this.add(continueButton);
    }

    private void moveBall() {
        x++;
        y++;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(image, x, y, this);
//        g2d.setColor(Color.RED);
//        g2d.fillOval(x, y, 30, 30);
        g2d.dispose();
    }
}
