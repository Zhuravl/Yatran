package ua.com.yatran.entities;

import ua.com.yatran.enums.Language;

import java.util.Objects;

/**
 * This class represents information about the user's settings and other data we need to keep between game levels
 */
public class Settings {

    private Language language;
    private int level;
    private int score;
    private int mistakes;
    private boolean soundOn;

    public Settings() {
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public boolean isSoundOn() {
        return soundOn;
    }

    public void setSoundOn(boolean soundOn) {
        this.soundOn = soundOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settings settings = (Settings) o;
        return level == settings.level && score == settings.score && mistakes == settings.mistakes && soundOn == settings.soundOn && language == settings.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, level, score, mistakes, soundOn);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "language=" + language +
                ", level=" + level +
                ", score=" + score +
                ", mistakes=" + mistakes +
                ", soundOn=" + soundOn +
                '}';
    }
}
