package seedu.taskmaster.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.taskmaster.model.session.StudentRecord;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentRecordListCard extends UiPart<Region> {

    private static final String FXML = "StudentRecordListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final StudentRecord studentRecord;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label nusnetId;
    @FXML
    private Label attendanceStatus;
    @FXML
    private Label name;

    /**
     * Creates a {@code StudentRecordListCard} with the given {@code AttendanceType} and index to display.
     */
    public StudentRecordListCard(StudentRecord studentRecord, int displayedIndex) {
        super(FXML);
        this.studentRecord = studentRecord;
        id.setText(displayedIndex + ". ");
        name.setText(studentRecord.getName().fullName);
        nusnetId.setText(studentRecord.getNusnetId().value);
        attendanceStatus.setText(studentRecord.getAttendanceType().name());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentRecordListCard)) {
            return false;
        }

        // state check
        StudentRecordListCard card = (StudentRecordListCard) other;
        return this.studentRecord.equals(card.studentRecord);
    }
}
