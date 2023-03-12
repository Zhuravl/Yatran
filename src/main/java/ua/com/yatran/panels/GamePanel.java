package ua.com.yatran.panels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.panels.subpanels.GameSubPanel;
import ua.com.yatran.panels.subpanels.KeyboardSubPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.ResourceBundle;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private GameSubPanel gameSubPanel;
    private KeyboardSubPanel keyboardSubPanel;

    private RankingPanel rankingPanel;

    public GamePanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;
        this.setLayout(null);
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, GameContext.getSettings().getLanguage().getLocale());
        String keyNames = rb.getString("key_list");
        registerKeyboardAction(
                e -> {
                    int rnd = new Random().nextInt(keyNames.length() - 1);
                    String str = keyNames.substring(rnd, rnd + 1);
                    keyboardSubPanel.highlightButton(str);
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_G, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI() {
        gameSubPanel.refreshGUI();
        keyboardSubPanel.refreshGUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        gameSubPanel = new GameSubPanel(contentPane, rankingPanel);
        this.add(gameSubPanel);

        keyboardSubPanel = new KeyboardSubPanel();
        this.add(keyboardSubPanel);
    }
}
