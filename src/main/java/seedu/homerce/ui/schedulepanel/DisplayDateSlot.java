package seedu.homerce.ui.schedulepanel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * A region to display the date on the schedule.
 */
public class DisplayDateSlot extends SlotContainer {

    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d/M");
    private static final String FXML = "schedulepanel/DisplayDateSlot.fxml";

    @FXML
    private Label day;

    @FXML
    private Label date;

    /**
     * Constructor for a slot to be added to the schedule that displays the date.
     */
    public DisplayDateSlot(LocalDate dateDisplay) {
        super(FXML);

        String dayText = dateDisplay.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String dateText = dateDisplay.format(DISPLAY_FORMAT);

        day.setText(dayText);
        date.setText(dateText);
    }
}
