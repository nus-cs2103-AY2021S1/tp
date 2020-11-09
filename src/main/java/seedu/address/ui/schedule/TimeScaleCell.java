package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/*
Code adapted from AY2021S1-CS2103T-F12-2/tp (https://github.com/AY2021S1-CS2103T-F12-2/tp)
 */
public class TimeScaleCell extends UiPart<Region> {
    private static final String FXML = "schedule/TimeScaleCell.fxml";

    @FXML
    private Label time;

    private String timeStr;

    TimeScaleCell(String timeString) {
        super(FXML);
        time.setText(timeString);
        //memoize the time, for hiding purpose.
        this.timeStr = timeString;
    }

    public void hideTime() {
        time.setText("  ");
    }

    public void recoverTime() {
        time.setText(timeStr);
    }
}
