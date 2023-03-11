package ua.com.yatran.entities;

/**
 * This class represents information about a keyboard key
 */
public class Key {

    private String text;
    private KeyConstraint keyConstraint;

    public Key(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Key setKeyConstraint(KeyConstraint keyConstraint) {
        this.keyConstraint = keyConstraint;
        return this;
    }

    public KeyConstraint getKeyConstraint() {
        return keyConstraint;
    }
}
