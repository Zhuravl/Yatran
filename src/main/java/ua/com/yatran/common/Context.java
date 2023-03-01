package ua.com.yatran.common;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class contains methods that work with dynamic context
 */
public class Context {

    /**
     * Returns all supported in the application keyboards
     */
    public static Object[] getAvailableKeyboards() {
        return Arrays.stream(Language.values()).map(Language::getKeyboardName).toArray();
    }

    /**
     * Returns all supported in the application levels for the specified language (keyboard).
     * A number of levels are calculated by dividing the existing list of chars by pairs.
     * The first level will contain the first two chars.
     *
     * @param language the language to calculate available levels for
     */
    public static String[] getAvailableLevels(Language language) {
        ResourceBundle rb = ResourceBundle.getBundle(Constants.LOCALE_PREFIX, language.getLocale());
        char[] keyArray = rb.getString("key_list").toCharArray();
        int lastLevel = keyArray.length / 2;
        if (keyArray.length % 2 != 0) {
            //Add an extra level to cover the last key without a pair
            lastLevel = (keyArray.length / 2) + 1;
        }
        String[] resultArray = new String[lastLevel];
        for (int i = 1; i <= lastLevel; i++) {
            resultArray[i - 1] = String.valueOf(i);
        }
        return resultArray;
    }

    /**
     * Returns a list with localized sound options
     */
    public static String[] getSoundOptions() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.LOCALE_PREFIX, locale);
        return new String[]{rb.getString("sound_on_label"), rb.getString("sound_off_label")};
    }
}
