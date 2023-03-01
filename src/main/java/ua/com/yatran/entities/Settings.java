package ua.com.yatran.entities;

import ua.com.yatran.enums.Language;

import java.util.Objects;

/**
 * This class represents information about the user's settings
 */
public class Settings {

    private Language language;
    private int level;
    private boolean soundOn;

    public Settings(Language language, int level, boolean soundOn) {
        this.language = language;
        this.level = level;
        this.soundOn = soundOn;
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
        return level == settings.level && soundOn == settings.soundOn && language == settings.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, level, soundOn);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "language=" + language +
                ", level=" + level +
                ", soundOn=" + soundOn +
                '}';
    }
}
