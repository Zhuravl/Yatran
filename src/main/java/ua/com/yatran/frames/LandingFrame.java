package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LandingFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel labelLogo;
    private JPanel contentPane;
    private Insets insets;

    public LandingFrame() {
        super(Constants.Common.APP_NAME);
        setResizable(false);
        setLayout(null);
        setSize(Constants.Common.LANDING_WINDOW_WIDTH, Constants.Common.LANDING_WINDOW_HEIGHT);
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
        contentPane.setBounds(insets.left, insets.top, Constants.Common.LANDING_WINDOW_WIDTH - insets.left - insets.right, Constants.Common.LANDING_WINDOW_HEIGHT - insets.bottom - insets.top);
        labelLogo = new JLabel();
        labelLogo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png"))));
        labelLogo.setBackground(Color.WHITE);
        labelLogo.setOpaque(true);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogo.setBounds(0, 0, Constants.Common.LANDING_WINDOW_WIDTH, Constants.Common.LANDING_WINDOW_HEIGHT / 2);
        contentPane.add(labelLogo);

        createButtonsForAllLocales();

        setContentPane(contentPane);
    }

    /**
     * Creates buttons for all available locales
     */
    private void createButtonsForAllLocales() {
        int previousX = (Constants.Common.LANDING_WINDOW_WIDTH / 2) - (Constants.Common.BUTTON_WIDTH / 2);
        int previousY = labelLogo.getHeight();

        for (int index = 0; index < Language.values().length; index++) {
            JButton button = new JButton(ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, Language.values()[index].getLocale()).getString("landing_button"));
            button.setFont(Constants.Common.FONT_MAIN);
            if (index == 0) {
                button.setBounds(previousX, previousY + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
            } else {
                button.setBounds(previousX, previousY + Constants.Common.BUTTON_HEIGHT + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
            }
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
