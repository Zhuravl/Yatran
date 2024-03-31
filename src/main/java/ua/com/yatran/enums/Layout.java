package ua.com.yatran.enums;

import java.util.Arrays;
import java.util.Locale;

import static ua.com.yatran.enums.Keyboard.ANSI;

public enum Layout {

    ANSI_ENG(Keyboard.ANSI, "English", "fjdksla;ghrutyvmbneicwoxqpz,.[']/5674839201-`=\\FJDKSLA:GHRUTYVMBNEICWOXQPZ<>{\"}?%^&$*#(@)!_~+|"),
    ANSI_UKR(Keyboard.ANSI, "Українська", "аовлідфжпркгенмьитушсцщчйзябюхєї.5674839201-ґ=ʼАОВЛІДФЖПРКГЕНМЬИТУШСЦЩЧЙЗЯБЮХЄЇ,%:?;*№(\")!_Ґ+₴"),
    ANSI_UKR_LEGACY(Keyboard.ANSI, "Українська (Застаріла)", ""),
    ANSI_UKR_QWERTY(Keyboard.ANSI, "Українська (QWERTY)", ""),
    ISO_ENG(Keyboard.ISO, "English", "fjdksla;ghrutyvmbneicwoxqpz,.[']/5674839201-`=\\FJDKSLA:GHRUTYVMBNEICWOXQPZ<>{\"}?%^&$*#(@)!_~+|"),
    ISO_UKR(Keyboard.ISO, "Українська", "аовлідфжпркгенмьитушсцщчйзябюхєї.5674839201-ґ=ʼАОВЛІДФЖПРКГЕНМЬИТУШСЦЩЧЙЗЯБЮХЄЇ,%:?;*№(\")!_Ґ+₴"),
    ISO_UKR_LEGACY(Keyboard.ISO, "Українська (Застаріла)", ""),
    ISO_UKR_QWERTY(Keyboard.ISO, "Українська (QWERTY)", "");

    private Keyboard type; //The keyboard layout type to be able to filter for the selected layout
    private String name; //The name to display in a dropdown on GUI
    private String keys; //The set of symbols to use for the game (the order is important as it is used in the constructors for the AbstractKeyboardPanel class!)

    Layout(Keyboard type, String name, String keys) {
        this.type = type;
        this.name = name;
        this.keys = keys;
    }

    public Keyboard getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getKeys() {
        return keys;
    }

    public static Layout getByName(Keyboard type, String name) {
        for (Layout layout : values()) {
            if (layout.getType() == type && layout.getName().equals(name)) {
                return layout;
            }
        }
        throw new IllegalArgumentException("Can not find Layout by name - '" + name + "'!");
    }

    public static Layout getDefault(Locale locale, Keyboard keyboard) {
        switch (locale.getLanguage()) {
            case "en" -> {
                if (keyboard == ANSI) {
                    return ANSI_ENG;
                } else {
                    return ISO_ENG;
                }
            }
            case "uk" -> {
                if (keyboard == ANSI) {
                    return ANSI_UKR;
                } else {
                    return ISO_UKR;
                }
            }
            default -> {
                return ANSI_ENG;
            }
        }
    }

    public static String[] getAllLayoutNamesForType(Keyboard type) {
        return Arrays.stream(Layout.values()).filter(layout -> layout.getType() == type).map(Layout::getName).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return "Layout{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", keys='" + keys + '\'' +
                '}';
    }
}
