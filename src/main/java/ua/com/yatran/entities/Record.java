package ua.com.yatran.entities;

import java.util.Calendar;
import java.util.Objects;

/**
 * This class represents information about the user's result
 */
public class Record {

    private String username;
    private Integer score;
    private Integer level;
    private Integer speed;
    private Integer mistakes;
    private Calendar date;

    public Record() {
    }

    public Record(String username) {
        this.username = username;
    }

    public Record(String username, Integer score, Integer level, Integer speed, Integer mistakes, Calendar date) {
        this.username = username;
        this.score = score;
        this.level = level;
        this.speed = speed;
        this.mistakes = mistakes;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
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
        Record record = (Record) o;
        return Objects.equals(username, record.username) && Objects.equals(score, record.score) && Objects.equals(level, record.level) && Objects.equals(speed, record.speed) && Objects.equals(mistakes, record.mistakes) && Objects.equals(date, record.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, score, level, speed, mistakes, date);
    }

    @Override
    public String toString() {
        return "Record{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", level=" + level +
                ", speed=" + speed +
                ", mistakes=" + mistakes +
                ", date=" + date +
                '}';
    }
}
