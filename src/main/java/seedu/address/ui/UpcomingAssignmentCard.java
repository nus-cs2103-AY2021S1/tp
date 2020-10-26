package seedu.address.ui;

import static seedu.address.ui.UpcomingTaskListPanel.getDueDate;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.assignment.Assignment;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class UpcomingAssignmentCard extends UiPart<Region> {

    private static final String FXML = "UpcomingAssignmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Assignment assignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label dueDate;
    @FXML
    private Label moduleCode;

    /**
     * Creates a {@code AssignmentCode} with the given {@code Assignment} and index to display.
     */
    public UpcomingAssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(displayedIndex + ". ");
        name.setText(assignment.getName().fullName);
        getDueDate(dueDate, assignment.getDeadline());
        moduleCode.setText("Module: " + assignment.getModuleCode().moduleCode);
    }

    private String overdue(LocalDateTime due) {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpcomingAssignmentCard)) {
            return false;
        }

        // state check
        UpcomingAssignmentCard card = (UpcomingAssignmentCard) other;
        return id.getText().equals(card.id.getText())
                && assignment.equals(card.assignment);
    }
}
