package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;

public class SettingsElements extends BaseElements {

    public SettingsElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Settings page is displayed...");
        boolean result;
        try {
            result = window.label("settingsLabel") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }
}
