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
        public static final int MAIN_WINDOW_WIDTH = 1080;
        public static final int MAIN_WINDOW_HEIGHT = (int) (MAIN_WINDOW_WIDTH / ((Math.sqrt(5) + 1) / 2));
        public static final Font FONT_LANDING = new Font("Tahoma", Font.PLAIN, 26);
        public static final Font FONT_MAIN = new Font("Tahoma", Font.PLAIN, 14);
        public static final int MISTAKES_MIN = 0;
        public static final int MISTAKES_MAX = 10;
    }

    public static class Screen {
        public static final String REGISTER = "Register";
        public static final String SETTINGS = "Settings";
        public static final String BRIEFING = "Briefing";
        public static final String GAME = "Game";
        public static final String RANKING = "Ranking";
    }
}
