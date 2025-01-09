package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class BriefingTests extends BaseTest {

    @Test
    public void checkGameStart() {
        Language language = TestDataHelper.getRandomEnum(Language.class);

        landingActions().selectLanguage(language);
        registeringActions().registerUser(TestDataHelper.getRandomName(language));
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        Assert.assertTrue(gameActions().isPageDisplayed(), "Assert that clicking the Start button starts the game");
    }
}
