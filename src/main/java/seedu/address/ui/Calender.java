package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.ReadOnlyEventList;
import seedu.address.model.event.Event;

public class Calender extends UiPart<Region> {
    private static final String FILEPATH = "Calender.fxml";
    private LocalDate now;
    private int year;
    private Month headerMonth;
    private int headerYear;
    private boolean isLeapYear;
    private ReadOnlyEventList eventList;

    @FXML
    private GridPane calenderGrid;
    @FXML
    private Label monthYearInput;

    /**
     * Represents the calender that is going to be displayed.
     */
    public Calender(ReadOnlyEventList eventList) {
        super(FILEPATH);
        this.now = LocalDate.now();
        this.year = now.getYear();
        this.headerMonth = now.getMonth();
        this.headerYear = this.year;
        this.isLeapYear = now.isLeapYear();
        this.eventList = eventList;
        loadNow();
    }

    private int numOfDays() {
        if (this.headerMonth.getValue() == 2) {
            if (Year.isLeap(this.headerYear)) {
                return this.headerMonth.length(true);
            } else {
                return this.headerMonth.length(false);
            }
        } else {
            return this.headerMonth.length(false);
        }
    }


    private void fillCalender(VBox box, int col, int row) {
        calenderGrid.add(box, col, row);
    }

    private void updateHeader(int value) {
        if (value == 1) { // plus 1 month
            if (this.headerMonth.getValue() == 12) {
                this.headerMonth = Month.JANUARY;
                this.headerYear = this.headerYear + 1;
            } else {
                this.headerMonth = this.headerMonth.plus(1);
            }
        } else if (value == -1) { // minus 1 month
            if (this.headerMonth.getValue() == 1) {
                this.headerMonth = Month.DECEMBER;
                this.headerYear = this.headerYear - 1;
            } else {
                this.headerMonth = this.headerMonth.minus(1);
            }
        } else {
            // do nothing
        }
        monthYearInput.setText(this.headerMonth.toString() + " " + this.headerYear);
        monthYearInput.setStyle("-fx-text-fill: white");
    }

    private ArrayList<Integer> loadEventList(int month, int year) {
        ArrayList<Integer> listOfDays = new ArrayList<>();
        for (Event event : this.eventList.getEventList()) {
            LocalDateTime dateTime = event.getTime().getStart();
            if (dateTime.getYear() == year && dateTime.getMonthValue() == month) {
                listOfDays.add(dateTime.getDayOfMonth());
            }
        }
        return listOfDays;
    }

    private boolean isInEventList(ArrayList<Integer> listofDays, int day) {
        for (int i : listofDays) {
            if (i == day) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads the Calendar with all the relevant information.
     */
    public void loadNow() {
        emptyCal();
        loadDayHeader();
        updateHeader(0);
        this.headerMonth.firstDayOfYear(this.isLeapYear);
        LocalDate start = YearMonth.of(this.headerYear, this.headerMonth).atDay(1);
        int first = start.getDayOfWeek().getValue();
        int count = 1;
        ArrayList<Integer> daysToMark = loadEventList(this.headerMonth.getValue(), this.headerYear);
        for (int i = first; i < first + numOfDays(); i++) {
            Label label = new Label();
            label.setText(Integer.toString(count));
            label.setStyle("-fx-text-fill: white");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(label);
            if (count % 2 == 0) {
                box.setBackground(new Background(
                        new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            box.setBorder(new Border(new BorderStroke(Color.valueOf("white"),
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            if (isInEventList(daysToMark, count)) {
                box.setBackground(new Background(
                        new BackgroundFill(Color.valueOf("#FA8072"), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            int col = parseCol(i);
            int row = parseRow(i);
            fillCalender(box, col, row);
            count++;
        }
    }

    private int parseCol(int value) {
        return (value + 6) % 7;
    }

    private int parseRow(int value) {
        return (value - 1) / 7 + 1;
    }

    private void emptyCal() {
        calenderGrid.getChildren().clear();
    }

    private void loadDayHeader() {
        for (int i = 0; i < 7; i++) {
            switch (i) {
            case 0:
                setDayHeader("Monday", 0);
                break;
            case 1:
                setDayHeader("Tuesday", 1);
                break;
            case 2:
                setDayHeader("Wednesday", 2);
                break;
            case 3:
                setDayHeader("Thursday", 3);
                break;
            case 4:
                setDayHeader("Friday", 4);
                break;
            case 5:
                setDayHeader("Saturday", 5);
                break;
            case 6:
                setDayHeader("Sunday", 6);
                break;
            default:
                setDayHeader("", 0);
            }
        }
    }

    private void setDayHeader(String day, int col) {
        VBox box = new VBox();
        Label label = new Label();
        label.setText(day);
        label.setUnderline(true);
        label.setStyle("-fx-text-fill: white");
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(label);
        fillCalender(box, col, 0);
    }

    /**
     * Handles the button action when pressing on next month.
     */
    @FXML
    public void nextMonth() {
        updateHeader(1);
        loadNow();
    }

    /**
     * Handles the button action when pressing on previous month.
     */
    @FXML
    public void previousMonth() {
        updateHeader(-1);
        loadNow();
    }
}
