package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class BriefingPanel extends JPanel {

    private JPanel contentPane;
    private JLabel briefingPicture, briefingText;
    private JButton continueButton;

    public BriefingPanel(JPanel contentPane) {
        this.contentPane = contentPane;
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

        briefingPicture = new JLabel(new ImageIcon("src/main/resources/images/Ukrainian-keyboard-standard.png"));
        briefingPicture.setBounds(0, 0, Constants.Common.MAIN_WINDOW_WIDTH, 400);
        this.add(briefingPicture);

        briefingText = new JLabel(rb.getString("briefing_label"));
        briefingText.setFont(Constants.Common.FONT_MAIN);
        briefingText.setBounds(50, briefingPicture.getY() + briefingPicture.getHeight() + 50, Constants.Common.MAIN_WINDOW_WIDTH, 20);
        this.add(briefingText);

        continueButton = new JButton(rb.getString("continue_button"));
        continueButton.setFont(Constants.Common.FONT_MAIN);
        continueButton.setBounds(briefingText.getX(), briefingText.getY() + briefingText.getHeight() + 30, 400, 100);
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(e -> EventQueue.invokeLater(() -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, Constants.Screen.GAME);
        }));
        this.add(continueButton);
    }
}
