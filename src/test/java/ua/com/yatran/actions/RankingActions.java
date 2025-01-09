package ua.com.yatran.actions;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.RankingElements;
import ua.com.yatran.entities.RankingRecord;

import java.util.List;

public class RankingActions extends BaseActions {

    private RankingElements rankingElements;

    public RankingActions(FrameFixture window) {
        rankingElements = new RankingElements(window);
    }

    /**
     * Clicks Continue button without changing any settings
     */
    public void clickContinueButton() {
        logger.info("Clicking Continue button...");
        rankingElements.clickContinueButton();
    }

    /**
     * Returns the current ranking table
     */
    public List<RankingRecord> getRankingTable() {
        logger.info("Getting the ranking table...");
        List<RankingRecord> rankingList = rankingElements.getRankingTable();
        logger.info("The result table = " + rankingList);
        return rankingList;
    }
}
