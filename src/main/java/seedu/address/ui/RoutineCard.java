package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.routine.Routine;

/**
 * An UI component that displays information of a {@code Exercise}.
 */
public class RoutineCard extends UiPart<Region> {

    private static final String FXML = "RoutineListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitNus level 4</a>
     */

    public final Routine routine;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ExerciseCode} with the given {@code Exercise} and index to display.
     */
    public RoutineCard(Routine routine, int displayedIndex) {
        super(FXML);
        this.routine = routine;
        id.setText(displayedIndex + ". ");
        name.setText(routine.getName().fullName);
        routine.getExercises().stream()
                .sorted(Comparator.comparing(exercise -> exercise.getName().fullName))
                .forEach(exercise -> tags.getChildren().add(new Label(exercise.getName().fullName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RoutineCard)) {
            return false;
        }

        // state check
        RoutineCard card = (RoutineCard) other;
        return id.getText().equals(card.id.getText())
                && routine.equals(card.routine);
    }
}
