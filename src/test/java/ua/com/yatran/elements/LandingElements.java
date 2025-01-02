package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;

public class LandingElements extends BaseElements {

    public LandingElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Landing page is displayed...");
        boolean result;
        try {
            result = window.label("labelName") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }


    public void clickEnglishButton() {
        logger.info("Clicking English button...");
        window.button("languageUSButton").click();
    }

    public void clickUkrainianButton() {
        logger.info("Clicking Ukrainian button...");
        window.button("languageUAButton").click();
    }
}
