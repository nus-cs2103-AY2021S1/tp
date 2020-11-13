package seedu.jarvis.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jarvis.model.mission.Mission;

/**
 * An UI component that displays information of a {@code Mission}.
 */
public class MissionCard extends UiPart<Region> {

    private static final String FXML = "MissionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Mission mission;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label deadline;

    /**
     * Creates a {@code MissionCard} with the given {@code Mission} and index to display.
     */
    public MissionCard(Mission mission, int displayedIndex) {
        super(FXML);
        this.mission = mission;
        id.setText(displayedIndex + ". ");
        title.setText(mission.getTitle());
        deadline.setText(mission.getDeadline());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MissionCard)) {
            return false;
        }

        // state check
        MissionCard card = (MissionCard) other;
        return id.getText().equals(card.id.getText())
                && mission.equals(card.mission);
    }
}

