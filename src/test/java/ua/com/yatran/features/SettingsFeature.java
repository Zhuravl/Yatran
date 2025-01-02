package ua.com.yatran.features;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.SettingsElements;

public class SettingsFeature extends BaseFeatures {

    private SettingsElements settingsElements;

    public SettingsFeature(FrameFixture window) {
        settingsElements = new SettingsElements(window);
    }

    /**
     * Returns true if Settings page is displayed
     */
    public boolean isPageDisplayed() {
        logger.info("Checking if Settings page is displayed...");
        boolean result = settingsElements.isPageDisplayed();
        logger.info("The result = " + result);
        return result;
    }
}
