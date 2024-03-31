package ua.com.yatran.frames;

import ua.com.yatran.constants.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class LandingFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private JLabel labelLogo, labelName, labelPromo;
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
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        labelLogo.setBounds(Constants.Common.ELEMENTS_CLEARANCE * 4, Constants.Common.ELEMENTS_CLEARANCE * 2, 150, 150);
        try {
            BufferedImage bimg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/icon-large.png")));
            Image img = bimg.getScaledInstance(labelLogo.getWidth(), labelLogo.getHeight(), Image.SCALE_SMOOTH);
            labelLogo.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentPane.add(labelLogo);

        labelName = new JLabel(Constants.Common.APP_NAME.toUpperCase());
        labelName.setFont(Constants.Common.FONT_LOGO);
        labelName.setForeground(Constants.Common.BUTTON_COLOR_LOGO);
        labelName.setHorizontalAlignment(SwingConstants.LEFT);
        labelName.setVerticalAlignment(SwingConstants.TOP);
        labelName.setBounds(labelLogo.getX() + labelLogo.getWidth() + Constants.Common.ELEMENTS_CLEARANCE, labelLogo.getY(), 425, 100);
        contentPane.add(labelName);

        labelPromo = new JLabel(Constants.Common.APP_PROMO.toUpperCase());
        labelPromo.setFont(Constants.Common.FONT_HINT);
        labelPromo.setForeground(Constants.Common.BUTTON_COLOR_LOGO);
        labelPromo.setHorizontalAlignment(SwingConstants.CENTER);
        labelPromo.setVerticalAlignment(SwingConstants.TOP);
        labelPromo.setBounds(labelName.getX(), labelName.getY() + labelName.getHeight() + Constants.Common.ELEMENTS_CLEARANCE, labelName.getWidth(), 50);
        contentPane.add(labelPromo);

        createButtonsForAllLocales();

        setContentPane(contentPane);
    }

    /**
     * Creates buttons for all available locales
     */
    private void createButtonsForAllLocales() {
        Locale localeUkr = new Locale("uk", "UA");
        JButton buttonUkr = new JButton(ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, localeUkr).getString("landing_button"));
        buttonUkr.setFont(Constants.Common.FONT_MAIN);
        buttonUkr.setBounds((Constants.Common.LANDING_WINDOW_WIDTH / 2) - (Constants.Common.BUTTON_WIDTH / 2), (labelLogo.getHeight() + Constants.Common.ELEMENTS_CLEARANCE * 6) + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
        buttonUkr.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                Locale.setDefault(localeUkr);
                ResourceBundle.clearCache();
                LandingFrame.this.dispose();
                new MainFrame();
            });
        });
        contentPane.add(buttonUkr);

        Locale localeEng = new Locale("en", "US");
        JButton buttonEng = new JButton(ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, localeEng).getString("landing_button"));
        buttonEng.setFont(Constants.Common.FONT_MAIN);
        buttonEng.setBounds(buttonUkr.getX(), buttonUkr.getY() + Constants.Common.BUTTON_HEIGHT + Constants.Common.ELEMENTS_CLEARANCE, Constants.Common.BUTTON_WIDTH, Constants.Common.BUTTON_HEIGHT);
        buttonEng.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                Locale.setDefault(localeEng);
                ResourceBundle.clearCache();
                LandingFrame.this.dispose();
                new MainFrame();
            });
        });
        contentPane.add(buttonEng);
    }
}
