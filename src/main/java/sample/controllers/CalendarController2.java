package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import sample.database.Model.DayoffRequest;
import sample.database.Model.User;
import sample.utils.TimesOfTheDayEnum;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalendarController2 implements Initializable {
    private final int usernameCaseSize = 200; //px width
    private final int dayCaseSize = 30; //px width
    private final int lineSize = dayCaseSize; //px height
    private final int weekLineSize = (int) (lineSize*0.66); //px height
    private final int columnCount = 20; //(int) ((planning.getWidth()-usernameCaseSize)/dayCaseSize)+1;

    @FXML private GridPane planning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*LocalDate tmp = LocalDate.now().plusDays(3);
        HashMap<String, User> users = new HashMap<>();
        User dayoffsUser1 = new HashMap<>();
        dayoffsUser1.put(tmp, TimesOfTheDayEnum.AFTERNOON);
        tmp = tmp.plusDays(1);
        dayoffsUser1.put(tmp, TimesOfTheDayEnum.ALL_DAY_LONG);
        tmp = tmp.plusDays(1);
        dayoffsUser1.put(tmp, TimesOfTheDayEnum.ALL_DAY_LONG);
        tmp = tmp.plusDays(1);
        dayoffsUser1.put(tmp, TimesOfTheDayEnum.MORNING);
        users.put("Daniel Rocacher", dayoffsUser1);
        User dayoffsUser2 = new HashMap<>();
        tmp = tmp.plusDays(6);
        dayoffsUser2.put(tmp, TimesOfTheDayEnum.ALL_DAY_LONG);
        tmp = tmp.plusDays(1);
        dayoffsUser2.put(tmp, TimesOfTheDayEnum.ALL_DAY_LONG);
        users.put("Pierre Alain", dayoffsUser2);*/


        LocalDate calendarDate = LocalDate.now();
        reset(calendarDate);
    }

    public void reset(final LocalDate local) {
        HashMap<String, User> users = new HashMap<>();
        reset(local, users);
    }

    public void reset(final LocalDate local, final HashMap<String, User> users) { //HashMap<username, HashMap<date, TimesOfTheDayEnum>>
        System.out.println("### Début affichage planning ###");
        LocalDate currentDate = LocalDate.now();
        LocalDate lastDate = local.plusDays(columnCount-1);
        LocalDate browseTime = local;
        LocalDate previousDate = local.minusDays(1);
        int dayCount = (int) DAYS.between(browseTime, lastDate);
        String[] usernames = users.keySet().toArray(new String[users.size()]);
        // Generates the grid partitioning
        planning.getChildren().clear();
        planning.getRowConstraints().clear();
        planning.getColumnConstraints().clear();
        for(int x=0; x<columnCount; x++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHgrow(Priority.NEVER);
            if(x == 0) {
                constraints.setMinWidth(usernameCaseSize);
                constraints.setMaxWidth(usernameCaseSize);
            } else {
                constraints.setMinWidth(dayCaseSize);
                constraints.setMaxWidth(dayCaseSize);
            }
            planning.getColumnConstraints().add(constraints);
        }
        for(int y=0; y<users.size()+3; y++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setVgrow(Priority.NEVER);
            if(y == 1) {
                constraints.setMinHeight(weekLineSize);
                constraints.setMaxHeight(weekLineSize);
            } else {
                constraints.setMinHeight(lineSize);
                constraints.setMaxHeight(lineSize);
            }
            planning.getRowConstraints().add(constraints);
        }
        System.out.println("grid:"+planning.getColumnConstraints().size()+"x"+planning.getRowConstraints().size());

        // Adds navigation controls
        GridPane navigationBar = new GridPane();
        navigationBar.getStyleClass().add("cell");
        //// Prepare grid of navigation bar
        RowConstraints rConstraints = new RowConstraints();
        rConstraints.setVgrow(Priority.ALWAYS);
        navigationBar.getRowConstraints().add(rConstraints);
        for(int x=0; x<3; x++) {
            ColumnConstraints cConstraints = new ColumnConstraints();
            cConstraints.setHgrow(Priority.ALWAYS);
            navigationBar.getColumnConstraints().add(cConstraints);
        }
        //// Go to the past
        Label lblBefore = new Label("<<");
        GridPane.setHalignment(lblBefore, HPos.CENTER);
        GridPane.setValignment(lblBefore, VPos.TOP);
        lblBefore.setOnMouseClicked(e -> reset(local.minusDays(columnCount-1), users));
        navigationBar.add(lblBefore, 0, 0);
        //// Go to the current date
        Label lblCurrentTime = new Label("¤");
        GridPane.setHalignment(lblCurrentTime, HPos.CENTER);
        GridPane.setValignment(lblCurrentTime, VPos.TOP);
        lblCurrentTime.setOnMouseClicked(e -> reset(currentDate, users));
        navigationBar.add(lblCurrentTime, 1, 0);
        //// Go to the futur
        Label lblAfter = new Label(">>");
        GridPane.setHalignment(lblAfter, HPos.CENTER);
        GridPane.setValignment(lblAfter, VPos.TOP);
        lblAfter.setOnMouseClicked(e -> reset(local.plusDays(columnCount-1), users));
        navigationBar.add(lblAfter, 2, 0);
        planning.add(navigationBar, 0, 0, 1, 3);

        // Puts usernames
        for(int i=0; i<usernames.length; i++) {
            AnchorPane usernamePane = new AnchorPane();
            Label monthLbl = new Label(usernames[i]);
            AnchorPane.setTopAnchor(monthLbl, 0.0);
            AnchorPane.setRightAnchor(monthLbl, 0.0);
            AnchorPane.setBottomAnchor(monthLbl, 0.0);
            AnchorPane.setLeftAnchor(monthLbl, 0.0);
            usernamePane.getChildren().add(monthLbl);
            usernamePane.getStyleClass().add("username");
            planning.add(usernamePane, 0, i+3, 1, 1);
            System.out.println("username:"+usernames[i]+"   (x="+0+"; y="+(i+3)+")");
        }
        // For each day
        for(int x=1; x<dayCount+1; x++) {
            int cellAvailible = columnCount-x;
            // Puts months
            if(x==1 || browseTime.getMonth().getValue() != previousDate.getMonth().getValue()) {
                StringBuilder month = new StringBuilder(browseTime.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
                month.replace(0, 1, month.substring(0, 1).toUpperCase());
                AnchorPane monthPane = new AnchorPane();
                Label monthLbl = new Label(month+" "+browseTime.getYear());
                AnchorPane.setTopAnchor(monthLbl, 0.0);
                AnchorPane.setRightAnchor(monthLbl, 0.0);
                AnchorPane.setBottomAnchor(monthLbl, 0.0);
                AnchorPane.setLeftAnchor(monthLbl, 0.0);
                monthPane.getChildren().add(monthLbl);
                monthPane.getStyleClass().add("month");
                int lblWidth = browseTime.lengthOfMonth()-browseTime.getDayOfMonth()+1;
                if(cellAvailible >= lblWidth) {
                    planning.add(monthPane, x, 0, lblWidth, 1);
                    System.out.println("month:"+month+" "+browseTime.getYear()+"   (x="+x+"; y="+0+") -> "+lblWidth+"x1");
                } else {
                    planning.add(monthPane, x, 0, cellAvailible, 1);
                    System.out.println("month:"+month+" "+browseTime.getYear()+"   (x="+x+"; y="+0+") -> "+cellAvailible+"x1");
                }
            }
            // Puts weeks
            if(x==1 || previousDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                Calendar tmp = new GregorianCalendar();
                tmp.setTime(java.sql.Date.valueOf(browseTime));
                int week = tmp.get(Calendar.WEEK_OF_YEAR);
                AnchorPane weekPane = new AnchorPane();
                Label weekLbl = new Label("S"+week);
                AnchorPane.setTopAnchor(weekLbl, 0.0);
                AnchorPane.setRightAnchor(weekLbl, 0.0);
                AnchorPane.setBottomAnchor(weekLbl, 0.0);
                AnchorPane.setLeftAnchor(weekLbl, 0.0);
                weekPane.getChildren().add(weekLbl);
                weekPane.getStyleClass().add("week");
                int lblWidth = DayOfWeek.SUNDAY.getValue()-browseTime.getDayOfWeek().getValue()+1;
                if(cellAvailible >= lblWidth) {
                    planning.add(weekPane, x, 1, lblWidth, 1);
                    System.out.println("week:S"+week+"   (x="+x+"; y="+1+") -> "+lblWidth+"x1");
                } else {
                    planning.add(weekPane, x, 1, cellAvailible, 1);
                    System.out.println("week:S"+week+"   (x="+x+"; y="+1+") -> "+cellAvailible+"x1");
                }
            }
            // Puts days
            AnchorPane dayPane = new AnchorPane();
            Label dayLbl = new Label(browseTime.getDayOfMonth() + "");
            AnchorPane.setTopAnchor(dayLbl, 0.0);
            AnchorPane.setRightAnchor(dayLbl, 0.0);
            AnchorPane.setBottomAnchor(dayLbl, 0.0);
            AnchorPane.setLeftAnchor(dayLbl, 0.0);
            if(browseTime.getYear()==currentDate.getYear() && browseTime.getMonth().getValue()==currentDate.getMonth().getValue() && browseTime.getDayOfMonth()==currentDate.getDayOfMonth()) {
                dayLbl.getStyleClass().add("currentDay");
            }
            dayPane.getChildren().add(dayLbl);
            if(browseTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || browseTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                dayPane.getStyleClass().add("holiday");
            }
            dayPane.getStyleClass().add("day");
            planning.add(dayPane, x, 2, 1, 1);
            System.out.println("#day:"+browseTime.getDayOfMonth()+"   (x="+x+"; y="+2+")");
            // Browses user list
            for(int y=0; y<usernames.length; y++) {
                User userInfos = users.get(usernames[y]);
                AnchorPane userDayPane = new AnchorPane();
                AnchorPane.setTopAnchor(dayLbl, 0.0);
                AnchorPane.setRightAnchor(dayLbl, 0.0);
                AnchorPane.setBottomAnchor(dayLbl, 0.0);
                AnchorPane.setLeftAnchor(dayLbl, 0.0);
                userDayPane.getStyleClass().add("cell");
                if(browseTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) || browseTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                    userDayPane.getStyleClass().add("holiday");
                } else {
                    userDayPane.getStyleClass().add("workday");
                }
                // Checks if for this user, the day is a dayoff
                for(DayoffRequest dayoffRequest : userInfos.getDayoffRequests()) {
                    DateFormat df = new SimpleDateFormat("H");
                    if(sameDay(browseTime, dayoffRequest.getDayStart())) {
                        int hourStart = Integer.parseInt(df.format(dayoffRequest.getDayStart()));
                        int hourEnd = Integer.parseInt(df.format(dayoffRequest.getDayEnd()));
                        if(hourStart >= 12) {
                            setDayoff(userDayPane, TimesOfTheDayEnum.AFTERNOON);
                        } else if(sameDay(dayoffRequest.getDayStart(), dayoffRequest.getDayEnd()) && hourEnd < 12) {
                            setDayoff(userDayPane, TimesOfTheDayEnum.MORNING);
                        } else {
                            setDayoff(userDayPane, TimesOfTheDayEnum.ALL_DAY_LONG);
                        }
                    } else if(sameDay(browseTime, dayoffRequest.getDayEnd())) {
                        int hourEnd = Integer.parseInt(df.format(dayoffRequest.getDayEnd()));
                        if(hourEnd >= 12) {
                            setDayoff(userDayPane, TimesOfTheDayEnum.ALL_DAY_LONG);
                        } else {
                            setDayoff(userDayPane, TimesOfTheDayEnum.MORNING);
                        }
                    } else if(browseTime.isAfter(dayoffRequest.getDayStart()) && browseTime.isBefore(dayoffRequest.getDayEnd())) {
                        setDayoff(userDayPane, TimesOfTheDayEnum.ALL_DAY_LONG);
                    }
                }
                planning.add(userDayPane, x, y+3, 1, 1);
                System.out.println("day   (x="+x+"; y="+(y+3)+")");
            }
            browseTime = browseTime.plusDays(1);
            previousDate = previousDate.plusDays(1);
        }
        System.out.println("### Fin affichage planning ###");
    }

    public void setDayoff(Node node, TimesOfTheDayEnum time) {
        if(node != null) {
            node.getStyleClass().remove("workday");
            node.getStyleClass().remove("dayoffMorning");
            node.getStyleClass().remove("dayoffAfternoon");
            node.getStyleClass().remove("dayoffAllDayLong");
            switch (time) {
                case MORNING:
                    node.getStyleClass().add("dayoffMorning");
                    break;
                case AFTERNOON:
                    node.getStyleClass().add("dayoffAfternoon");
                    break;
                case ALL_DAY_LONG:
                    node.getStyleClass().add("dayoffAllDayLong");
                    break;
            }
        }
    }

    public boolean sameDay(LocalDate A, LocalDate B) {
        return (A.getYear()==B.getYear() && A.getMonth().getValue()==B.getMonth().getValue() && A.getDayOfMonth()==B.getDayOfMonth());
    }
}
