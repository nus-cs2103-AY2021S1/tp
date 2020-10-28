package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Description;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class LessonCard extends UiPart<Region> {

    private static final String FXML = "LessonCard.fxml";

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
    private FlowPane tags;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label dayOfWeek;
    @FXML
    private Label time;
    @FXML
    private Label recurrence;
    @FXML
    private HBox descriptionHolder;
    @FXML
    private Label description;

    /**
     * Creates a {@code LessonCode} with the given {@code Lesson} and index to display.
     */
    public LessonCard(Lesson lesson, int displayedIndex) {
        super(FXML);
        this.lesson = lesson;
        id.setText(displayedIndex + ". ");
        title.setText(lesson.getTitle().title);
        dayOfWeek.setText(lesson.getDayOfWeek().toString());
        time.setText(lesson.getStartTime().toString() + " - " + lesson.getEndTime().toString());
        recurrence.setText(lesson.getStartDate().toString() + " to " + lesson.getEndDate().toString());
        loadDescription(lesson);
        loadTag(lesson);
    }

    private void loadTag(Lesson lesson) {
        if (!lesson.getTag().tagName.equals("")) {
            tags.getChildren().add(new Label(lesson.getTag().tagName));
        }
    }

    private boolean loadDescription(Lesson lesson) {
        if (lesson.getDescription().equals(Description.defaultDescription())) {
            card.getChildren().remove(descriptionHolder);
            return false;
        } else {
            description.setText(lesson.getDescription().value.toString());
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
