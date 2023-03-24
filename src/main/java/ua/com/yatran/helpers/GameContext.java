package ua.com.yatran.helpers;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.entities.Settings;
import ua.com.yatran.enums.Language;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * This class contains methods that work with dynamic context
 */
public class GameContext {

    private static final int CHARACTERS_IN_LEVEL = 2;

    private static Settings settings;
    private static RankingRecord record;

    /**
     * Sets the defined settings to context
     *
     * @param settings settings instance to add
     */
    public static void setSettings(Settings settings) {
        GameContext.settings = settings;
    }

    /**
     * Returns the settings instance with user preferences
     */
    public static Settings getSettings() {
        return settings;
    }

    /**
     * Sets the defined record to context
     *
     * @param record user record to add
     */
    public static void setRecord(RankingRecord record) {
        GameContext.record = record;
    }

    /**
     * Returns the ranking record instance with user results
     */
    public static RankingRecord getRecord() {
        return record;
    }

    /**
     * Saves the current record to the existing records list
     */
    public static void saveRecordToDisk() {
        List<RankingRecord> recordList = getRecordList();
        recordList.add(getRecord());
        try (
                FileOutputStream fout = new FileOutputStream(Constants.Common.RANKING_FILE_NAME, false);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
        ) {
            oos.writeObject(recordList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, language.getLocale());
        char[] keyArray = rb.getString("key_list").toCharArray();
        int lastLevel = keyArray.length / CHARACTERS_IN_LEVEL;
        if (keyArray.length % CHARACTERS_IN_LEVEL != 0) {
            //Add an extra level to cover the last key without a pair
            lastLevel = lastLevel + 1;
        }
        String[] resultArray = new String[lastLevel];
        for (int i = 1; i <= lastLevel; i++) {
            resultArray[i - 1] = String.valueOf(i);
        }
        return resultArray;
    }

    /**
     * Returns the last available level for the current keyboard language
     */
    public static int getMaxLevel() {
        String[] levelsArray = getAvailableLevels(getSettings().getLanguage());
        return Integer.parseInt(levelsArray[levelsArray.length - 1]);
    }

    /**
     * Returns the MAX allowed mistakes for the current keyboard language.
     * The number of allowed mistakes should equal 10% of all symbols typed during the game (during remaining levels, including the current).
     */
    public static int getMaxMistakes() {
        int levelsRemaining = (getAvailableLevels(getSettings().getLanguage()).length - getSettings().getLevel()) + 1;
        return (int) (levelsRemaining * Constants.Game.LEVEL_CHARACTER_SIZE * 0.1);
    }

    /**
     * Returns a list with localized sound options
     */
    public static String[] getSoundOptions() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);
        return new String[]{rb.getString("sound_on_label"), rb.getString("sound_off_label")};
    }

    /**
     * Returns a list with localized ranking table column names
     */
    public static String[] getRankingTableColumnNames() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);
        return new String[]{rb.getString("ranking_table_place"), rb.getString("ranking_table_name"), rb.getString("ranking_table_scores"), rb.getString("ranking_table_level"), rb.getString("ranking_table_mistakes"), rb.getString("ranking_table_date")};
    }

    /**
     * Returns the saved previously ranking record list
     */
    public static List<RankingRecord> getRecordList() {
        List<RankingRecord> resultList = new ArrayList<>();
        try (
                FileInputStream streamIn = new FileInputStream(Constants.Common.RANKING_FILE_NAME);
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
        ) {
            resultList = (List<RankingRecord>) objectinputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Returns randomly created array of characters based on the current level and keyboard preference
     */
    public static String[] getRandomLettersForLevel() {
        String[] resultList = new String[Constants.Game.LEVEL_CHARACTER_SIZE];
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, getSettings().getLanguage().getLocale());
        String keys = " " + rb.getString("key_list");
        int maxIndex = (getSettings().getLevel() * CHARACTERS_IN_LEVEL) + 1;
        if (maxIndex > keys.length()) {
            //Normalize the MAX index for the last level with an odd number of characters
            maxIndex = maxIndex - 1;
        }
        for (int i = 0; i < Constants.Game.LEVEL_CHARACTER_SIZE; i++) {
            int randomKey = new Random().nextInt(maxIndex);
            resultList[i] = (String.valueOf(keys.charAt(randomKey)));
        }
        return resultList;
    }
}
