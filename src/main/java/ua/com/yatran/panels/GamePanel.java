package ua.com.yatran.panels;

import ua.com.yatran.panels.subpanels.GameSubPanel;
import ua.com.yatran.panels.subpanels.KeyboardSubPanel;

import javax.swing.*;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private GameSubPanel gameSubPanel;
    private KeyboardSubPanel keyboardSubPanel;

    private RankingPanel rankingPanel;

    public GamePanel(JPanel contentPane, RankingPanel rankingPanel) {
        this.contentPane = contentPane;
        this.rankingPanel = rankingPanel;
        this.setLayout(null);
//        registerKeyboardAction(
//                e -> continueButton.doClick(),
//                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
//                JComponent.WHEN_IN_FOCUSED_WINDOW);

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
