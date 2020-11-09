package seedu.medibook.ui.patientprofile;

import java.util.Comparator;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays one of the medical details of a {@code Patient} as a row of a list.
 */
public class MedicalDetailsRow extends UiPart<Region> {
    private static final String FXML = "MedicalDetailsRow.fxml";

    @FXML
    private HBox row;
    @FXML
    private Label fieldName;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code MedicalDetailsRow} for a medical detail of a {@code Patient} given the category and tags.
     */
    public MedicalDetailsRow(String fieldName, Set<Tag> tags) {
        super(FXML);

        this.fieldName.setText(fieldName);
        tags.stream().sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> {
                this.tags.getChildren().add(new Label(tag.tagName));
            });
    }
}
