package ua.com.yatran.interfaces;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.JButtonAlt;
import ua.com.yatran.entities.Key;
import ua.com.yatran.entities.KeyBoardLayout;
import ua.com.yatran.entities.KeyConstraint;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the visualization of the keyboard and keys that are needed to press.
 * It is super important to keep the current number of chars in the locale file (the 'key_list' variable) due to hardcoded indexes in this implementation!
 * This class could be a nice candidate for refactoring!
 */
public abstract class AbstractKeyboardPanel extends JPanel {

    protected static final long serialVersionUID = 1L;

    protected String keyNames;
    protected Component[] previousComps;

    private Component shiftButton;

    public AbstractKeyboardPanel(String keyNames) {
        this.setLayout(new KeyBoardLayout());
        this.setBounds(Constants.Common.ELEMENTS_CLEARANCE / 4, Constants.Common.MAIN_WINDOW_HEIGHT - (Constants.Common.MAIN_WINDOW_HEIGHT / 6) - 35, Constants.Common.MAIN_WINDOW_WIDTH - (Constants.Common.ELEMENTS_CLEARANCE / 2), Constants.Common.MAIN_WINDOW_HEIGHT / 5);

        this.keyNames = keyNames;
        this.previousComps = new Component[2];
    }

    /**
     * Resets highlighting for all buttons
     */
    public void resetButtonHighlighting() {
        setBackgroundColor(Constants.Common.BUTTON_COLOR_DEFAULT, previousComps);
    }

    /**
     * Highlights a button with defined key
     *
     * @param key button key to search by
     */
    public void highlightButton(String key) {
        resetButtonHighlighting();
        for (Component component : this.getComponents()) {
            if (component instanceof JButtonAlt button) {
                if (button.getValue().equals(key)) {
                    //The default button text case
                    setBackgroundColor(Constants.Common.BUTTON_COLOR_ACCENT, button);
                    return;
                }
                if (button.getAltValue().equals(key)) {
                    //The alt button text case when pressing the Shift button is required
                    setBackgroundColor(Constants.Common.BUTTON_COLOR_ACCENT, getShiftButton(), button);
                    return;
                }
            }
        }
    }

    /**
     * Returns the Shift button component
     */
    private Component getShiftButton() {
        if (shiftButton == null) {
            for (Component component : this.getComponents()) {
                if (component instanceof JButtonAlt button) {
                    if (button.getText().equalsIgnoreCase("Shift")) {
                        shiftButton = button;
                        break;
                    }
                }
            }
        }
        return shiftButton;
    }

    /**
     * Creates Key instance with the defined span.
     * Use it for the technical keys like 'Shift', 'Enter', 'Backspace', etc.
     *
     * @param text text to set
     * @param x    line index
     * @param y    key index
     * @param span key span
     */
    protected Key createKey(String text, int x, int y, double span) {
        return new Key(text, text, "").setKeyConstraint(new KeyConstraint(x, y, span));
    }

    /**
     * Creates Key instance with the default span.
     * Use it for the normal keys with standard size
     *
     * @param value    key value to set (the uppercased variant will be used as key name)
     * @param altValue key alternative value (key+Shift) to set
     * @param x        line index
     * @param y        key index
     */
    protected Key createKey(String value, String altValue, int x, int y) {
        return new Key(value.toUpperCase(), value, altValue).setKeyConstraint(new KeyConstraint(x, y));
    }

    /**
     * Creates Key instance with the default span.
     * Use it for the normal keys with non-standard size
     *
     * @param value    key value to set (the uppercased variant will be used as key name)
     * @param altValue key alternative value (key+Shift) to set
     * @param x        line index
     * @param y        key index
     * @param span     key span
     */
    protected Key createKey(String value, String altValue, int x, int y, double span) {
        return new Key(value.toUpperCase(), value, altValue).setKeyConstraint(new KeyConstraint(x, y, span));
    }

    /**
     * Creates a button if text is not empty and not equal 'fill', otherwise - label
     *
     * @param value text to set for the button
     */
    protected JComponent createButton(String text, String value, String altValue) {
        JComponent comp;
        if (text == null || text.equalsIgnoreCase("fill")) {
            comp = new JLabel();
        } else {
            comp = new JButtonAlt(text, value, altValue);
        }
        comp.setFocusable(false);
        return comp;
    }

    /**
     * Sets the defined background color to the defined key list
     *
     * @param color color to set as a background for keys
     */
    protected void setBackgroundColor(Color color, Component... comps) {
        for (int index = 0; index < comps.length; index++) {
            Component component = comps[index];
            if (component != null) {
                component.setBackground(color);
                previousComps[index] = component;
            }
        }
    }
}
