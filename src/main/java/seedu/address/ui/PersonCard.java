package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.log.Log;

/**
 * An UI component that displays information of a {@code Log}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Log log;

    @FXML
    private HBox cardPane;
    @FXML
    private Label exercise;
    @FXML
    private Label id;
    @FXML
    private Label time;
    @FXML
    private Label reps;
    @FXML
    private Label comments;

    /**
     * Creates a {@code PersonCode} with the given {@code Log} and index to display.
     */
    public PersonCard(Log log, int displayedIndex) {
        super(FXML);
        this.log = log;
        id.setText(displayedIndex + ". ");
        exercise.setText(log.getExercise().getName().value);
        time.setText(log.getPrettyDateTime());
        reps.setText(log.getReps().value);
        comments.setText(log.getComment().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && log.equals(card.log);
    }
}
