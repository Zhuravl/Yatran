package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.entities.Settings;
import ua.com.yatran.enums.Language;
import ua.com.yatran.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Locale;

public class MainFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private JPanel contentPane, registerPanel, settingsPanel, briefingPanel, gamePanel, rankingPanel;
    private Insets insets;
    private RankingRecord rankingRecord;
    private Settings settings;

    public MainFrame() {
        super(Constants.Common.APP_NAME);
        setLayout(null);
        setSize(Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();
        rankingRecord = new RankingRecord();
        settings = new Settings(Language.getByLocale(Locale.getDefault()), 1, true);

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        contentPane.setBounds(insets.left, insets.top, Constants.Common.MAIN_WINDOW_WIDTH - insets.left - insets.right, Constants.Common.MAIN_WINDOW_HEIGHT - insets.bottom - insets.top);

        registerPanel = new RegisterPanel(contentPane, rankingRecord);
        settingsPanel = new SettingsPanel(contentPane, settings);
        briefingPanel = new BriefingPanel(contentPane);
        rankingPanel = new RankingPanel(contentPane);
        gamePanel = new GamePanel(contentPane, (RankingPanel) rankingPanel, rankingRecord);

        contentPane.add(registerPanel, Constants.Screen.REGISTER);
        contentPane.add(settingsPanel, Constants.Screen.SETTINGS);
        contentPane.add(briefingPanel, Constants.Screen.BRIEFING);
        contentPane.add(gamePanel, Constants.Screen.GAME);
        contentPane.add(rankingPanel, Constants.Screen.RANKING);

        setContentPane(contentPane);
    }
}
