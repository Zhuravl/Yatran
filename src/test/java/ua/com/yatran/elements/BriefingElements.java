package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;

import java.awt.event.KeyEvent;

public class BriefingElements extends BaseElements {

    public BriefingElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Briefing page is displayed...");
        boolean result;
        try {
            result = window.label("briefingLabel1") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }

    public void pressSpaceKey() {
        logger.info("Pressing Space key");
        window.pressAndReleaseKeys(KeyEvent.VK_SPACE);
    }
}
