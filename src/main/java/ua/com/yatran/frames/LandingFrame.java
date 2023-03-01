package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Locale;
import java.util.ResourceBundle;

public class LandingFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final int W_FRAME = 700;
    public static final int H_FRAME = 600;

    private JLabel labelLogo;
    private JPanel contentPane;
    private Insets insets;

    public LandingFrame() {
        super(Constants.APP_NAME);
        setResizable(false);
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        insets = this.getInsets();

        GUI();
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right, H_FRAME - insets.bottom - insets.top);

        labelLogo = new JLabel(new ImageIcon("src/main/resources/images/Yatran-logo.png"));
        labelLogo.setBounds(0, 0, 700, 300);
        contentPane.add(labelLogo);

        createButtonsForAllLocales();

        setContentPane(contentPane);
    }

    /**
     * Creates buttons for all available locales
     */
    private void createButtonsForAllLocales() {
        int previousX = labelLogo.getX();
        int previousY = labelLogo.getY();

        for (int index = 0; index < Language.values().length; index++) {
            JButton button = new JButton(ResourceBundle.getBundle(Constants.LOCALE_PREFIX, Language.values()[index].getLocale()).getString("landing_button"));
            button.setFont(Constants.FONT_LANDING);
            button.setBounds(200, 300, 200, 30);
            if (index == 0) {
                button.setBounds(previousX + 150, previousY + 300, 400, 100);
            } else {
                button.setBounds(previousX, previousY + 120, 400, 100);
            }
            button.setFocusPainted(false);
            int finalIndex = index;
            button.addActionListener(e -> {
                EventQueue.invokeLater(() -> {
                    LandingFrame.this.dispose();
                    Locale.setDefault(Language.values()[finalIndex].getLocale());
                    ResourceBundle.clearCache();
                    new MainFrame();
                });
            });
            contentPane.add(button);

            previousX = button.getX();
            previousY = button.getY();
        }
    }
}
