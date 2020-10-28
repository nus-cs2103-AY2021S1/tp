package seedu.address.ui.card;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.event.Event;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Event event;

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
    private Label from;
    @FXML
    private Label to;
    @FXML
    private HBox descriptionHolder;
    @FXML
    private Label description;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        title.setText(event.getTitle().title);
        from.setText(event.getStartDateTime().toString());
        to.setText(event.getEndDateTime().toString());
        loadTag(event);
        loadDescription(event);
    }

    private void loadTag(Event event) {
        if (!event.getTag().tagName.equals("")) {
            tags.getChildren().add(new Label(event.getTag().tagName));
        }
    }

    private boolean loadDescription(Task task) {
        if (task.getDescription().equals(Description.defaultDescription())) {
            card.getChildren().remove(descriptionHolder);
            return false;
        } else {
            description.setText(task.getDescription().value);
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
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
