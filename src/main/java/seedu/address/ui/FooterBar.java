package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the footer bar that is displayed at the footer of the application.
 */
public class FooterBar extends UiPart<Region> {

    private static final String FXML = "FooterBar.fxml";

    @FXML
    private Label versionNumber;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public FooterBar(String versionNum) {
        super(FXML);
        versionNumber.setText(versionNum);
    }
}
