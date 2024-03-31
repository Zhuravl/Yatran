package ua.com.yatran.entities;

import javax.swing.*;

public class JButtonAlt extends JButton {

    private String value;
    private String altValue;

    public JButtonAlt(String text, String value, String altValue) {
        super(text);
        this.value = value;
        this.altValue = altValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAltValue() {
        return altValue;
    }

    public void setAltValue(String altValue) {
        this.altValue = altValue;
    }
}
