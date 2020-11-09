package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * The UI component that is responsible for displaying CAP.
 */
public class SemBox extends UiPart<Region> {
    private static final String FXML = "SemBox.fxml";

    @FXML
    private Text currentSemDisplay;

    /**
     * Creates a {@code SemBox} with the given {@code currentSem}.
     */
    public SemBox(String currentSem) {
        super(FXML);
        setSemDisplay(currentSem);
    }

    /**
     * Set the display CAP's value.
     *
     * @param currentCap CAP to be displayed
     */
    public void setSemDisplay(String currentCap) {
        requireNonNull(currentCap);
        currentSemDisplay.setText("Currently editing: " + currentCap);
    }

    public Text getCurrentSemDisplay() {
        return currentSemDisplay;
    }
}
