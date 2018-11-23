package sample.database.Model;

import java.util.*;

/**
 * 
 */
public class DayoffRequest {

    /**
     * Default constructor
     */
    public DayoffRequest() {
    }

    /**
     * 
     */
    public DayoffType type;

    /**
     * 
     */
    public Date dayStart;

    /**
     * 
     */
    public Date dayEnd;

    /**
     * 
     */
    public float duration;

    /**
     * 
     */
    public String reason;

    /**
     * 
     */
    public String comment;

    /**
     * 
     */
    public DayoffRequestState state;

}