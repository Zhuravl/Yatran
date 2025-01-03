package ua.com.yatran.core.entities;

import java.util.Calendar;
import java.util.Objects;

public class GameResult {

    private String username;
    private Integer level;
    private Integer place;
    private Integer scores;
    private Integer mistakes;
    private Calendar date;

    public GameResult() {
    }

    public GameResult(Integer place, String username, Integer scores, Integer level, Integer mistakes, Calendar date) {
        this.username = username;
        this.level = level;
        this.place = place;
        this.scores = scores;
        this.mistakes = mistakes;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(level, that.level) &&
                Objects.equals(place, that.place) &&
                Objects.equals(scores, that.scores) &&
                Objects.equals(mistakes, that.mistakes) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, level, place, scores, mistakes, date);
    }

    @Override
    public String toString() {
        return "GameSession{" +
                "username='" + username + '\'' +
                ", level=" + level +
                ", place=" + place +
                ", scores=" + scores +
                ", mistakes=" + mistakes +
                ", date=" + date +
                '}';
    }
}
