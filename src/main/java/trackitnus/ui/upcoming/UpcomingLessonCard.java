package trackitnus.ui.upcoming;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import trackitnus.model.lesson.Lesson;
import trackitnus.ui.UiPart;

/**
 * An UI component that displays information of a {@code Lesson}.
 */
public class UpcomingLessonCard extends UiPart<Region> {

    private static final String FXML = "Upcoming/UpcomingLessonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TrackIter level 4</a>
     */

    public final Lesson lesson;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label code;
    @FXML
    private Label type;
    @FXML
    private Label date;

    /**
     * Creates a {@code ContactCode} with the given {@code Lesson} and index to display.
     */
    public UpcomingLessonCard(Lesson lesson, int displayedIndex, Color lessonColor) {
        super(FXML);
        this.lesson = lesson;
        id.setText("[" + displayedIndex + "] ");
        code.setText(lesson.getCode().code);
        type.setText(lesson.getType().name());
        date.setText(lesson.getTime().toString().substring(4));
        date.setStyle("-fx-text-fill: " + getColorHex(lessonColor) + ";");
    }

    /**
     * Get the hexcode color from a java.scene.paint.Color class for Label fill.
     *
     * @param color the color to generate hexcode.
     * @return String the hexcode of the color.
     */
    public String getColorHex(Color color) {
        java.awt.Color c = new java.awt.Color((float) color.getRed(),
            (float) color.getGreen(),
            (float) color.getBlue(),
            (float) color.getOpacity());
        String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
        return hex;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpcomingLessonCard)) {
            return false;
        }

        // state check
        UpcomingLessonCard card = (UpcomingLessonCard) other;
        return id.getText().equals(card.id.getText())
            && lesson.equals(card.lesson);
    }
}

