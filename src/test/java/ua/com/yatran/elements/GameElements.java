package ua.com.yatran.elements;

import org.assertj.swing.fixture.FrameFixture;
import ua.com.yatran.enums.Language;

public class GameElements extends BaseElements {

    public GameElements(FrameFixture window) {
        super(window);
    }

    @Override
    public boolean isPageDisplayed() {
        logger.info("Checking if the Game page is displayed...");
        boolean result;
        try {
            result = window.panel("keyboardPanel") != null;
        } catch (Exception e) {
            result = false;
        }
        logger.info("The result = " + result);
        return result;
    }

    public Language getKeyboard() {
        logger.info("Getting the selected keyboard...");
        String keyboardName = window.textBox("keyboardField").text();
        Language result = keyboardName != null ? Language.getByKeyboardName(keyboardName) : null;
        logger.info("The result = " + result);
        return result;
    }

    public Integer getLevel() {
        logger.info("Getting the selected level...");
        String level = window.textBox("levelField").text();
        Integer result = level != null ? Integer.parseInt(level) : null;
        logger.info("The result = " + result);
        return result;
    }

    public Integer getScores() {
        logger.info("Getting the selected scores...");
        String scores = window.textBox("scoreField").text();
        Integer result = scores != null ? Integer.parseInt(scores) : null;
        logger.info("The result = " + result);
        return result;
    }

    public Integer getMistakes() {
        logger.info("Getting the selected mistakes...");
        Integer result = window.progressBar("mistakesBar").target().getValue();
        logger.info("The result = " + result);
        return result;
    }
}
