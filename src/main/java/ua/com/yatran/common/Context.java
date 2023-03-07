package ua.com.yatran.common;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.enums.Language;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

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
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, language.getLocale());
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
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);
        return new String[]{rb.getString("sound_on_label"), rb.getString("sound_off_label")};
    }

    /**
     * Returns a list with localized ranking table column names
     */
    public static String[] getRankingTableColumnNames() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle(Constants.Common.LOCALE_PREFIX, locale);
        return new String[]{rb.getString("ranking_table_place"), rb.getString("ranking_table_name"), rb.getString("ranking_table_scores"), rb.getString("ranking_table_level"), rb.getString("ranking_table_speed"), rb.getString("ranking_table_mistakes"), rb.getString("ranking_table_date")};
    }

    /**
     * Adds the defined record to the existing records list
     *
     * @param record record to add to the result list
     */
    public static void addToRecordList(RankingRecord record) {
        List<RankingRecord> recordList = getRecordList();
        recordList.add(record);
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
}
