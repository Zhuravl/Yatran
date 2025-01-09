package ua.com.yatran.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ua.com.yatran.Yatran;
import ua.com.yatran.actions.*;
import ua.com.yatran.frames.LandingFrame;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.swing.finder.WindowFinder.findFrame;

/**
 * Base class for all tests.
 * The main class for all test-layer classes that are responsible for keeping the test data and test logic
 */
public abstract class BaseTest extends AssertJSwingTestCaseTemplate {

    protected static Logger logger = LogManager.getLogger(BaseTest.class);
    protected FrameFixture window;

    private final Map<String, Object> actionsStorage = new ConcurrentHashMap<>();

    public BaseTest() {
    }

    /**
     * Creates and returns an instance of {@link LandingActions}
     */
    public LandingActions landingActions() {
        if (actionsStorage.get("landingActions") == null) {
            actionsStorage.put("landingActions", new LandingActions(window, this));
        }
        return (LandingActions) actionsStorage.get("landingActions");
    }

    /**
     * Creates and returns an instance of {@link RegisteringActions}
     */
    public RegisteringActions registeringActions() {
        if (actionsStorage.get("registeringActions") == null) {
            actionsStorage.put("registeringActions", new RegisteringActions(window));
        }
        return (RegisteringActions) actionsStorage.get("registeringActions");
    }

    /**
     * Creates and returns an instance of {@link SettingsActions}
     */
    public SettingsActions settingsActions() {
        if (actionsStorage.get("settingsActions") == null) {
            actionsStorage.put("settingsActions", new SettingsActions(window));
        }
        return (SettingsActions) actionsStorage.get("settingsActions");
    }

    /**
     * Creates and returns an instance of {@link BriefingActions}
     */
    public BriefingActions briefingActions() {
        if (actionsStorage.get("briefingActions") == null) {
            actionsStorage.put("briefingActions", new BriefingActions(window));
        }
        return (BriefingActions) actionsStorage.get("briefingActions");
    }

    /**
     * Creates and returns an instance of {@link GameActions}
     */
    public GameActions gameActions() {
        if (actionsStorage.get("gameActions") == null) {
            actionsStorage.put("gameActions", new GameActions(window));
        }
        return (GameActions) actionsStorage.get("gameActions");
    }

    /**
     * Creates and returns an instance of {@link RankingActions}
     */
    public RankingActions rankingActions() {
        if (actionsStorage.get("rankingActions") == null) {
            actionsStorage.put("rankingActions", new RankingActions(window));
        }
        return (RankingActions) actionsStorage.get("rankingActions");
    }

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        logger.info("Starting test suite execution...");
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeMethod(alwaysRun = true)
    protected void startTest(Method method) {
        logger.info("Executing test '" + method.getName() + "'...");
        setUpRobot();
        ApplicationLauncher.application(Yatran.class).start();
        switchFrameContext(LandingFrame.class);
    }

    @AfterMethod(alwaysRun = true)
    public void finishTest(Method method, ITestResult result) {
        try {
            window.cleanUp();
        } finally {
            cleanUp();
        }

        String resultName = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "PASSED";
            case ITestResult.FAILURE -> "FAILED";
            case ITestResult.SKIP -> "SKIPPED";
            default -> "UNKNOWN STATE";
        };
        logger.info("Test '" + method.getName() + "' execution has been finished with result: " + resultName + "!");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        FailOnThreadViolationRepaintManager.uninstall();
        logger.info("Test suite execution has been finished!");
    }

    /**
     * Switches the frame context to work with the provided <code>{@link Frame}</code> and clears all feature classes for pulling the updated frame while re-creating.
     *
     * @param targetFrame the <code>{@link Frame}</code> to switch to
     */
    public void switchFrameContext(Class<? extends Frame> targetFrame) {
        logger.info("Switching frame context to " + targetFrame.getName() + "...");
        window = findFrame(targetFrame).using(robot());
        //Reset all action classes after switching the frame context for re-creation of them with the updated window in the future steps
        actionsStorage.clear();
        logger.info("The frame context has been successfully switched!");
    }
}
