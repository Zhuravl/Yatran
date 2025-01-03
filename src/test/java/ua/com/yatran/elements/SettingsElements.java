package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.enums.Language;

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

    public void clickContinueButton() {
        logger.info("Clicking Continue button...");
        window.button("continueButton").click();
    }

    public void setKeyboard(Language keyboard) {
        logger.info("Setting keyboard to " + keyboard + "...");
        window.comboBox("keyboardBox").selectItem(keyboard.getKeyboardName());
    }

    public void setLevel(Integer level) {
        logger.info("Setting level to " + level + "...");
        window.comboBox("levelBox").selectItem(level.toString());
    }

    public void setSoundsOn(Boolean soundsOn) {
        logger.info("Setting sounds on to " + soundsOn + "...");
        window.comboBox("soundBox").selectItem(soundsOn ? 0 : 1);
    }
}
