package ua.com.yatran.panels.subpanels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Key;
import ua.com.yatran.entities.KeyBoardLayout;
import ua.com.yatran.entities.KeyConstraint;

import javax.swing.*;
import java.io.Serial;
import java.util.ResourceBundle;

public class KeyboardSubPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    private Key[][] keys;

    public KeyboardSubPanel() {
        this.setBounds(0, Constants.Common.MAIN_WINDOW_HEIGHT / 2, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 2);
        this.setLayout(new KeyBoardLayout());

        initiateKeys();
    }

    /**
     * Refreshes GUI to pull the latest data
     */
    public void refreshGUI() {

    }

    private void initiateKeys() {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, GameContext.getSettings().getLanguage().getLocale());
        String keyNames = rb.getString("key_list");
        keys = new Key[][]{
                {
                        createKey(keyNames.substring(44, 45).toUpperCase(), 0, 0),
                        createKey(keyNames.substring(42, 43).toUpperCase(), 0, 1),
                        createKey(keyNames.substring(40, 41).toUpperCase(), 0, 2),
                        createKey(keyNames.substring(38, 39).toUpperCase(), 0, 3),
                        createKey(keyNames.substring(36, 37).toUpperCase(), 0, 4),
                        createKey(keyNames.substring(33, 34).toUpperCase(), 0, 5),
                        createKey(keyNames.substring(34, 35).toUpperCase(), 0, 6),
                        createKey(keyNames.substring(35, 36).toUpperCase(), 0, 7),
                        createKey(keyNames.substring(37, 38).toUpperCase(), 0, 8),
                        createKey(keyNames.substring(39, 40).toUpperCase(), 0, 9),
                        createKey(keyNames.substring(41, 42).toUpperCase(), 0, 10),
                        createKey(keyNames.substring(43, 44).toUpperCase(), 0, 11),
                        createKey(keyNames.substring(45, 46).toUpperCase(), 0, 12),
                        createKey("Backspace", 0, 13, 2d)
                },
                {
                        createKey("Tab", 1, 0, 1d),
                        createKey(keyNames.substring(24, 25).toUpperCase(), 1, 1),
                        createKey(keyNames.substring(21, 22).toUpperCase(), 1, 2),
                        createKey(keyNames.substring(18, 19).toUpperCase(), 1, 3),
                        createKey(keyNames.substring(10, 11).toUpperCase(), 1, 4),
                        createKey(keyNames.substring(12, 13).toUpperCase(), 1, 5),
                        createKey(keyNames.substring(13, 14).toUpperCase(), 1, 6),
                        createKey(keyNames.substring(11, 12).toUpperCase(), 1, 7),
                        createKey(keyNames.substring(19, 20).toUpperCase(), 1, 8),
                        createKey(keyNames.substring(22, 23).toUpperCase(), 1, 9),
                        createKey(keyNames.substring(25, 26).toUpperCase(), 1, 10),
                        createKey(keyNames.substring(29, 30).toUpperCase(), 1, 11),
                        createKey(keyNames.substring(31, 32).toUpperCase(), 1, 12),
                        createKey(keyNames.substring(46, 47).toUpperCase(), 1, 13, 1.5d)
                },
                {
                        createKey("Caps", 2, 0, 1.5d),
                        createKey(keyNames.substring(6, 7).toUpperCase(), 2, 1),
                        createKey(keyNames.substring(4, 5).toUpperCase(), 2, 2),
                        createKey(keyNames.substring(2, 3).toUpperCase(), 2, 3),
                        createKey(keyNames.substring(0, 1).toUpperCase(), 2, 4),
                        createKey(keyNames.substring(8, 9).toUpperCase(), 2, 5),
                        createKey(keyNames.substring(9, 10).toUpperCase(), 2, 6),
                        createKey(keyNames.substring(1, 2).toUpperCase(), 2, 7),
                        createKey(keyNames.substring(3, 4).toUpperCase(), 2, 8),
                        createKey(keyNames.substring(5, 6).toUpperCase(), 2, 9),
                        createKey(keyNames.substring(7, 8).toUpperCase(), 2, 10),
                        createKey(keyNames.substring(30, 31).toUpperCase(), 2, 11),
                        createKey("Enter", 2, 12, 1.5d)
                },
                {
                        createKey("Shift", 3, 0, 2d),
                        createKey(keyNames.substring(26, 27).toUpperCase(), 3, 1),
                        createKey(keyNames.substring(23, 24).toUpperCase(), 3, 2),
                        createKey(keyNames.substring(20, 21).toUpperCase(), 3, 3),
                        createKey(keyNames.substring(14, 15).toUpperCase(), 3, 4),
                        createKey(keyNames.substring(16, 17).toUpperCase(), 3, 5),
                        createKey(keyNames.substring(17, 18).toUpperCase(), 3, 6),
                        createKey(keyNames.substring(15, 16).toUpperCase(), 3, 7),
                        createKey(keyNames.substring(27, 28).toUpperCase(), 3, 8),
                        createKey(keyNames.substring(28, 29).toUpperCase(), 3, 9),
                        createKey(keyNames.substring(32, 33).toUpperCase(), 3, 10),
                        createKey("fill", 3, 11, 0.5d)
                },
                {
                        createKey("fill", 4, 0, 4d),
                        createKey(" ", 4, 1, 6d),
                        createKey("fill", 4, 2, 5d)
                },
        };

        for (int row = 0; row < keys.length; row++) {
            for (int col = 0; col < keys[row].length; col++) {
                Key key = keys[row][col];
                add(createButton(key.getText()), key.getKeyConstraint());
            }
        }
    }

    private Key createKey(String text, int x, int y, double span) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    private Key createKey(String text, int x, int y) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y));
    }

    private JComponent createButton(String text) {
        JComponent comp = null;
        if (text == null || text.equalsIgnoreCase("fill")) {
            comp = new JLabel();
        } else {
            comp = new JButton(text);
        }
        return comp;
    }
}