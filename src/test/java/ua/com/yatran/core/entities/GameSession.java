package ua.com.yatran.core.entities;

import ua.com.yatran.enums.Language;

import java.util.Objects;

public class GameSession {

    private String username;
    private Language language;
    private Language keyboard;
    private Integer level;
    private Boolean isSoundsOn;

    public GameSession() {
    }

    public GameSession(String username, Language language, Language keyboard, Integer level, Boolean isSoundsOn) {
        this.username = username;
        this.language = language;
        this.keyboard = keyboard;
        this.level = level;
        this.isSoundsOn = isSoundsOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Language keyboard) {
        this.keyboard = keyboard;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getSoundsOn() {
        return isSoundsOn;
    }

    public void setSoundsOn(Boolean soundsOn) {
        isSoundsOn = soundsOn;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(username, that.username) &&
                language == that.language &&
                keyboard == that.keyboard &&
                Objects.equals(level, that.level) &&
                Objects.equals(isSoundsOn, that.isSoundsOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, language, keyboard, level, isSoundsOn);
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "username='" + username + '\'' +
                ", language=" + language +
                ", keyboard=" + keyboard +
                ", level=" + level +
                ", isSoundsOn=" + isSoundsOn +
                '}';
    }
}
