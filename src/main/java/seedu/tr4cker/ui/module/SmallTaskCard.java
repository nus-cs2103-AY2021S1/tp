package seedu.tr4cker.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays truncated information of a {@code Task}.
 */
public class SmallTaskCard extends UiPart<Region> {

    private static final String FXML = "SmallTaskCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Tr4cker level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label deadline;
    @FXML
    private Label id;

    /**
     * Creates a {@code SmallTaskCard} with the given {@code task} and index to display.
     */
    public SmallTaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().taskName);
        deadline.setText(task.getDeadline().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SmallTaskCard)) {
            return false;
        }

        // state check
        SmallTaskCard card = (SmallTaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
