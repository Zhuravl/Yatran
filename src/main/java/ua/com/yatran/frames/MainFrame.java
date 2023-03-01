package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Record;
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

    public static final int W_FRAME = 1080;
    public static final int H_FRAME = (int) (W_FRAME / ((Math.sqrt(5) + 1) / 2));

    private JPanel contentPane, registerPanel, settingsPanel, briefingPanel, gamePanel, chartPanel;
    private Insets insets;
    private Record record;
    private Settings settings;

    public MainFrame() {
        super(Constants.APP_NAME);
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();
        record = new Record();
        settings = new Settings(Language.getByLocale(Locale.getDefault()), 1, true);

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        contentPane.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right, H_FRAME - insets.bottom - insets.top);

        registerPanel = new RegisterPanel(contentPane, record);
        settingsPanel = new SettingsPanel(contentPane, settings);
        briefingPanel = new BriefingPanel(contentPane);
        gamePanel = new GamePanel(contentPane);
        chartPanel = new ChartPanel(contentPane);

        contentPane.add(registerPanel);
        contentPane.add(settingsPanel);
        contentPane.add(briefingPanel);
        contentPane.add(gamePanel);
        contentPane.add(chartPanel);

        setContentPane(contentPane);
    }
}
