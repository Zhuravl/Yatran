package ua.com.yatran.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Base class for all actions.
 * The main class for all action-layer classes that are responsible for keeping the application business logic
 */
public abstract class BaseActions {

    protected static Logger logger = LogManager.getLogger(BaseActions.class);

    public BaseActions() {
    }
}
