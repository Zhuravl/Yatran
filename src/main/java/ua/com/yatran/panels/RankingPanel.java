package ua.com.yatran.panels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class RankingPanel extends JPanel {

    private JPanel contentPane;
    private JButton continueButton, exitButton;
    private JTable rankingTable;
    private JScrollPane scrollPane;
    private RankingTableModel tableModel;

    public RankingPanel(JPanel contentPane) {
        this.contentPane = contentPane;
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

        rankingTable = new JTable(tableModel);
        rankingTable.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH - 20, 350);
        rankingTable.setEnabled(false);
        rankingTable.getTableHeader().setEnabled(false);
        rankingTable.setShowGrid(true);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, rankingTable.getWidth(), rankingTable.getHeight());
        scrollPane.setViewportView(rankingTable);
        this.add(scrollPane);

        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 20, 400, 100);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.REGISTER);
        }));
        this.add(continueButton);

        exitButton = new JButton(rb.getString("exit_button"));
        exitButton.setFont(Constants.Common.FONT_MAIN);
        exitButton.setBounds(continueButton.getX(), continueButton.getY() + continueButton.getHeight() + 20, 400, 100);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            System.exit(0);
        }));
        this.add(exitButton);
    }
}
