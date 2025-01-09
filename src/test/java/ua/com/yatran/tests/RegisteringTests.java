package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class RegisteringTests extends BaseTest {

    private final int MIN_USERNAME_LETTERS = 2;
    private final int MAX_USERNAME_LETTERS = 20;

    @Test
    public void checkLettersLess() {
        landingActions().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registeringActions().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS - 1));
        Assert.assertFalse(settingsActions().isPageDisplayed(), "Assert that Continue button does not navigate to the next page when name is less than MIN letters");
    }

    @Test
    public void checkLettersMin() {
        landingActions().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registeringActions().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS));
        Assert.assertTrue(settingsActions().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MIN letters");
    }

    @Test
    public void checkLettersMax() {
        landingActions().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registeringActions().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS));
        Assert.assertTrue(settingsActions().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MAX letters");
    }

    @Test
    public void checkLettersMore() {
        landingActions().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registeringActions().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS + 1), false);
        Assert.assertEquals(registeringActions().getCurrentUsername().length(), MAX_USERNAME_LETTERS, "Assert that user can not enter more than MAX letters in the username field");
    }
}
