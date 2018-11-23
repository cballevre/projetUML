package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import sample.utils.TimesOfTheDayEnum;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {
    private final char weekdays[] = {'L', 'M', 'M', 'J', 'V', 'S', 'D'};
    private final int columnCount = 7;
    private LocalDate calendarDate;
    private int indexFirstDay;

    @FXML private GridPane calendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        calendarDate = LocalDate.now();
        reset(calendarDate);
    }

    public void reset(LocalDate local) {
        int dayCount = local.lengthOfMonth();
        int rowCount = (int) Math.ceil((float) dayCount / columnCount)+2;

        // Adds cells
        calendar.getChildren().clear();
        calendar.getRowConstraints().clear();
        calendar.getColumnConstraints().clear();
        for(int x=0; x<columnCount; x++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHgrow(Priority.SOMETIMES);
            calendar.getColumnConstraints().add(constraints);
        }
        for(int y=0; y<rowCount; y++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setVgrow(Priority.SOMETIMES);
            calendar.getRowConstraints().add(constraints);
        }

        // Adds the label of month name
        StringBuilder month = new StringBuilder(local.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        month.replace(0, 1, month.substring(0, 1).toUpperCase());
        Label monthLbl = new Label(month+" - "+local.getYear());
        GridPane.setHalignment(monthLbl, HPos.CENTER);
        GridPane.setValignment(monthLbl, VPos.CENTER);
        calendar.add(monthLbl, 0, 0, columnCount, 1);

        // Adds navigation controls
        Label lblBefore = new Label("<<");
        GridPane.setHalignment(lblBefore, HPos.CENTER);
        GridPane.setValignment(lblBefore, VPos.CENTER);
        lblBefore.setOnMouseClicked(e -> {
            calendarDate = calendarDate.minusMonths(1);
            reset(calendarDate);
        });
        calendar.add(lblBefore, 0, 0, 1, 1);

        // Adds navigation controls
        Label lblCurrentTime = new Label("¤");
        GridPane.setHalignment(lblCurrentTime, HPos.CENTER);
        GridPane.setValignment(lblCurrentTime, VPos.CENTER);
        lblCurrentTime.setOnMouseClicked(e -> {
            calendarDate = LocalDate.now();
            reset(calendarDate);
        });
        calendar.add(lblCurrentTime, 1, 0, 1, 1);

        Label lblAfter = new Label(">>");
        GridPane.setHalignment(lblAfter, HPos.CENTER);
        GridPane.setValignment(lblAfter, VPos.CENTER);
        lblAfter.setOnMouseClicked(e -> {
            calendarDate = calendarDate.plusMonths(1);
            reset(calendarDate);
        });
        calendar.add(lblAfter, columnCount-1, 0, 1, 1);

        // Adds labels of day initials
        for(int i=0; i<weekdays.length; i++) {
            Label lbl = new Label(weekdays[i]+"");
            GridPane.setHalignment(lbl, HPos.CENTER);
            GridPane.setValignment(lbl, VPos.CENTER);
            calendar.add(lbl, i, 1, 1, 1);
        }

        // Adds the label of day number
        int offset = calendarDate.withDayOfMonth(1).getDayOfWeek().getValue()-2;
        indexFirstDay = calendar.getChildren().size()+offset+1;
        LocalDate currentDate = LocalDate.now();
        for(int i=0; i<=dayCount+offset; i++) {
            int y = i/columnCount + 2;
            int x = i%columnCount;
            int dayNumber = i-offset;
            AnchorPane apane = new AnchorPane();
            if(i > offset) {
                Label lbl = new Label((dayNumber) + "");
                AnchorPane.setTopAnchor(lbl, 0.0);
                AnchorPane.setRightAnchor(lbl, 0.0);
                AnchorPane.setBottomAnchor(lbl, 0.0);
                AnchorPane.setLeftAnchor(lbl, 0.0);
                if (currentDate.getDayOfMonth() == dayNumber && currentDate.getYear() == calendarDate.getYear() && currentDate.getMonth() == calendarDate.getMonth())
                    lbl.getStyleClass().add("currentDay");
                apane.getChildren().add(lbl);
            }
            calendar.add(apane, x, y, 1, 1);
        }
    }

    public void setDayoff(int dayNumber, TimesOfTheDayEnum time) {
        if(dayNumber > 0 && dayNumber < calendar.getChildren().size()-10) {
            int index = dayNumber+indexFirstDay-1;
            calendar.getChildren().get(index).getStyleClass().remove("dayoffMorning");
            calendar.getChildren().get(index).getStyleClass().remove("dayoffAfternoon");
            calendar.getChildren().get(index).getStyleClass().remove("dayoffAllDayLong");
            switch (time) {
                case MORNING:
                    calendar.getChildren().get(index).getStyleClass().add("dayoffMorning");
                    break;
                case AFTERNOON:
                    calendar.getChildren().get(index).getStyleClass().add("dayoffAfternoon");
                    break;
                case ALL_DAY_LONG:
                    calendar.getChildren().get(index).getStyleClass().add("dayoffAllDayLong");
                    break;
            }
        }
    }
}
