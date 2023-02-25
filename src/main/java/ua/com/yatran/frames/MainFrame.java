package ua.com.yatran.frames;

import ua.com.yatran.common.Constants;
import ua.com.yatran.panels.PreferencesPanel;
import ua.com.yatran.panels.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MainFrame extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final int W_FRAME = 1080;
    public static final int H_FRAME = (int) (W_FRAME / ((Math.sqrt(5) + 1) / 2));

    private JPanel contentPane, registerPanel, preferencesPanel;
    private Insets insets;

    public MainFrame() {
        super(Constants.APP_NAME);
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();

        GUI();
    }

    private void GUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        contentPane.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right, H_FRAME - insets.bottom - insets.top);

        registerPanel = new RegisterPanel(contentPane);
        preferencesPanel = new PreferencesPanel(contentPane);

        contentPane.add(registerPanel);
        contentPane.add(preferencesPanel);

        setContentPane(contentPane);
    }
}
