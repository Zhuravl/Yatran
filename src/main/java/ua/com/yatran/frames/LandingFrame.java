package ua.com.yatran.frames;

import ua.com.yatran.common.Constants;

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
    public static final Font FONT_PREFERENCE = new Font("Tahoma", Font.PLAIN, 26);

    private JLabel labelLogo;
    private JButton buttonUkrainian;
    private JButton buttonEnglish;
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

    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right, H_FRAME - insets.bottom - insets.top);

        labelLogo = new JLabel(new ImageIcon("src/main/resources/images/Yatran-logo.png"));
        labelLogo.setBounds(0, 0, 700, 300);
        contentPane.add(labelLogo);

        buttonUkrainian = new JButton("Українською!");
        buttonUkrainian.setFont(FONT_PREFERENCE);
        buttonUkrainian.setBounds(labelLogo.getX() + 150, labelLogo.getY() + 300, 400, 100);
        buttonUkrainian.setFocusPainted(false);
        buttonUkrainian.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                LandingFrame.this.dispose();
                Locale.setDefault(new Locale("uk", "UA"));
                ResourceBundle.clearCache();
                new MainFrame();
            });
        });
        contentPane.add(buttonUkrainian);

        buttonEnglish = new JButton("In English!");
        buttonEnglish.setFont(FONT_PREFERENCE);
        buttonEnglish.setBounds(200, 300, 200, 30);
        buttonEnglish.setBounds(buttonUkrainian.getX(), buttonUkrainian.getY() + 120, 400, 100);
        buttonEnglish.setFocusPainted(false);
        buttonEnglish.addActionListener(e -> {
            EventQueue.invokeLater(() -> {
                LandingFrame.this.dispose();
                Locale.setDefault(new Locale("en", "US"));
                ResourceBundle.clearCache();
                new MainFrame();
            });
        });
        contentPane.add(buttonEnglish);

        setContentPane(contentPane);
    }
}
