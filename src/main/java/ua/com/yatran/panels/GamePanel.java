package ua.com.yatran.panels;

import ua.com.yatran.common.Context;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePanel extends JPanel {

    private RankingRecord rankingRecord;
    private JPanel contentPane;
    private RankingPanel rankingPanel;
    private JButton continueButton;

    public GamePanel(JPanel panel, RankingPanel rankingPanel, RankingRecord record) {
        contentPane = panel;
        this.rankingPanel = rankingPanel;
        rankingRecord = record;
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
            rankingRecord.setScore(new Random().nextInt(200));
            rankingRecord.setLevel(new Random().nextInt(15));
            rankingRecord.setSpeed(new Random().nextInt(100));
            rankingRecord.setMistakes(new Random().nextInt(50));
            rankingRecord.setDate(Calendar.getInstance());
            Context.addToRecordList(rankingRecord);
            rankingPanel.refreshTable(rankingRecord);
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.RANKING);
        }));
        this.add(continueButton);
    }
}
