package sample.database.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 
 */
public class DayoffRequest {

    private DayoffType type;
    private LocalDate dayStart;
    private LocalDate dayEnd;
    private float duration;
    private String reason;
    private String comment;
    private DayoffRequestState state;

    public DayoffRequest(DayoffType type, LocalDate dayStart, LocalDate dayEnd, float duration, String reason, String comment, DayoffRequestState state) {
        this.type = type;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.duration = duration;
        this.reason = reason;
        this.comment = comment;
        this.state = state;
    }

    public DayoffType getType() {
        return type;
    }

    public void setType(DayoffType type) {
        this.type = type;
    }

    public LocalDate getDayStart() {
        return dayStart;
    }


    public void setDayStart(LocalDate dayStart) {
        this.dayStart = dayStart;
    }

    public LocalDate getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalDate dayEnd) {
        this.dayEnd = dayEnd;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DayoffRequestState getState() {
        return state;
    }

    public void setState(DayoffRequestState state) {
        this.state = state;
    }
}