package ua.com.yatran.core.helpers;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import java.util.Random;
import java.util.ResourceBundle;

public class TestDataHelper {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "/*-+.,~!@#$%^&()_=";
    private static final String ALL_CHARACTERS = LETTERS + LETTERS.toLowerCase() + DIGITS + SPECIALS;
    private static final Random RANDOM = new Random();

    private TestDataHelper() {
    }

    /**
     * Returns random enum value
     *
     * @param clazz enum class to return instance from
     * @param <T>   return class type
     */
    public static <T extends Enum<?>> T getRandomEnum(Class<T> clazz) {
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Returns the expected registration label text corresponding to the selected language
     *
     * @param language language to get text for
     */
    public static String getRegistrationLabelText(Language language) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, language.getLocale());
        return rb.getString("username_label");
    }

    /**
     * Returns randomly generated string with the defined length
     *
     * @param length number of symbols in generated string
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHARACTERS.charAt(new Random().nextInt(ALL_CHARACTERS.length())));
        }
        return sb.toString();
    }
}
