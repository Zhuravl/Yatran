package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class RegisteringPageTest extends BaseTest {

    private final int MIN_USERNAME_LETTERS = 2;
    private final int MAX_USERNAME_LETTERS = 20;

    @Test
    public void checkLettersLess() {
        landingPage().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerPage().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS - 1));
        Assert.assertFalse(settingsPage().isPageDisplayed(), "Assert that Continue button does not navigate to the next page when name is less than MIN letters");
    }

    @Test
    public void checkLettersMin() {
        landingPage().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerPage().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS));
        Assert.assertTrue(settingsPage().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MIN letters");
    }

    @Test
    public void checkLettersMax() {
        landingPage().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerPage().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS));
        Assert.assertTrue(settingsPage().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MAX letters");
    }

    @Test
    public void checkLettersMore() {
        landingPage().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerPage().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS + 1), false);
        Assert.assertEquals(registerPage().getCurrentUsername().length(), MAX_USERNAME_LETTERS, "Assert that user can not enter more than MAX letters in the username field");
    }
}
