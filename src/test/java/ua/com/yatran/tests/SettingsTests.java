package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.entities.GameInfo;
import ua.com.yatran.core.entities.GameSession;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.enums.Language;

public class SettingsTests extends BaseTest {

    private static final int GAME_LEVEL_FIRST = 1;
    private static final int GAME_LEVEL_LAST = 47;
    private static final boolean SOUND_PREFERENCE_DEFAULT = true;

    @Test
    public void checkDefaultSettings() {
        GameSession session = new GameSession();
        session.setLanguage(TestDataHelper.getRandomEnum(Language.class));
        session.setUsername(TestDataHelper.getRandomName(session.getLanguage()));
        session.setKeyboard(session.getLanguage()); //Default keyboard depends on the selected language
        session.setLevel(GAME_LEVEL_FIRST);
        session.setSoundsOn(SOUND_PREFERENCE_DEFAULT);

        GameInfo gameInfo = new GameInfo();
        gameInfo.setKeyboard(session.getKeyboard());
        gameInfo.setLevel(session.getLevel());
        gameInfo.setScores(0);
        gameInfo.setMistakes(0);

        landingActions().selectLanguage(session.getLanguage());
        registeringActions().registerUser(session.getUsername());
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        Assert.assertEquals(gameActions().getGameInfo(), gameInfo, "Assert that the default game settings are correct and applied");
    }

    @Test
    public void checkCustomSettings() {
        GameSession session = new GameSession();
        session.setLanguage(TestDataHelper.getRandomEnum(Language.class));
        session.setUsername(TestDataHelper.getRandomName(session.getLanguage()));
        session.setKeyboard(TestDataHelper.getRandomEnum(Language.class, session.getLanguage()));
        session.setLevel(GAME_LEVEL_LAST);
        session.setSoundsOn(!SOUND_PREFERENCE_DEFAULT);

        GameInfo gameInfo = new GameInfo();
        gameInfo.setKeyboard(session.getKeyboard());
        gameInfo.setLevel(session.getLevel());
        gameInfo.setScores(0);
        gameInfo.setMistakes(0);

        landingActions().selectLanguage(session.getLanguage());
        registeringActions().registerUser(session.getUsername());
        settingsActions().setSettings(session);
        briefingActions().startGame();
        Assert.assertEquals(gameActions().getGameInfo(), gameInfo, "Assert that the custom game settings are applied correctly");
    }
}
