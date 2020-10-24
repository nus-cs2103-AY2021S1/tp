package chopchop.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for displaying pinned information.
 * Displays results of statistics.
 */
public class PinBox extends UiPart<Region> {

    private static final String FXML = "PinBox.fxml";

    @FXML
    private TextArea pins;

    @FXML
    private TextArea header1;


    /**
     * Creates a {@code PinBox}.
     */
    public PinBox() {
        super(FXML);
        pins.setText("Statistics\nTo be implemented.");
        header1.setText("This is header 1");
    }
}
