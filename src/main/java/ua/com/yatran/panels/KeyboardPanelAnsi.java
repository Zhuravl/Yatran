package ua.com.yatran.panels;

import ua.com.yatran.entities.Key;
import ua.com.yatran.interfaces.AbstractKeyboardPanel;

public class KeyboardPanelAnsi extends AbstractKeyboardPanel {

    public KeyboardPanelAnsi(String keyNames) {
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
                        createKey("Tab", 1, 0, 1d),
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
                        createKey(this.keyNames.substring(46, 47), this.keyNames.substring(93, 94), 1, 13, 1.4d)
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
                        createKey("Enter", 2, 12, 1.9d)
                },
                {
                        createKey("Shift", 3, 0, 2d),
                        createKey(this.keyNames.substring(26, 27), this.keyNames.substring(73, 74), 3, 1),
                        createKey(this.keyNames.substring(23, 24), this.keyNames.substring(70, 71), 3, 2),
                        createKey(this.keyNames.substring(20, 21), this.keyNames.substring(67, 68), 3, 3),
                        createKey(this.keyNames.substring(14, 15), this.keyNames.substring(61, 62), 3, 4),
                        createKey(this.keyNames.substring(16, 17), this.keyNames.substring(63, 64), 3, 5),
                        createKey(this.keyNames.substring(17, 18), this.keyNames.substring(64, 65), 3, 6),
                        createKey(this.keyNames.substring(15, 16), this.keyNames.substring(62, 63), 3, 7),
                        createKey(this.keyNames.substring(27, 28), this.keyNames.substring(74, 75), 3, 8),
                        createKey(this.keyNames.substring(28, 29), this.keyNames.substring(75, 76), 3, 9),
                        createKey(this.keyNames.substring(32, 33), this.keyNames.substring(79, 80), 3, 10),
                        createKey("fill", 3, 11, 2.4d)
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
}