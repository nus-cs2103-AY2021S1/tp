package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.quiz.Attempt;

/**
 * An UI component that displays information of a {@code Attempt}.
 */
public class AttemptCard extends UiPart<Region> {

    private static final String FXML = "AttemptListCard.fxml";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy    HH:mm");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Attempt attempt;

    @FXML
    private HBox cardPane;
    @FXML
    private Label attemptDate;
    @FXML
    private Label id;
    @FXML
    private Label score;
    @FXML
    private FlowPane tags;
    @FXML
    private Label priority;



    /**
     * Creates a {@code PersonCode} with the given {@code Attempt} and index to display.
     */
    public AttemptCard(Attempt attempt, int displayedIndex) {
        super(FXML);
        this.attempt = attempt;
        id.setText(displayedIndex + ". ");
        attemptDate.setText(dtf.format(attempt.getTimestamp()));
        score.setText(attempt.attemptAnalysis());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttemptCard)) {
            return false;
        }

        // state check
        AttemptCard card = (AttemptCard) other;
        return id.getText().equals(card.id.getText())
                && attempt.equals(card.attempt);
    }
}
