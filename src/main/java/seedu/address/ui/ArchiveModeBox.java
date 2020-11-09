package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ArchiveModeBox extends UiPart<Region> {

    private static final String FXML = "ArchiveModeBox.fxml";

    public final BooleanProperty isArchiveModeProperty;

    @FXML
    private Label archiveMode;

    /**
     * Creates a {@code ArchiveStatusBox} with the given {@code isArchiveModeProperty}.
     */
    public ArchiveModeBox(BooleanProperty isArchiveModeProperty) {
        super(FXML);

        requireNonNull(isArchiveModeProperty);
        this.isArchiveModeProperty = isArchiveModeProperty;

        archiveMode.setText(archiveTextDisplay(isArchiveModeProperty.get())); // set initial value
        // set value on change
        isArchiveModeProperty.addListener((observable, oldValue, newValue)->
                archiveMode.setText(archiveTextDisplay(newValue)));
    }

    private String archiveTextDisplay(boolean isArchiveModeBoolean) {
        if (isArchiveModeBoolean) {
            return "Archived Client List";
        } else {
            return "Active Client List";
        }
    }
}
