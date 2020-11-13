package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class StatusBarDaySelected extends UiPart<Region> {

    private static final String FXML = "StatusBarDaySelected.fxml";

    @FXML
    private Label dateSelectedLabel;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarDaySelected() {
        super(FXML);
    }

    public void setDateSelectedLabel(String dateSelected) {
        dateSelectedLabel.setText("   Date selected: " + dateSelected);
    }

    public void clear() {
        dateSelectedLabel.setText("");
    }

}
