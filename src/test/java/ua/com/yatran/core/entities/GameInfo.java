package ua.com.yatran.core.entities;

import ua.com.yatran.enums.Language;

import java.util.Objects;

public class GameInfo {

    private Language keyboard;
    private Integer level;
    private Integer scores;
    private Integer mistakes;

    public GameInfo() {
    }

    public GameInfo(Language keyboard, Integer level, Integer scores, Integer mistakes) {
        this.keyboard = keyboard;
        this.level = level;
        this.scores = scores;
        this.mistakes = mistakes;
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

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getMistakes() {
        return mistakes;
    }

    public void setMistakes(Integer mistakes) {
        this.mistakes = mistakes;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return keyboard == gameInfo.keyboard &&
                Objects.equals(level, gameInfo.level) &&
                Objects.equals(scores, gameInfo.scores) &&
                Objects.equals(mistakes, gameInfo.mistakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyboard, level, scores, mistakes);
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "keyboard=" + keyboard +
                ", level=" + level +
                ", scores=" + scores +
                ", mistakes=" + mistakes +
                '}';
    }
}
