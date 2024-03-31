package ua.com.yatran.enums;

import java.util.Arrays;
import java.util.Locale;

/**
 * This enum contains all supported keyboard layouts
 */
public enum Keyboard {

    ANSI("ANSI"), // American Standard Keyboard (the Enter is in the Caps Lock line)
    ISO("ISO"); // European Standard Keyboard (the Enter is in the Tab line)

    private String name;

    Keyboard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Keyboard getDefault(Locale locale) {
        return switch (locale.getLanguage()) {
            case "uk" -> ISO;
            case "en" -> ANSI;
            default -> ANSI;
        };
    }

    public static Keyboard getByName(String name) {
        for (Keyboard keyboard : values()) {
            if (keyboard.getName().equals(name)) {
                return keyboard;
            }
        }
        throw new IllegalArgumentException("Can not find Keyboard by name - '" + name + "'!");
    }

    public static Object[] getAllKeyboardNames() {
        return Arrays.stream(Keyboard.values()).map(Keyboard::getName).toArray();
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "name='" + name + '\'' +
                '}';
    }
}
