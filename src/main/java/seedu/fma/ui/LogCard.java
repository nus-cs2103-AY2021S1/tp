package seedu.fma.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Duration;
import seedu.fma.model.log.Log;

/**
 * An UI component that displays information of a {@code Log}.
 */
public class LogCard extends UiPart<Region> {

    private static final String FXML = "LogListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on LogBook level 4</a>
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
    private Label calories;
    @FXML
    private Label comments;

    /**
     * Creates a {@code LogCode} with the given {@code Log} and index to display.
     */
    public LogCard(Log log, int displayedIndex) {
        super(FXML);
        this.log = log;
        id.setText(displayedIndex + ". ");
        exercise.setText(log.getExercise().getName().value);
        time.setText(log.getPrettyDateTime());
        reps.setText(log.getReps().toString());
        calories.setText(String.valueOf(log.getCalories()));

        comments.setText("Comments: " + log.getComment().value);

        // Add hover tool tip when comment is too long
        if (log.getComment().value.length() > 40) {
            Tooltip commentToolTip = new Tooltip(log.toString());
            comments.setFont(Font.font("Segoe UI", 12));
            comments.setTooltip(commentToolTip);
            commentToolTip.setShowDelay(Duration.seconds(1));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LogCard)) {
            return false;
        }

        // state check
        LogCard card = (LogCard) other;
        return id.getText().equals(card.id.getText())
                && log.equals(card.log);
    }
}
