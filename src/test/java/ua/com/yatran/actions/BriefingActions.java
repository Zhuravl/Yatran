package ua.com.yatran.actions;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.BriefingElements;

public class BriefingActions extends BaseActions {

    private final BriefingElements briefingElements;

    public BriefingActions(FrameFixture window) {
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
