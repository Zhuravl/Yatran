package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class RegisteringPageTests extends BaseTest {

    private final int MIN_USERNAME_LETTERS = 2;
    private final int MAX_USERNAME_LETTERS = 20;

    @Test
    public void checkLettersLess() {
        landingFeature().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerFeature().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS - 1));
        Assert.assertFalse(settingsFeature().isPageDisplayed(), "Assert that Continue button does not navigate to the next page when name is less than MIN letters");
    }

    @Test
    public void checkLettersMin() {
        landingFeature().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerFeature().registerUser(TestDataHelper.getRandomString(MIN_USERNAME_LETTERS));
        Assert.assertTrue(settingsFeature().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MIN letters");
    }

    @Test
    public void checkLettersMax() {
        landingFeature().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerFeature().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS));
        Assert.assertTrue(settingsFeature().isPageDisplayed(), "Assert that Continue button navigates to the next page when name is MAX letters");
    }

    @Test
    public void checkLettersMore() {
        landingFeature().selectLanguage(TestDataHelper.getRandomEnum(Language.class));
        registerFeature().registerUser(TestDataHelper.getRandomString(MAX_USERNAME_LETTERS + 1), false);
        Assert.assertEquals(registerFeature().getCurrentUsername().length(), MAX_USERNAME_LETTERS, "Assert that user can not enter more than MAX letters in the username field");
    }
}
