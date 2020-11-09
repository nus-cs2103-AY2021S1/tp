package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.grade.Assignment;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {

    private static final String FXML = "AssignmentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */


    public final Assignment assignment;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label assignmentName;
    @FXML
    private Label assignmentPercentage;
    @FXML
    private Label assignmentResult;

    /**
     * Creates a {@code AssignmentCard} with the given {@code Assignment} and index to display.
     */
    public AssignmentCard(Assignment assignment, int displayedIndex) {
        super(FXML);
        this.assignment = assignment;
        id.setText(displayedIndex + ". ");
        this.assignmentName.setText(assignment.getAssignmentName().get().assignmentName);
        this.assignmentPercentage.setText("Percentage Accessed % : "
                + assignment.getAssignmentPercentage().get().toString());
        this.assignmentResult.setText("Result : "
                + assignment.getAssignmentResult().get().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentCard)) {
            return false;
        }

        // state check
        AssignmentCard card = (AssignmentCard) other;
        return id.getText().equals(card.id.getText())
                && assignment.equals(((AssignmentCard) other).assignment);
    }
}
