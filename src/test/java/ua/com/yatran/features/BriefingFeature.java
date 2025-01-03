package ua.com.yatran.features;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.BriefingElements;

public class BriefingFeature extends BaseFeatures {

    private final BriefingElements briefingElements;

    public BriefingFeature(FrameFixture window) {
        briefingElements = new BriefingElements(window);
    }

    /**
     * Starts the game
     */
    public void startGame() {
        logger.info("Starting the game...");
        briefingElements.pressSpaceKey();
        logger.info("The game is started!");
    }
}
