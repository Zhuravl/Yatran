package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTableFixture;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.entities.RankingRecord;

import java.util.ArrayList;
import java.util.List;

public class RankingElements extends BaseElements {

    public RankingElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Game page is displayed...");
        boolean result;
        try {
            result = window.panel("rankingTable") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }

    public void clickContinueButton() {
        logger.info("Clicking Continue button...");
        window.button("continueButton").click();
    }

    public List<RankingRecord> getRankingTable() {
        logger.info("Getting ranking table...");
        List<RankingRecord> resultList = null;
        JTableFixture table = window.table("rankingTable");
        if (table.rowCount() > 0) {
            resultList = new ArrayList<>();
            String[][] tableData = table.contents();
            for (int i = 0; i < tableData.length; i++) {
                RankingRecord record = new RankingRecord();
                //Ignoring the first column as it is the position that equals the record index
                record.setUsername(tableData[i][1]);
                record.setScore(tableData[i][2].isEmpty() ? null : Integer.parseInt(tableData[i][2]));
                record.setLevel(tableData[i][3].isEmpty() ? null : Integer.parseInt(tableData[i][3]));
                record.setMistakes(tableData[i][4].isEmpty() ? null : Integer.parseInt(tableData[i][4]));
                record.setDate(tableData[i][5].isEmpty() ? null : TestDataHelper.parseToCalendar(tableData[i][5]));
                resultList.add(record);
            }
        }
        logger.info("The result = " + resultList);
        return resultList;
    }
}
