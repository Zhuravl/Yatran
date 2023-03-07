package ua.com.yatran.panels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private RankingPanel rankingPanel;
    private JButton continueButton;

    public GamePanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;
        this.setLayout(null);
        registerKeyboardAction(
                e -> continueButton.doClick(),
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
        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(100, 100, 400, 100);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            GameContext.getRecord().setScore(new Random().nextInt(200));
            GameContext.getRecord().setLevel(new Random().nextInt(15));
            GameContext.getRecord().setSpeed(new Random().nextInt(100));
            GameContext.getRecord().setMistakes(new Random().nextInt(50));
            GameContext.getRecord().setDate(Calendar.getInstance());
            GameContext.saveRecordToDisk();
            rankingPanel.refreshTable(GameContext.getRecord());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.RANKING);
        }));
        this.add(continueButton);
    }
}
