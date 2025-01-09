package ua.com.yatran.actions;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.elements.RegisteringElements;

public class RegisteringActions extends BaseActions {

    RegisteringElements registeringElements;

    public RegisteringActions(FrameFixture window) {
        registeringElements = new RegisteringElements(window);
    }

    /**
     * Returns the currently displayed registration label text
     */
    public String getRegistrationLabelText() {
        logger.info("Getting currently displayed registration label text...");
        String result = registeringElements.getRegistrationLabelText();
        logger.info("The result text = '" + result + "'");
        return result;
    }

    /**
     * Registers user with the provided username and clicks on Continue button
     *
     * @param username username to be entered
     */
    public void registerUser(String username) {
        registerUser(username, true);
    }

    /**
     * Registers user with the provided username and clicks on Continue button if needed
     *
     * @param username            username to be entered
     * @param clickContinueButton do you want to click on the Continue button?
     */
    public void registerUser(String username, boolean clickContinueButton) {
        logger.info("Register user: '" + username + "'...");
        registeringElements.enterUsername(username);
        if (clickContinueButton) {
            registeringElements.clickContinueButton();
        }
        logger.info("The user has been successfully registered!");
    }

    /**
     * Returns the current username
     */
    public String getCurrentUsername() {
        logger.info("Getting current username...");
        String result = registeringElements.getCurrentUsername();
        logger.info("The result = '" + result + "'");
        return result;
    }
}
