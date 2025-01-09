package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;

public class RegisteringElements extends BaseElements {

    public RegisteringElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Register page is displayed...");
        boolean result;
        try {
            result = window.label("usernameHintLabel") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }

    public String getRegistrationLabelText() {
        logger.info("Getting registration label text...");
        String result = window.label("usernameLabel").text();
        logger.info("The result text = '" + result + "'");
        return result;
    }

    public void enterUsername(String username) {
        logger.info("Entering username: '" + username + "'...");
        window.textBox("usernameField").enterText(username);
    }

    public String getCurrentUsername() {
        logger.info("Getting current username...");
        String result = window.textBox("usernameField").text();
        logger.info("The result text = '" + result + "'");
        return result;
    }

    public void clickContinueButton() {
        logger.info("Clicking Continue button...");
        window.button("continueButton").click();
    }
}
