package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class TutorialGroupCard extends UiPart<Region> {
    private static final String FXML = "TutorialGroupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TutorialGroup tutorialGroup;

    @FXML
    private HBox cardPane;
    @FXML
    private Label groupId;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label dayOfWeek;
    //    @FXML
    //    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TutorialGroupCard(TutorialGroup tutorialGroup, int displayedIndex) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;
        id.setText(displayedIndex + ". ");
        groupId.setText(tutorialGroup.getId().toString());
        dayOfWeek.setText("Day: " + tutorialGroup.getDayOfWeek());
        startTime.setText("Start Time: " + tutorialGroup.getStartTime().toString().substring(0,5));
        endTime.setText("End Time: " + tutorialGroup.getEndTime().toString().substring(0,5));
        //totalStudents.setText("Total Students: " + module.getTotalStudents());
        //        startTime.setText(tutorialGroup.getStartTime().toString());
        //        endTime.setText(tutorialGroup.getEndTime().toString());
        //        duration.setText("" + tutorialGroup.getDurationInHours());

        //        person.getTags().stream()
        //                .sorted(Comparator.comparing(tag -> tag.tagName))
        //                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialGroupCard)) {
            return false;
        }

        // state check
        TutorialGroupCard card = (TutorialGroupCard) other;
        return id.getText().equals(card.id.getText())
            && tutorialGroup.equals(card.tutorialGroup);
    }
}
