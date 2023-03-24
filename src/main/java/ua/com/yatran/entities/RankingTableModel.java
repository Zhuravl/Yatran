package ua.com.yatran.entities;

import ua.com.yatran.helpers.GameContext;

import javax.swing.table.AbstractTableModel;
import java.util.Comparator;
import java.util.List;

/**
 * This class implements fetching data for the Ranking Table
 */
public class RankingTableModel extends AbstractTableModel {

    private static final int RANKING_TABLE_SIZE = 15;

    private final String[] columnNames;
    private Object[][] data;
    private static int resultIndexLocal;

    public RankingTableModel() {
        columnNames = GameContext.getRankingTableColumnNames();
        data = new String[][]{}; //create empty table for app initialisation
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Refreshes the data by pulling the latest changes
     *
     * @param record the current user record to show it among the general results
     */
    public void refreshData(RankingRecord record) {
        data = getRankingTableData(record);
    }

    /**
     * Returns the current user row index to highlight it in the general table
     */
    public int getCurrentUserRowIndex() {
        return resultIndexLocal;
    }

    /**
     * Returns a list with processed for displaying ranking table data
     */
    private static String[][] getRankingTableData(RankingRecord currentRecord) {
        String[][] resultArray;
        List<RankingRecord> recordList = GameContext.getRecordList();
        if (recordList == null || recordList.size() == 0) {
            resultArray = new String[][]{};
        } else {
            recordList.sort(Comparator.comparing(RankingRecord::getDate));
            recordList.sort(Comparator.comparing(RankingRecord::getUsername));
            recordList.sort(Comparator.comparing(RankingRecord::getMistakes));
            recordList.sort(Comparator.comparing(RankingRecord::getLevel).reversed());
            recordList.sort(Comparator.comparing(RankingRecord::getScore).reversed());
            int resultIndexGeneral = getRecordIndex(currentRecord, recordList);
            int maxIndex = Math.min(RANKING_TABLE_SIZE, recordList.size());
            resultArray = new String[maxIndex][];
            if (resultIndexGeneral < RANKING_TABLE_SIZE) {
                for (int i = 0; i < maxIndex; i++) {
                    resultArray[i] = getRecordForTable(i + 1, recordList.get(i));
                }
                resultIndexLocal = resultIndexGeneral;
            } else {
                for (int i = 0; i < (RANKING_TABLE_SIZE - 2); i++) {
                    resultArray[i] = getRecordForTable(i + 1, recordList.get(i));
                }
                resultArray[RANKING_TABLE_SIZE - 2] = getRecordForTable(null, null);
                resultArray[RANKING_TABLE_SIZE - 1] = getRecordForTable(resultIndexGeneral, recordList.get(resultIndexGeneral));
                resultIndexLocal = (RANKING_TABLE_SIZE - 1);
            }
        }
        return resultArray;
    }

    /**
     * Returns the index of the defined record in the general record list or -1 if it's absent
     *
     * @param rankingRecord record to search index of
     * @param rankingList   list to search for record in
     */
    private static int getRecordIndex(RankingRecord rankingRecord, List<RankingRecord> rankingList) {
        for (int index = 0; index < rankingList.size(); index++) {
            if (rankingRecord.equals(rankingList.get(index))) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns the string representation of the record with it place in the general ranking table or empty array for the NULL values
     *
     * @param place  record place in the table
     * @param record record details
     */
    private static String[] getRecordForTable(Integer place, RankingRecord record) {
        final String EMPTY_PLACE = ".....";
        final String EMPTY_VALUE = "";

        String placeString = place != null ? String.valueOf(place) : EMPTY_PLACE;
        String usernameString = (record != null && record.getUsername() != null) ? record.getUsername() : EMPTY_VALUE;
        String scoreString = (record != null && record.getScore() != null) ? String.valueOf(record.getScore()) : EMPTY_VALUE;
        String levelString = (record != null && record.getLevel() != null) ? String.valueOf(record.getLevel()) : EMPTY_VALUE;
        String mistakesString = (record != null && record.getMistakes() != null) ? String.valueOf(record.getMistakes()) : EMPTY_VALUE;
        String dateString = (record != null && record.getScore() != null) ? record.getDateFormatted() : EMPTY_VALUE;
        return new String[]{placeString, usernameString, scoreString, levelString, mistakesString, dateString};
    }
}
