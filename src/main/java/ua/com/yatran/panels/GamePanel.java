package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class GamePanel extends JPanel {

    private JPanel contentPane;
    private JButton continueButton;

    public GamePanel(JPanel panel) {
        contentPane = panel;
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
        ResourceBundle rb = ResourceBundle.getBundle(Constants.LOCALE_PREFIX, locale);
        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.FONT_MAIN);
        continueButton.setBounds(100, 100, 400, 100);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.next(contentPane);
        }));
        this.add(continueButton);
    }
}
