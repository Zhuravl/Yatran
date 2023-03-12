package ua.com.yatran.panels.subpanels;

import ua.com.yatran.common.GameContext;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Key;
import ua.com.yatran.entities.KeyBoardLayout;
import ua.com.yatran.entities.KeyConstraint;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ResourceBundle;

/**
 * This class is responsible for the visualization of the keyboard and keys that are needed to press.
 * It is super important to keep the current number of chars in the locale file (the 'key_list' variable) due to hardcoded indexes in this implementation!
 * This class could be a nice candidate for refactoring!
 */
public class KeyboardSubPanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    public KeyboardSubPanel() {
        GUI();
    }

    /**
     * Refreshes GUI to pull the latest data about the selected keyboard
     */
    public void refreshGUI() {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, GameContext.getSettings().getLanguage().getLocale());
        String keyNames = rb.getString("key_list");
        ((JButton) getComponent(0)).setText(keyNames.substring(44, 45).toUpperCase());
        ((JButton) getComponent(1)).setText(keyNames.substring(42, 43).toUpperCase());
        ((JButton) getComponent(2)).setText(keyNames.substring(40, 41).toUpperCase());
        ((JButton) getComponent(3)).setText(keyNames.substring(38, 39).toUpperCase());
        ((JButton) getComponent(4)).setText(keyNames.substring(36, 37).toUpperCase());
        ((JButton) getComponent(5)).setText(keyNames.substring(33, 34).toUpperCase());
        ((JButton) getComponent(6)).setText(keyNames.substring(34, 35).toUpperCase());
        ((JButton) getComponent(7)).setText(keyNames.substring(35, 36).toUpperCase());
        ((JButton) getComponent(8)).setText(keyNames.substring(37, 38).toUpperCase());
        ((JButton) getComponent(9)).setText(keyNames.substring(39, 40).toUpperCase());
        ((JButton) getComponent(10)).setText(keyNames.substring(41, 42).toUpperCase());
        ((JButton) getComponent(11)).setText(keyNames.substring(43, 44).toUpperCase());
        ((JButton) getComponent(12)).setText(keyNames.substring(45, 46).toUpperCase());
        //Skip Backspace button (index=13)
        //Skip Tab button (index=14)
        ((JButton) getComponent(15)).setText(keyNames.substring(24, 25).toUpperCase());
        ((JButton) getComponent(16)).setText(keyNames.substring(21, 22).toUpperCase());
        ((JButton) getComponent(17)).setText(keyNames.substring(18, 19).toUpperCase());
        ((JButton) getComponent(18)).setText(keyNames.substring(10, 11).toUpperCase());
        ((JButton) getComponent(19)).setText(keyNames.substring(12, 13).toUpperCase());
        ((JButton) getComponent(20)).setText(keyNames.substring(13, 14).toUpperCase());
        ((JButton) getComponent(21)).setText(keyNames.substring(11, 12).toUpperCase());
        ((JButton) getComponent(22)).setText(keyNames.substring(19, 20).toUpperCase());
        ((JButton) getComponent(23)).setText(keyNames.substring(22, 23).toUpperCase());
        ((JButton) getComponent(24)).setText(keyNames.substring(25, 26).toUpperCase());
        ((JButton) getComponent(25)).setText(keyNames.substring(29, 30).toUpperCase());
        ((JButton) getComponent(26)).setText(keyNames.substring(31, 32).toUpperCase());
        ((JButton) getComponent(27)).setText(keyNames.substring(46, 47).toUpperCase());
        //Skip Caps button (index=28)
        ((JButton) getComponent(29)).setText(keyNames.substring(6, 7).toUpperCase());
        ((JButton) getComponent(30)).setText(keyNames.substring(4, 5).toUpperCase());
        ((JButton) getComponent(31)).setText(keyNames.substring(2, 3).toUpperCase());
        ((JButton) getComponent(32)).setText(keyNames.substring(0, 1).toUpperCase());
        ((JButton) getComponent(33)).setText(keyNames.substring(8, 9).toUpperCase());
        ((JButton) getComponent(34)).setText(keyNames.substring(9, 10).toUpperCase());
        ((JButton) getComponent(35)).setText(keyNames.substring(1, 2).toUpperCase());
        ((JButton) getComponent(36)).setText(keyNames.substring(3, 4).toUpperCase());
        ((JButton) getComponent(37)).setText(keyNames.substring(5, 6).toUpperCase());
        ((JButton) getComponent(38)).setText(keyNames.substring(7, 8).toUpperCase());
        ((JButton) getComponent(39)).setText(keyNames.substring(30, 31).toUpperCase());
        //Skip Enter button (index=40)
        //Skip Shift button (index=41)
        ((JButton) getComponent(42)).setText(keyNames.substring(26, 27).toUpperCase());
        ((JButton) getComponent(43)).setText(keyNames.substring(23, 24).toUpperCase());
        ((JButton) getComponent(44)).setText(keyNames.substring(20, 21).toUpperCase());
        ((JButton) getComponent(45)).setText(keyNames.substring(14, 15).toUpperCase());
        ((JButton) getComponent(46)).setText(keyNames.substring(16, 17).toUpperCase());
        ((JButton) getComponent(47)).setText(keyNames.substring(17, 18).toUpperCase());
        ((JButton) getComponent(48)).setText(keyNames.substring(15, 16).toUpperCase());
        ((JButton) getComponent(49)).setText(keyNames.substring(27, 28).toUpperCase());
        ((JButton) getComponent(50)).setText(keyNames.substring(28, 29).toUpperCase());
        ((JButton) getComponent(51)).setText(keyNames.substring(32, 33).toUpperCase());
    }

    /**
     * Highlights a button with defined text
     *
     * @param text button text to search by
     */
    public void highlightButton(String text) {
        final int SPACE_KEY_INDEX = 94;
        setBackgroundColor(Constants.Common.BUTTON_COLOR_DEFAULT, getComponents());
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, GameContext.getSettings().getLanguage().getLocale());
        Color accentColor = Constants.Common.BUTTON_COLOR_ACCENT;
        String keyNames = rb.getString("key_list");
        int neededKeyIndex;
        if (" ".equals(text)) {
            neededKeyIndex = SPACE_KEY_INDEX;
        } else {
            neededKeyIndex = keyNames.indexOf(text);
        }
        switch (neededKeyIndex) {
            case 0 -> setBackgroundColor(accentColor, getComponent(32));
            case 1 -> setBackgroundColor(accentColor, getComponent(35));
            case 2 -> setBackgroundColor(accentColor, getComponent(31));
            case 3 -> setBackgroundColor(accentColor, getComponent(36));
            case 4 -> setBackgroundColor(accentColor, getComponent(30));
            case 5 -> setBackgroundColor(accentColor, getComponent(37));
            case 6 -> setBackgroundColor(accentColor, getComponent(29));
            case 7 -> setBackgroundColor(accentColor, getComponent(38));
            case 8 -> setBackgroundColor(accentColor, getComponent(33));
            case 9 -> setBackgroundColor(accentColor, getComponent(34));
            case 10 -> setBackgroundColor(accentColor, getComponent(18));
            case 11 -> setBackgroundColor(accentColor, getComponent(21));
            case 12 -> setBackgroundColor(accentColor, getComponent(19));
            case 13 -> setBackgroundColor(accentColor, getComponent(20));
            case 14 -> setBackgroundColor(accentColor, getComponent(45));
            case 15 -> setBackgroundColor(accentColor, getComponent(48));
            case 16 -> setBackgroundColor(accentColor, getComponent(46));
            case 17 -> setBackgroundColor(accentColor, getComponent(47));
            case 18 -> setBackgroundColor(accentColor, getComponent(17));
            case 19 -> setBackgroundColor(accentColor, getComponent(22));
            case 20 -> setBackgroundColor(accentColor, getComponent(44));
            case 21 -> setBackgroundColor(accentColor, getComponent(16));
            case 22 -> setBackgroundColor(accentColor, getComponent(23));
            case 23 -> setBackgroundColor(accentColor, getComponent(43));
            case 24 -> setBackgroundColor(accentColor, getComponent(15));
            case 25 -> setBackgroundColor(accentColor, getComponent(24));
            case 26 -> setBackgroundColor(accentColor, getComponent(42));
            case 27 -> setBackgroundColor(accentColor, getComponent(49));
            case 28 -> setBackgroundColor(accentColor, getComponent(50));
            case 29 -> setBackgroundColor(accentColor, getComponent(25));
            case 30 -> setBackgroundColor(accentColor, getComponent(39));
            case 31 -> setBackgroundColor(accentColor, getComponent(26));
            case 32 -> setBackgroundColor(accentColor, getComponent(51));
            case 33 -> setBackgroundColor(accentColor, getComponent(5));
            case 34 -> setBackgroundColor(accentColor, getComponent(6));
            case 35 -> setBackgroundColor(accentColor, getComponent(7));
            case 36 -> setBackgroundColor(accentColor, getComponent(4));
            case 37 -> setBackgroundColor(accentColor, getComponent(8));
            case 38 -> setBackgroundColor(accentColor, getComponent(3));
            case 39 -> setBackgroundColor(accentColor, getComponent(9));
            case 40 -> setBackgroundColor(accentColor, getComponent(2));
            case 41 -> setBackgroundColor(accentColor, getComponent(10));
            case 42 -> setBackgroundColor(accentColor, getComponent(1));
            case 43 -> setBackgroundColor(accentColor, getComponent(11));
            case 44 -> setBackgroundColor(accentColor, getComponent(0));
            case 45 -> setBackgroundColor(accentColor, getComponent(12));
            case 46 -> setBackgroundColor(accentColor, getComponent(27));
            case 47 -> setBackgroundColor(accentColor, getComponent(32), getComponent(41));
            case 48 -> setBackgroundColor(accentColor, getComponent(35), getComponent(41));
            case 49 -> setBackgroundColor(accentColor, getComponent(31), getComponent(41));
            case 50 -> setBackgroundColor(accentColor, getComponent(36), getComponent(41));
            case 51 -> setBackgroundColor(accentColor, getComponent(30), getComponent(41));
            case 52 -> setBackgroundColor(accentColor, getComponent(37), getComponent(41));
            case 53 -> setBackgroundColor(accentColor, getComponent(29), getComponent(41));
            case 54 -> setBackgroundColor(accentColor, getComponent(38), getComponent(41));
            case 55 -> setBackgroundColor(accentColor, getComponent(33), getComponent(41));
            case 56 -> setBackgroundColor(accentColor, getComponent(34), getComponent(41));
            case 57 -> setBackgroundColor(accentColor, getComponent(18), getComponent(41));
            case 58 -> setBackgroundColor(accentColor, getComponent(21), getComponent(41));
            case 59 -> setBackgroundColor(accentColor, getComponent(19), getComponent(41));
            case 60 -> setBackgroundColor(accentColor, getComponent(20), getComponent(41));
            case 61 -> setBackgroundColor(accentColor, getComponent(45), getComponent(41));
            case 62 -> setBackgroundColor(accentColor, getComponent(48), getComponent(41));
            case 63 -> setBackgroundColor(accentColor, getComponent(46), getComponent(41));
            case 64 -> setBackgroundColor(accentColor, getComponent(47), getComponent(41));
            case 65 -> setBackgroundColor(accentColor, getComponent(17), getComponent(41));
            case 66 -> setBackgroundColor(accentColor, getComponent(22), getComponent(41));
            case 67 -> setBackgroundColor(accentColor, getComponent(44), getComponent(41));
            case 68 -> setBackgroundColor(accentColor, getComponent(16), getComponent(41));
            case 69 -> setBackgroundColor(accentColor, getComponent(23), getComponent(41));
            case 70 -> setBackgroundColor(accentColor, getComponent(43), getComponent(41));
            case 71 -> setBackgroundColor(accentColor, getComponent(15), getComponent(41));
            case 72 -> setBackgroundColor(accentColor, getComponent(24), getComponent(41));
            case 73 -> setBackgroundColor(accentColor, getComponent(42), getComponent(41));
            case 74 -> setBackgroundColor(accentColor, getComponent(49), getComponent(41));
            case 75 -> setBackgroundColor(accentColor, getComponent(50), getComponent(41));
            case 76 -> setBackgroundColor(accentColor, getComponent(25), getComponent(41));
            case 77 -> setBackgroundColor(accentColor, getComponent(39), getComponent(41));
            case 78 -> setBackgroundColor(accentColor, getComponent(26), getComponent(41));
            case 79 -> setBackgroundColor(accentColor, getComponent(51), getComponent(41));
            case 80 -> setBackgroundColor(accentColor, getComponent(5), getComponent(41));
            case 81 -> setBackgroundColor(accentColor, getComponent(6), getComponent(41));
            case 82 -> setBackgroundColor(accentColor, getComponent(7), getComponent(41));
            case 83 -> setBackgroundColor(accentColor, getComponent(4), getComponent(41));
            case 84 -> setBackgroundColor(accentColor, getComponent(8), getComponent(41));
            case 85 -> setBackgroundColor(accentColor, getComponent(3), getComponent(41));
            case 86 -> setBackgroundColor(accentColor, getComponent(9), getComponent(41));
            case 87 -> setBackgroundColor(accentColor, getComponent(2), getComponent(41));
            case 88 -> setBackgroundColor(accentColor, getComponent(10), getComponent(41));
            case 89 -> setBackgroundColor(accentColor, getComponent(1), getComponent(41));
            case 90 -> setBackgroundColor(accentColor, getComponent(11), getComponent(41));
            case 91 -> setBackgroundColor(accentColor, getComponent(0), getComponent(41));
            case 92 -> setBackgroundColor(accentColor, getComponent(12), getComponent(41));
            case 93 -> setBackgroundColor(accentColor, getComponent(27), getComponent(41));
            case 94 -> setBackgroundColor(accentColor, getComponent(54));
            default ->
                    throw new IllegalArgumentException("There is not a defined pattern for the key with index - '" + neededKeyIndex + "'!");
        }
    }

    /**
     * Initiates and configures the UI elements
     */
    private void GUI() {
        setBounds(0, Constants.Common.MAIN_WINDOW_HEIGHT / 2, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 2);
        setLayout(new KeyBoardLayout());

        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, GameContext.getSettings().getLanguage().getLocale());
        String keyNames = rb.getString("key_list");
        Key[][] keys = new Key[][]{
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

    /**
     * Creates Key instance with the defined span
     *
     * @param text text to set
     * @param x    line index
     * @param y    key index
     * @param span key span
     */
    private Key createKey(String text, int x, int y, double span) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    /**
     * Creates Key instance with the default span
     *
     * @param text text to set
     * @param x    line index
     * @param y    key index
     */
    private Key createKey(String text, int x, int y) {
        return new Key(text).setKeyConstraint(new KeyConstraint(x, y));
    }

    /**
     * Creates a button if text is not empty and not equal 'fill', otherwise - label
     *
     * @param text text to set for the button
     */
    private JComponent createButton(String text) {
        JComponent comp;
        if (text == null || text.equalsIgnoreCase("fill")) {
            comp = new JLabel();
        } else {
            comp = new JButton(text);
        }
        return comp;
    }

    /**
     * Sets the defined background color to the defined key list
     *
     * @param color color to set as a background for keys
     */
    private void setBackgroundColor(Color color, Component... comps) {
        for (Component comp : comps) {
            comp.setBackground(color);
        }
    }
}