package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays one of the personal details of a {@code Patient} as a row of a list.
 */
public class PersonalDetailsRow extends UiPart<Region> {
    private static final String FXML = "PersonalDetailsRow.fxml";

    @FXML
    private HBox row;
    @FXML
    private Label fieldName;
    @FXML
    private Label value;

    /**
     * Creates a {@code PersonalDetailsRow} for a field of a {@code Patient} with the given value of the field.
     */
    public PersonalDetailsRow(String fieldName, String value) {
        super(FXML);

        this.fieldName.setText(fieldName);
        this.value.setText(value);
    }
}
