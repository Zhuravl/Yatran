package ua.com.yatran.entities;

import ua.com.yatran.enums.Layout;

import java.util.Objects;

/**
 * This class represents information about the user's settings and other data we need to keep between game levels
 */
public class Settings {

    private Layout layout;
    private int level;
    private int score;
    private int mistakes;
    private boolean soundOn;

    public Settings() {
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
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
        Settings that = (Settings) o;
        return layout == that.layout &&
                level == that.level &&
                score == that.score &&
                mistakes == that.mistakes &&
                soundOn == that.soundOn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(layout, level, score, mistakes, soundOn);
    }

    @Override
    public String toString() {
        return "Settings{" +
                "layout=" + layout +
                ", level=" + level +
                ", score=" + score +
                ", mistakes=" + mistakes +
                ", soundOn=" + soundOn +
                '}';
    }
}
