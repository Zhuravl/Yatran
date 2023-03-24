package ua.com.yatran.entities;

import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * This class represents information about the user's result
 */
public class RankingRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private Integer score;
    private Integer level;
    private Integer mistakes;
    private Calendar date;

    public RankingRecord() {
    }

    public RankingRecord(String username) {
        this.username = username;
    }

    public RankingRecord(String username, Integer score, Integer level, Integer mistakes, Calendar date) {
        this.username = username;
        this.score = score;
        this.level = level;
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

    public Integer getMistakes() {
        return mistakes;
    }

    public void setMistakes(Integer mistakes) {
        this.mistakes = mistakes;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDateFormatted() {
        if (date != null) {
            return DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()).format(date.getTimeInMillis());
        } else {
            return null;
        }
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankingRecord rankingRecord = (RankingRecord) o;
        return Objects.equals(username, rankingRecord.username) && Objects.equals(score, rankingRecord.score) && Objects.equals(level, rankingRecord.level) && Objects.equals(mistakes, rankingRecord.mistakes) && Objects.equals(date, rankingRecord.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, score, level, mistakes, date);
    }

    @Override
    public String toString() {
        return "RankingRecord{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", level=" + level +
                ", mistakes=" + mistakes +
                ", date=" + getDateFormatted() +
                '}';
    }
}
