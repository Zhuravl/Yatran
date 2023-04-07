package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingTableModel;
import ua.com.yatran.helpers.GameContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class RankingPanel extends JPanel {

    private JPanel contentPane;
    private JButton continueButton, exitButton;
    private JLabel rankingLabel;
    private JTable rankingTable;
    private JScrollPane scrollPane;
    private RankingTableModel tableModel;
    private SettingsPanel settingsPanel;

    public RankingPanel(JPanel contentPane, SettingsPanel settingsPanel) {
        this.contentPane = contentPane;
        this.settingsPanel = settingsPanel;
        this.tableModel = new RankingTableModel();
        this.setLayout(null);
        registerKeyboardAction(
                e -> continueButton.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI() {
        RankingTableModel model = (RankingTableModel) rankingTable.getModel();
        model.refreshData(GameContext.getRecord());
        rankingTable.setRowSelectionInterval(model.getCurrentUserRowIndex(), model.getCurrentUserRowIndex());
        rankingTable.revalidate();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);

        rankingLabel = new JLabel(rb.getString("ranking_label"));
        rankingLabel.setFont(Constants.Common.FONT_MAIN);
        rankingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rankingLabel.setBounds(0, Constants.Common.ELEMENTS_CLEARANCE * 5, Constants.Common.MAIN_WINDOW_WIDTH, 40);
        this.add(rankingLabel);

        rankingTable = new JTable(tableModel);
        rankingTable.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH - Constants.Common.ELEMENTS_CLEARANCE * 2, 350);
        rankingTable.setEnabled(false);
        rankingTable.getTableHeader().setEnabled(false);
        rankingTable.setShowGrid(true);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(Constants.Common.ELEMENTS_CLEARANCE, rankingLabel.getY() + rankingLabel.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, rankingTable.getWidth(), rankingTable.getHeight());
        scrollPane.setViewportView(rankingTable);
        this.add(scrollPane);

        exitButton = new JButton(rb.getString("exit_button"));
        exitButton.setFont(Constants.Common.FONT_MAIN);
        exitButton.setBounds(Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_HEIGHT - Constants.Common.BUTTON_HEIGHT - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH / 2, Constants.Common.BUTTON_HEIGHT);
        exitButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            System.exit(0);
        }));
        this.add(exitButton);

        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(Constants.Common.MAIN_WINDOW_WIDTH - Constants.Common.BUTTON_WIDTH - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.MAIN_WINDOW_HEIGHT - Constants.Common.BUTTON_HEIGHT - Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            settingsPanel.selectLevel(GameContext.getSettings() != null ? GameContext.getSettings().getLevel() : 1);
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.REGISTER);
        }));
        this.add(continueButton);
    }
}
