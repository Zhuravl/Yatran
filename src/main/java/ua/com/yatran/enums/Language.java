package ua.com.yatran.enums;

import java.util.Locale;

/**
 * This enum contains all supported in the application languages
 */
public enum Language {

    UKRAINIAN("УКР", new Locale("uk", "UA")),
    ENGLISH("ENG", new Locale("en", "US"));

    private final String keyboardName;
    private final Locale locale;

    Language(String keyboardName, Locale locale) {
        this.keyboardName = keyboardName;
        this.locale = locale;
    }

    public String getKeyboardName() {
        return keyboardName;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Language getByKeyboardName(String name) {
        for (Language language : values()) {
            if (language.getKeyboardName().equals(name)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Can not find Language by name - '" + name + "'!");
    }

    public static Language getByLocale(Locale locale) {
        for (Language language : values()) {
            if (language.getLocale().equals(locale)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Can not find Language by locale - '" + locale + "'!");
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + keyboardName + '\'' +
                ", locale=" + locale +
                '}';
    }
}
