package ua.com.yatran.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.com.yatran.core.helpers.TestDataHelper;
import ua.com.yatran.entities.RankingRecord;
import ua.com.yatran.enums.Language;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingTests extends BaseTest {

    private static final int SCORE_NUMBER_MAX = 1000;
    private static final int GAME_LEVEL_LAST = 47;
    private static final int MISTAKE_NUMBER_MAX = 100;

    @Test
    public void checkSingleRecord() {
        Language language = TestDataHelper.getRandomEnum(Language.class);
        String username = TestDataHelper.getRandomName(language);
        List<RankingRecord> expectedList = new ArrayList<>();
        expectedList.add(new RankingRecord(username, TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));

        landingActions().selectLanguage(language);
        registeringActions().registerUser(username);
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        gameActions().skipGame(expectedList);
        Assert.assertEquals(rankingActions().getRankingTable(), expectedList, "Assert that the ranking table with the single record is correct");
    }

    @Test
    public void checkFullTable() {
        Language language = TestDataHelper.getRandomEnum(Language.class);
        String username = TestDataHelper.getRandomName(language);
        List<RankingRecord> expectedList = new ArrayList<>();
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));

        landingActions().selectLanguage(language);
        registeringActions().registerUser(username);
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        gameActions().skipGame(expectedList);

        expectedList.sort(Comparator.comparing(RankingRecord::getDate));
        expectedList.sort(Comparator.comparing(RankingRecord::getUsername));
        expectedList.sort(Comparator.comparing(RankingRecord::getMistakes));
        expectedList.sort(Comparator.comparing(RankingRecord::getLevel).reversed());
        expectedList.sort(Comparator.comparing(RankingRecord::getScore).reversed());

        Assert.assertEquals(rankingActions().getRankingTable(), expectedList, "Assert that the ranking table with the MAX records is correct");
    }

    @Test
    public void checkMoreRecords() {
        Language language = TestDataHelper.getRandomEnum(Language.class);
        String username = TestDataHelper.getRandomName(language);
        RankingRecord currentSessionRecord = new RankingRecord(TestDataHelper.getRandomName(language), 0, TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate());
        List<RankingRecord> expectedList = new ArrayList<>();
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(new RankingRecord(TestDataHelper.getRandomName(language), 1 + TestDataHelper.getRandomNumber(SCORE_NUMBER_MAX), TestDataHelper.getRandomNumber(GAME_LEVEL_LAST), TestDataHelper.getRandomNumber(MISTAKE_NUMBER_MAX), TestDataHelper.getCurrentDate()));
        expectedList.add(currentSessionRecord);

        landingActions().selectLanguage(language);
        registeringActions().registerUser(username);
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        gameActions().skipGame(expectedList);

        //Left the only MAX records, sort them and replace the last two with the 'empty' and the 'current session' records
        expectedList.remove(expectedList.size() - 1);
        expectedList.sort(Comparator.comparing(RankingRecord::getDate));
        expectedList.sort(Comparator.comparing(RankingRecord::getUsername));
        expectedList.sort(Comparator.comparing(RankingRecord::getMistakes));
        expectedList.sort(Comparator.comparing(RankingRecord::getLevel).reversed());
        expectedList.sort(Comparator.comparing(RankingRecord::getScore).reversed());
        expectedList.set(expectedList.size() - 2, new RankingRecord("", null, null, null, null));
        expectedList.set(expectedList.size() - 1, currentSessionRecord);

        Assert.assertEquals(rankingActions().getRankingTable(), expectedList, "Assert that the ranking table with the MAX+1 records is correct");
    }

    @Test
    public void checkContinueButton() {
        Language language = TestDataHelper.getRandomEnum(Language.class);
        String username = TestDataHelper.getRandomName(language);

        landingActions().selectLanguage(language);
        registeringActions().registerUser(username);
        settingsActions().clickContinueButton();
        briefingActions().startGame();
        gameActions().skipGame();
        rankingActions().clickContinueButton();
        Assert.assertEquals(registeringActions().getCurrentUsername(), username, "Assert that clicking the Continue button takes user to the Registering page with the current username");
    }
}
