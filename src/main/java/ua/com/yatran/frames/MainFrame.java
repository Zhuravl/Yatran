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
        setSize(Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT + Constants.Common.WINDOW_TITLE_BAR_HEIGHT);
        setResizable(false);
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
        contentPane.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT);

        registerPanel = new RegisterPanel(contentPane);
        settingsPanel = new SettingsPanel(contentPane);
        rankingPanel = new RankingPanel(contentPane, (SettingsPanel) settingsPanel);
        gamePanel = new GamePanel(contentPane, (RankingPanel) rankingPanel);
        briefingPanel = new BriefingPanel(contentPane, (GamePanel) gamePanel);

        contentPane.add(registerPanel, Constants.Screen.REGISTER);
        contentPane.add(settingsPanel, Constants.Screen.SETTINGS);
        contentPane.add(briefingPanel, Constants.Screen.BRIEFING);
        contentPane.add(gamePanel, Constants.Screen.GAME);
        contentPane.add(rankingPanel, Constants.Screen.RANKING);

        setContentPane(contentPane);
    }
}
