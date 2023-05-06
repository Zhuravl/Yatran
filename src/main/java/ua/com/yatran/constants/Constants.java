package ua.com.yatran.constants;

import java.awt.*;

/**
 * This class contains all constants used in the application
 */
public class Constants {

    public static class Common {
        public static final String APP_NAME = "Yatran";
        public static final String LOCALE_PREFIX = "Locale";
        public static final String RANKING_FILE_NAME = "Data.ytr";
        public static final int USERNAME_MIN = 2;
        public static final int USERNAME_MAX = 20;
        public static final int LANDING_WINDOW_WIDTH = 700;
        public static final int LANDING_WINDOW_HEIGHT = 600;
        public static final int MAIN_WINDOW_WIDTH = 1080;
        public static final int MAIN_WINDOW_HEIGHT = (int) (MAIN_WINDOW_WIDTH / ((Math.sqrt(5) + 1) / 2));
        public static final int WINDOW_TITLE_BAR_HEIGHT = 28;
        public static final int BUTTON_WIDTH = 400;
        public static final int BUTTON_HEIGHT = 100;
        public static final int ELEMENTS_CLEARANCE = 20;
        public static final Font FONT_MAIN = new Font("Tahoma", Font.PLAIN, 26);
        public static final Font FONT_HINT = new Font("Tahoma", Font.PLAIN, 14);
        public static final Font FONT_LETTER_BLOCK = new Font("Tahoma", Font.PLAIN, 22);
        public static final Color BUTTON_COLOR_DEFAULT = Color.WHITE;
        public static final Color BUTTON_COLOR_ACCENT = new Color(0, 130, 255);
    }

    public static class Game {
        public static final int CHARACTERS_IN_LEVEL = 2;
        public static final int INFLUENCE_SPEED = 1;
        public static final int LEVEL_CHARACTER_SIZE = 20;
    }

    public static class Screen {
        public static final String REGISTER = "Register";
        public static final String SETTINGS = "Settings";
        public static final String BRIEFING = "Briefing";
        public static final String GAME = "Game";
        public static final String RANKING = "Ranking";
    }
}
