package ua.com.yatran.core.helpers;

import io.github.zhuravl.randomname.RandomName;
import io.github.zhuravl.randomname.enums.NamePart;
import ua.com.yatran.constants.Constants;
import ua.com.yatran.enums.Language;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

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
     * Returns random enum value excluding the defined
     *
     * @param clazz enum class to return instance from
     * @param <T>   return class type
     */
    public static <T extends Enum<?>> T getRandomEnum(Class<T> clazz, T excluded) {
        T result;
        do {
            result = getRandomEnum(clazz);
        } while (result == excluded);
        return result;
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

    /**
     * Returns randomly generated number in range from zero to the given bound
     *
     * @param bound the bound of random number generation (exclusive)
     */
    public static Integer getRandomNumber(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * Returns randomly generated name in the specified language
     *
     * @param language language to generate name in
     */
    public static String getRandomName(Language language) {
        switch (language) {
            case ENGLISH:
                return RandomName.getName(io.github.zhuravl.randomname.enums.Language.ENG, NamePart.FIRST);
            case UKRAINIAN:
                return RandomName.getName(io.github.zhuravl.randomname.enums.Language.UKR, NamePart.FIRST);
            default:
                throw new IllegalArgumentException("Provided language is not supported in random name generator - " + language + "!");
        }
    }

    /**
     * Returns current date
     */
    public static Calendar getCurrentDate() {
        return getCurrentDate(Calendar.SECOND, 0);
    }

    /**
     * Returns current date plus defined months
     *
     * @param field the given calendar field (like 'Calendar.MONTH')
     * @param value the value to add to current date
     */
    public static Calendar getCurrentDate(int field, int value) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(field, value);
        return calendar;
    }

    /**
     * Converts string to the Calendar instance
     *
     * @param date date in string format
     */
    public static Calendar parseToCalendar(String date) {
        Calendar calendar = null;
        try {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
            Date parsedDate = dateFormat.parse(date);
            calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
        } catch (ParseException e) {
            //Do nothing here;
        }
        return calendar;
    }
}
