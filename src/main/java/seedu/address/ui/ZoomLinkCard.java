package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ZoomLink;

/**
 * An UI component that displays information of a {@code ZoomLink}.
 */
public class ZoomLinkCard extends UiPart<Region> {

    private static final String FXML = "ZoomLinkCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    //public final ModuleLesson moduleLesson;

    public final ZoomLink zoomLink;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    //@FXML
    //private Label lessonName;
    @FXML
    private Label link;

    /**
     * Creates a {@code ZoomLinkCard} with the given {@code ZoomLink} and index to display.
     */
    public ZoomLinkCard(/*ModuleLesson moduleLesson,*/ZoomLink zoomLink, int displayedIndex) {
        super(FXML);
        //this.moduleLesson = moduleLesson;
        this.zoomLink = zoomLink;
        id.setText(displayedIndex + ". ");
        //lessonName.setText(moduleLesson.toString());
        link.setText(zoomLink.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ZoomLinkCard)) {
            return false;
        }

        // state check
        ZoomLinkCard card = (ZoomLinkCard) other;
        return id.getText().equals(card.id.getText())
                //&& moduleLesson.equals(((ZoomLinkCard) other).moduleLesson)
                && zoomLink.equals(((ZoomLinkCard) other).zoomLink);
    }
}
