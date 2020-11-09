package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * The UI component that is responsible for displaying CAP.
 */
public class CapBox extends UiPart<Region> {
    private static final String FXML = "CapBox.fxml";

    @FXML
    private Text currentCapDisplay;

    /**
     * Creates a {@code CapBox} with the given {@code currentCap}.
     */
    public CapBox(String currentCap) {
        super(FXML);
        setCapDisplay(currentCap);
    }

    /**
     * Set the display CAP's value.
     *
     * @param currentCap CAP to be displayed
     */
    public void setCapDisplay(String currentCap) {
        requireNonNull(currentCap);
        currentCapDisplay.setText("Current CAP: " + currentCap);
    }

    public Text getCurrentCapDisplay() {
        return currentCapDisplay;
    }
}
