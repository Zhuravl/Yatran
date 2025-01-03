package ua.com.yatran.features;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.core.entities.GameSession;
import ua.com.yatran.elements.SettingsElements;

public class SettingsFeature extends BaseFeatures {

    private final SettingsElements settingsElements;

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

    /**
     * Clicks Continue button without changing any settings
     */
    public void clickContinueButton() {
        logger.info("Clicking Continue button...");
        settingsElements.clickContinueButton();
    }

    /**
     * Sets up the game settings
     *
     * @param session game session with settings to set
     */
    public void setSettings(GameSession session) {
        logger.info("Setting up game settings [" + session + "]...");
        settingsElements.setKeyboard(session.getKeyboard());
        settingsElements.setLevel(session.getLevel());
        settingsElements.setSoundsOn(session.getSoundsOn());
        clickContinueButton();
        logger.info("The game settings are successfully set!");
    }
}
