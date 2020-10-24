package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ArchiveModeBox extends UiPart<Region> {

    private static final String FXML = "ArchiveModeBox.fxml";

    public final boolean isArchiveMode;

    @FXML
    private Label archiveMode;

    /**
     * Creates a {@code ArchiveStatusBox} with the given {@code isArchiveMode}.
     */
    public ArchiveModeBox(boolean isArchiveMode) {
        super(FXML);
        this.isArchiveMode = isArchiveMode;
        if (isArchiveMode) {
            archiveMode.setText("Archived Client List");
        } else {
            archiveMode.setText("Active Client List");
        }
    }
}
