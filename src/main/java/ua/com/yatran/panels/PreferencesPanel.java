package ua.com.yatran.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class PreferencesPanel extends JPanel {

    private JPanel contentPane;
    private JButton button;

    public PreferencesPanel(JPanel panel) {
        contentPane = panel;
        this.setLayout(null);
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("Locale", locale);

        button = new JButton(rb.getString("button_back"));
        button.setFont(new Font("Tahoma", Font.PLAIN, 26));
        button.setBounds(100, 100, 400, 100);
        button.setFocusPainted(false);
        button.addActionListener(e -> EventQueue.invokeLater(() -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.previous(contentPane);
        }));
        this.add(button);
    }
}
