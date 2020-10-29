package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;

public class DetailDisplay extends UiPart<Region> {

    private static final String FXML = "DetailsDisplay.fxml";

    @FXML
    private TextArea detailsDisplay;

    public DetailDisplay() {
        super(FXML);
    }

    public void setDisplay(CommandResult result) {
        detailsDisplay.setText(result.getFeedbackToUser());
    }
}
