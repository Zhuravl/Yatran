package ua.com.yatran.features;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for all features.
 * The main class for all feature-layer classes that are responsible for keeping the application business logic
 */
public abstract class BaseFeatures {

    protected static Logger logger = LogManager.getLogger(BaseFeatures.class);

    public BaseFeatures() {
    }
}
