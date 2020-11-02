package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.lang.management.ClassLoadingMXBean;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class Calender extends UiPart<Region> {
    private static final String FILEPATH = "Calender.fxml";
    private static VBox[] grid = new VBox[42];
    private LocalDate now;
    private int year;
    private Month month;
    private Month headerMonth;
    private int headerYear;
    private boolean isLeapYear;

    @FXML
    public GridPane calenderGrid;

    @FXML
    private Label monthYearInput;

    public Calender() {
        super(FILEPATH);
        loadVbox();
        this.now = LocalDate.now();
        this.year = now.getYear();
        this.month = now.getMonth();
        this.headerMonth = now.getMonth();
        this.headerYear = this.year;
        this.isLeapYear = now.isLeapYear();
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

    private void loadVbox() {
        for (int i = 0; i < 42; i++) {
            grid[i] = new VBox();
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
        } else { // minus 1 month
            if (this.headerMonth.getValue() == 1) {
                this.headerMonth = Month.DECEMBER;
                this.headerYear = this.headerYear - 1;
            } else {
                this.headerMonth = this.headerMonth.minus(1);
            }
        }
        monthYearInput.setText(this.headerMonth.toString() + " " + this.headerYear);
    }

    private void loadNow() {
        monthYearInput.setText(this.headerMonth.toString() + " " + this.headerYear);
        this.month.firstDayOfYear(this.isLeapYear);
        LocalDate start = YearMonth.of(this.headerYear, this.headerMonth).atDay(1);
        int first = start.getDayOfWeek().getValue();
        int count = 1;
        for (int i = first; i < first + numOfDays(); i++) {
            Label label = new Label();
            label.setText(Integer.toString(count));
            count++;
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(label);
            int col = parseCol(i);
            int row = parseRow(i);
//            System.out.println(String.format("Value: %d, Col: %d, Row: %d",i,col,row));
            fillCalender(box, col, row);
        }
    }

    private int parseCol(int value) {
        return (value + 6) % 7;
    }

    private int parseRow(int value) {
        return (value - 1) / 7 + 1;
    }

    private void nextPage() {

    }

    private void previousPage() {

    }

    private void emptyCal() {
        calenderGrid.getChildren().clear();
    }

    private void loadDayHeader() {
        for (int i = 0; i < 7; i++) {
            switch (i) {
            case 0:
                setDayHeader("Monday", 0);
            case 1:
                setDayHeader("Tuesday", 1);
            case 2:
                setDayHeader("Wednesday", 2);
            case 3:
                setDayHeader("Thursday", 3);
            case 4:
                setDayHeader("Friday", 4);
            case 5:
                setDayHeader("Saturday", 5);
            case 6:
                setDayHeader("Sunday", 6);
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
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(label);
        fillCalender(box, col, 0);
    }

    @FXML
    public void nextMonth() {
        updateHeader(1 );
        emptyCal();
        loadDayHeader();
        loadNow();
    }

    @FXML
    public void previousMonth() {
        updateHeader(-1);
        emptyCal();
        loadDayHeader();
        loadNow();
    }
}
