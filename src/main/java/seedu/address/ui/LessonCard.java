package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.State;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox card;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label status;
    @FXML
    private CheckBox statusSign;
    @FXML
    private Label type;
    @FXML
    private HBox dateTimeHolder;
    @FXML
    private Label dateTime;
    @FXML
    private HBox descriptionHolder;
    @FXML
    private Label description;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
    }

    private boolean loadStatus(Task task) {
        if (task.getStatus().value.equals(State.COMPLETE)) {
            statusSign.setSelected(true);
        }
        status.setText(task.getStatus().value.toString());
        return true;
    }

    private boolean loadDateTime(Task task) {
        if (task.getDateTime().equals(DateTime.defaultDateTime())) {
            card.getChildren().remove(dateTimeHolder);
            return false;
        } else {
            dateTime.setText(task.getDateTime().toString());
            return true;
        }

    }

    private boolean loadDescription(Task task) {
        if (task.getDescription().equals(Description.defaultDescription())) {
            card.getChildren().remove(descriptionHolder);
            return false;
        } else {
            description.setText(task.getDescription().value.toString());
            return true;
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonCard)) {
            return false;
        }

        // state check
        LessonCard card = (LessonCard) other;
        return id.getText().equals(card.id.getText())
                && lesson.equals(card.lesson);
    }
}
