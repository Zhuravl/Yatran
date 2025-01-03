package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class BriefingPageTests extends BaseTest {

    @Test
    public void checkGameStart() {
        Language language = TestDataHelper.getRandomEnum(Language.class);
        landingFeature().selectLanguage(language);
        registerFeature().registerUser(TestDataHelper.getRandomName(language));
        settingsFeature().clickContinueButton();
        briefingFeature().startGame();
        Assert.assertTrue(gameFeature().isPageDisplayed(), "Assert that clicking the Start button starts the game");
    }
}
