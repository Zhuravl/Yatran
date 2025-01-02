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
import ua.com.yatran.features.LandingFeature;
import ua.com.yatran.features.RegisterFeature;
import ua.com.yatran.features.SettingsFeature;
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

    private final Map<String, Object> featureStorage = new ConcurrentHashMap<>();

    public BaseTest() {
    }

    /**
     * Creates and returns an instance of {@link LandingFeature}
     */
    public LandingFeature landingPage() {
        if (featureStorage.get("landingFeature") == null) {
            featureStorage.put("landingFeature", new LandingFeature(window, this));
        }
        return (LandingFeature) featureStorage.get("landingFeature");
    }

    /**
     * Creates and returns an instance of {@link RegisterFeature}
     */
    public RegisterFeature registerPage() {
        if (featureStorage.get("registerFeature") == null) {
            featureStorage.put("registerFeature", new RegisterFeature(window));
        }
        return (RegisterFeature) featureStorage.get("registerFeature");
    }

    /**
     * Creates and returns an instance of {@link SettingsFeature}
     */
    public SettingsFeature settingsPage() {
        if (featureStorage.get("settingsFeature") == null) {
            featureStorage.put("settingsFeature", new SettingsFeature(window));
        }
        return (SettingsFeature) featureStorage.get("settingsFeature");
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
        //Reset all feature classes after switching the frame context for re-creation of them with the updated window in the future steps
        featureStorage.clear();
        logger.info("The frame context has been successfully switched!");
    }
}
