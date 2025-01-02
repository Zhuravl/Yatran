package ua.com.yatran.features;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.LandingElements;
import ua.com.yatran.enums.Language;
import ua.com.yatran.frames.MainFrame;
import ua.com.yatran.tests.BaseTest;

public class LandingFeature extends BaseFeatures {

    private LandingElements landingElements;
    private BaseTest baseTest; //Link for the ability to switch the frame context after work with the Landing page

    public LandingFeature(FrameFixture window, BaseTest baseTest) {
        landingElements = new LandingElements(window);
        this.baseTest = baseTest;
    }

    /**
     * Selects the provided language on the landing page
     *
     * @param language language to be selected
     */
    public void selectLanguage(Language language) {
        logger.info("Selecting language: " + language);
        switch (language) {
            case ENGLISH:
                landingElements.clickEnglishButton();
                break;
            case UKRAINIAN:
                landingElements.clickUkrainianButton();
                break;
            default:
                throw new IllegalArgumentException("Provided language is not supported - " + language + "!");
        }
        baseTest.switchFrameContext(MainFrame.class); //The special step required because of the specific application implementation when the frame is changed after the language selection
        logger.info("The language has been successfully selected!");
    }
}
