package ua.com.yatran.features;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.core.entities.GameInfo;
import ua.com.yatran.elements.GameElements;

public class GameFeature extends BaseFeatures {

    private GameElements gameElements;

    public GameFeature(FrameFixture window) {
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
}
