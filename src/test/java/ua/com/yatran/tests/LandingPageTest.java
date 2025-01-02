package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class LandingPageTest extends BaseTest {

    @Test(dataProvider = "getAllLanguages")
    public void checkLocaleSelection(Language language) {
        landingPage().selectLanguage(language);
        Assert.assertEquals(registerPage().getRegistrationLabelText(), TestDataHelper.getRegistrationLabelText(language), "Assert that registration label text corresponds to the selected language");
    }

    @DataProvider
    public Object[][] getAllLanguages() {
        Language[] languages = Language.values();
        Object[][] data = new Object[languages.length][1];
        for (int i = 0; i < languages.length; i++) {
            data[i][0] = languages[i];
        }
        return data;
    }
}
