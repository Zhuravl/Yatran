package ua.com.yatran.elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.swing.fixture.FrameFixture;

/**
 * Base class for all elements.
 * The main class for all element-layer classes that are responsible for interaction with the page elements
 */
public abstract class BaseElements {

    protected static Logger logger = LogManager.getLogger(BaseElements.class);
    protected FrameFixture window;

    public BaseElements(FrameFixture window) {
        this.window = window;
    }

    /**
     * Checks if the page is displayed
     */
    public abstract boolean isPageDisplayed();
}
