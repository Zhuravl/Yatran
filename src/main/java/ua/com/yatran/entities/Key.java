package ua.com.yatran.entities;

/**
 * This class represents information about a keyboard key
 */
public class Key {

    private String text; //Key text to use for the representation of UI
    private String value; //Key value should equal result after the key pressed
    private String altValue; //Key alternative value should equal result after the Shift+key pressed
    private KeyConstraint keyConstraint; //Key position and size

    public Key(String text, String value, String altValue) {
        this.text = text;
        this.value = value;
        this.altValue = altValue;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
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

    public Key setKeyConstraint(KeyConstraint keyConstraint) {
        this.keyConstraint = keyConstraint;
        return this;
    }

    public KeyConstraint getKeyConstraint() {
        return keyConstraint;
    }
}
