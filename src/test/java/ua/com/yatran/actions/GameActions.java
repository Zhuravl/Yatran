package ua.com.yatran.actions;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.core.entities.GameInfo;
import ua.com.yatran.core.helpers.GameResultsHelper;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.elements.GameElements;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.enums.Language;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class GameActions extends BaseActions {

    private GameElements gameElements;

    public GameActions(FrameFixture window) {
        gameElements = new GameElements(window);
    }

    /**
     * Returns the current game info
     */
    public GameInfo getGameInfo() {
        logger.info("Getting game info...");
        GameInfo result = new GameInfo();
        result.setKeyboard(gameElements.getKeyboard());
        result.setLevel(gameElements.getLevel());
        result.setScores(gameElements.getScores());
        result.setMistakes(gameElements.getMistakes());
        logger.info("The result = " + result);
        return result;
    }

    /**
     * Returns true if Game page is displayed
     */
    public boolean isPageDisplayed() {
        logger.info("Checking if the Game page is displayed...");
        boolean result = gameElements.isPageDisplayed();
        logger.info("The result = " + result);
        return result;
    }

    /**
     * Skips the game, setting the one default result to be able to read it on the Ranking page
     */
    public void skipGame() {
        skipGame(Arrays.asList(new RankingRecord(TestDataHelper.getRandomName(Language.ENGLISH), 1, 1, 0, TestDataHelper.getCurrentDate(Calendar.YEAR, -1))));
    }

    /**
     * Skips the game and saves the provided records to be able to read them on the Ranking page
     *
     * @param records records of the game to save
     */
    public void skipGame(List<RankingRecord> records) {
        logger.info("Skipping the game for records: " + records + "...");
        if (records != null && !records.isEmpty()) {
            GameResultsHelper.saveRecords(records);
        }
        gameElements.pressSkipGameCombination();
        logger.info("The game has been successfully skipped!");
    }
}
