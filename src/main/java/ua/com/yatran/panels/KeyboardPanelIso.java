package ua.com.yatran.panels;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.Key;
import ua.com.yatran.interfaces.AbstractKeyboardPanel;

import java.awt.*;

public class KeyboardPanelIso extends AbstractKeyboardPanel {

    public KeyboardPanelIso(String keyNames) {
        super(keyNames);
        Key[][] keys = new Key[][]{
                {
                        createKey(this.keyNames.substring(44, 45), this.keyNames.substring(91, 92), 0, 0),
                        createKey(this.keyNames.substring(42, 43), this.keyNames.substring(89, 90), 0, 1),
                        createKey(this.keyNames.substring(40, 41), this.keyNames.substring(87, 88), 0, 2),
                        createKey(this.keyNames.substring(38, 39), this.keyNames.substring(85, 86), 0, 3),
                        createKey(this.keyNames.substring(36, 37), this.keyNames.substring(83, 84), 0, 4),
                        createKey(this.keyNames.substring(33, 34), this.keyNames.substring(80, 81), 0, 5),
                        createKey(this.keyNames.substring(34, 35), this.keyNames.substring(81, 82), 0, 6),
                        createKey(this.keyNames.substring(35, 36), this.keyNames.substring(82, 83), 0, 7),
                        createKey(this.keyNames.substring(37, 38), this.keyNames.substring(84, 85), 0, 8),
                        createKey(this.keyNames.substring(39, 40), this.keyNames.substring(86, 87), 0, 9),
                        createKey(this.keyNames.substring(41, 42), this.keyNames.substring(88, 89), 0, 10),
                        createKey(this.keyNames.substring(43, 44), this.keyNames.substring(90, 91), 0, 11),
                        createKey(this.keyNames.substring(45, 46), this.keyNames.substring(92, 93), 0, 12),
                        createKey("Backspace", 0, 13, 1.4d)
                },
                {
                        createKey("Tab", 1, 0, 1.4d),
                        createKey(this.keyNames.substring(24, 25), this.keyNames.substring(71, 72), 1, 1),
                        createKey(this.keyNames.substring(21, 22), this.keyNames.substring(68, 69), 1, 2),
                        createKey(this.keyNames.substring(18, 19), this.keyNames.substring(65, 66), 1, 3),
                        createKey(this.keyNames.substring(10, 11), this.keyNames.substring(57, 58), 1, 4),
                        createKey(this.keyNames.substring(12, 13), this.keyNames.substring(59, 60), 1, 5),
                        createKey(this.keyNames.substring(13, 14), this.keyNames.substring(60, 61), 1, 6),
                        createKey(this.keyNames.substring(11, 12), this.keyNames.substring(58, 59), 1, 7),
                        createKey(this.keyNames.substring(19, 20), this.keyNames.substring(66, 67), 1, 8),
                        createKey(this.keyNames.substring(22, 23), this.keyNames.substring(69, 70), 1, 9),
                        createKey(this.keyNames.substring(25, 26), this.keyNames.substring(72, 73), 1, 10),
                        createKey(this.keyNames.substring(29, 30), this.keyNames.substring(76, 77), 1, 11),
                        createKey(this.keyNames.substring(31, 32), this.keyNames.substring(78, 79), 1, 12),
                        createKey("Enter", 1, 13, 1d)
                },
                {
                        createKey("Caps", 2, 0, 1.5d),
                        createKey(this.keyNames.substring(6, 7), this.keyNames.substring(53, 54), 2, 1),
                        createKey(this.keyNames.substring(4, 5), this.keyNames.substring(51, 52), 2, 2),
                        createKey(this.keyNames.substring(2, 3), this.keyNames.substring(49, 50), 2, 3),
                        createKey(this.keyNames.substring(0, 1), this.keyNames.substring(47, 48), 2, 4),
                        createKey(this.keyNames.substring(8, 9), this.keyNames.substring(55, 56), 2, 5),
                        createKey(this.keyNames.substring(9, 10), this.keyNames.substring(56, 57), 2, 6),
                        createKey(this.keyNames.substring(1, 2), this.keyNames.substring(48, 49), 2, 7),
                        createKey(this.keyNames.substring(3, 4), this.keyNames.substring(50, 51), 2, 8),
                        createKey(this.keyNames.substring(5, 6), this.keyNames.substring(52, 53), 2, 9),
                        createKey(this.keyNames.substring(7, 8), this.keyNames.substring(54, 55), 2, 10),
                        createKey(this.keyNames.substring(30, 31), this.keyNames.substring(77, 78), 2, 11),
                        createKey(this.keyNames.substring(46, 47), this.keyNames.substring(93, 94), 2, 12),
                        createKey("fill", 2, 13, 0.9d)
                },
                {
                        createKey("Shift", 3, 0, 1.3d),
                        createKey(this.keyNames.substring(46, 47), this.keyNames.substring(93, 94), 3, 1),
                        createKey(this.keyNames.substring(26, 27), this.keyNames.substring(73, 74), 3, 2),
                        createKey(this.keyNames.substring(23, 24), this.keyNames.substring(70, 71), 3, 3),
                        createKey(this.keyNames.substring(20, 21), this.keyNames.substring(67, 68), 3, 4),
                        createKey(this.keyNames.substring(14, 15), this.keyNames.substring(61, 62), 3, 5),
                        createKey(this.keyNames.substring(16, 17), this.keyNames.substring(63, 64), 3, 6),
                        createKey(this.keyNames.substring(17, 18), this.keyNames.substring(64, 65), 3, 7),
                        createKey(this.keyNames.substring(15, 16), this.keyNames.substring(62, 63), 3, 8),
                        createKey(this.keyNames.substring(27, 28), this.keyNames.substring(74, 75), 3, 9),
                        createKey(this.keyNames.substring(28, 29), this.keyNames.substring(75, 76), 3, 10),
                        createKey(this.keyNames.substring(32, 33), this.keyNames.substring(79, 80), 3, 11),
                        createKey("fill", 3, 12, 2.1d)
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
                add(createButton(key.getText(), key.getValue(), key.getAltValue()), key.getKeyConstraint());
            }
        }
    }

    @Override
    public void highlightButton(String key) {
        final int SPACE_KEY_INDEX = 94;
        resetButtonHighlighting();
        Color accentColor = Constants.Common.BUTTON_COLOR_ACCENT;
        int neededKeyIndex;
        if (" ".equals(key)) {
            neededKeyIndex = SPACE_KEY_INDEX;
        } else {
            neededKeyIndex = keyNames.indexOf(key);
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
}