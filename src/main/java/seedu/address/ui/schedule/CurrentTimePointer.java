package seedu.address.ui.schedule;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class CurrentTimePointer extends UiPart<Region> {
    private static final String FXML = "schedule/CurrentTimePointer.fxml";

    @FXML
    private Label currentTime;
    @FXML
    private HBox currentTimePointer;

    /**
     * Constructor of CurrentTimePointer
     * @param time must be the format of hh:mm AM/PM
     */
    public CurrentTimePointer(String time) {
        super(FXML);
        currentTime.setText(time);
    }

    public void updateTime(String time) {
        currentTime.setText(time);
    }


}
