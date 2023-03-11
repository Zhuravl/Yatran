package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MainFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private JPanel contentPane, registerPanel, settingsPanel, briefingPanel, gamePanel, rankingPanel;
    private Insets insets;

    public MainFrame() {
        super(Constants.Common.APP_NAME);
        setLayout(null);
        setSize(Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        contentPane.setBounds(insets.left, insets.top, Constants.Common.MAIN_WINDOW_WIDTH - insets.left - insets.right, Constants.Common.MAIN_WINDOW_HEIGHT - insets.bottom - insets.top);

        registerPanel = new RegisterPanel(contentPane);
        briefingPanel = new BriefingPanel(contentPane);
        rankingPanel = new RankingPanel(contentPane);
        gamePanel = new GamePanel(contentPane, (RankingPanel) rankingPanel);
        settingsPanel = new SettingsPanel(contentPane, (GamePanel) gamePanel);

        contentPane.add(registerPanel, Constants.Screen.REGISTER);
        contentPane.add(settingsPanel, Constants.Screen.SETTINGS);
        contentPane.add(briefingPanel, Constants.Screen.BRIEFING);
        contentPane.add(gamePanel, Constants.Screen.GAME);
        contentPane.add(rankingPanel, Constants.Screen.RANKING);

        setContentPane(contentPane);
    }
}
