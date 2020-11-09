package seedu.address.ui;

import java.time.LocalDate;
import java.time.YearMonth;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
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

/**
 * Calendar View UI.
 */
public class CalendarView extends UiPart<Region> {

    private static final String FXML = "CalendarView.fxml";
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    private static final int TOTAL_NUM_OF_DATEGRID = 42;
    private static final int NUM_OF_DAYS_PER_WEEK = 7;

    private int[] simulateGridPane = new int[42];
    private int day;
    private int month;
    private int year;
    private int previousMonthBalance;
    private int nextMonthBalance;
    private int currentMonthBalance;
    private YearMonth yearMonth;
    private LocalDate todayDate;
    private LocalDate fixedDatePointer;
    private LocalDate datePointer;
    private LocalDate firstDayOfTheMonth;

    @FXML
    private Label mmyyyyLabel;

    @FXML
    private GridPane mmyyyyGridPane;

    @FXML
    private GridPane dateGridPane;

    @FXML
    private GridPane everydayGridPane;

    /**
     * Constructs the CalendarView.
     */
    public CalendarView() {
        super(FXML);
        todayDate = LocalDate.now();
        datePointer = todayDate;
        fixedDatePointer = todayDate;
        day = todayDate.getDayOfMonth();
        month = todayDate.getMonthValue();
        year = todayDate.getYear();
        yearMonth = YearMonth.of(year, month);
        firstDayOfTheMonth = yearMonth.atDay(1);
        setMonthYearLabel();
        createCalenderView();
    }

    private void updateDayMonthYear(LocalDate date) {
        year = date.getYear();
        month = date.getMonthValue();
        day = date.getDayOfMonth();
        yearMonth = YearMonth.of(year, month);
        firstDayOfTheMonth = yearMonth.atDay(1);
    }

    private boolean isSameMonth(LocalDate d1, LocalDate d2) {
        LocalDate pivot = d1.withDayOfMonth(1);
        LocalDate toCheck = d2.withDayOfMonth(1);
        return pivot.equals(toCheck);
    }

    /**
     * Returns the total number of days in a month based on the year and month.
     *
     * @return return the total number of days.
     */
    public int getNumberOfDaysInTheMonth() {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Returns the total number of days in a month based on the year and month.
     *
     * @return return the total number of days.
     */
    public int getNumberOfDaysOfAMonth(int month, int year) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /**
     * Returns the total number of days in the previous month based on the year and month.
     *
     * @return return the total number of days.
     */
    public int getNumberOfDaysInPreviousMonth() {
        if (month > 1) {
            return getNumberOfDaysOfAMonth(month - 1, year);
        }
        return getNumberOfDaysOfAMonth(11, year);
    }

    private void setMonthYearLabel() {
        mmyyyyGridPane.setBackground(new Background(
                new BackgroundFill(
                        Color.valueOf("383838"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));
        String result = MONTHS[month - 1] + " - " + fixedDatePointer.getYear();
        mmyyyyLabel.setText(result);
    }

    private Label createLabelDay(int dayNumber) {
        Label label = new Label();
        label.setText(String.valueOf(dayNumber));
        label.setStyle("-fx-text-fill: white");
        return label;
    }

    private VBox placeHolderForLabel() {
        VBox holder = new VBox();
        holder.setFillWidth(false);
        holder.setPrefHeight(20);
        holder.setPrefWidth(20);
        holder.setMinSize(20, 20);
        holder.setMaxSize(30, 30);
        holder.setAlignment(Pos.CENTER);
        return holder;
    }

    private void fill() {
        currentMonthBalance = getNumberOfDaysInTheMonth();
        int firstDayOfMonth = firstDayOfTheMonth.getDayOfWeek().getValue();
        previousMonthBalance = firstDayOfMonth % NUM_OF_DAYS_PER_WEEK;
        int firstDay = getNumberOfDaysInPreviousMonth() - previousMonthBalance + 1;

        for (int i = 0; i < previousMonthBalance; i++) {
            simulateGridPane[i] = firstDay;
            firstDay++;
        }

        for (int i = 0; i < currentMonthBalance; i++) {
            simulateGridPane[previousMonthBalance + i] = i + 1;
        }

        nextMonthBalance = TOTAL_NUM_OF_DATEGRID - currentMonthBalance - previousMonthBalance;
        int pointerInNextMonth = currentMonthBalance + previousMonthBalance;
        for (int i = 0; i < nextMonthBalance; i++) {
            simulateGridPane[pointerInNextMonth + i] = i + 1;
        }
    }

    /**
     * Creates Vbox-es for each grid. Every Vbox contains a Label with a text indicating the date.
     */
    public void createCalenderView() {
        fill();

        int currentDateGrid = 0;
        everydayGridPane.setBackground(new Background(
                new BackgroundFill(Color.valueOf("383838"), CornerRadii.EMPTY, Insets.EMPTY)));
        dateGridPane.setBackground(new Background(
                new BackgroundFill(Color.valueOf("383838"), CornerRadii.EMPTY, Insets.EMPTY)));

        for (int row = 0; row <= 5; row++) {
            for (int col = 0; col <= 6; col++) {
                VBox holder = placeHolderForLabel();

                if ((currentDateGrid < previousMonthBalance)
                        || (currentDateGrid > TOTAL_NUM_OF_DATEGRID - 1 - nextMonthBalance)) {
                    holder.setBlendMode(BlendMode.SOFT_LIGHT);
                }

                if ((currentDateGrid == previousMonthBalance + day - 1)
                        && (isSameMonth(datePointer, fixedDatePointer))) {
                    holder.setBackground(new Background(
                            new BackgroundFill(Color.DARKORANGE.darker(), CornerRadii.EMPTY, Insets.EMPTY)));

                    holder.setBorder(new Border(new BorderStroke(Color.valueOf("#FFFFFF"),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                }

                Label labelDay = createLabelDay(simulateGridPane[currentDateGrid]);
                holder.getChildren().add(labelDay);
                dateGridPane.add(holder, col, row);
                GridPane.setHalignment(holder, HPos.CENTER);
                GridPane.setValignment(holder, VPos.CENTER);

                currentDateGrid++;
            }
        }
    }

}
