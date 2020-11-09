package seedu.address.ui.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.friend.Friend;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays information of a {@code Friend}.
 */
public class FriendCard extends UiPart<Region> {

    private static final String FXML = "cards/FriendListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     */

    public final Friend friend;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label passport;
    @FXML
    private Label mobile;

    /**
     * Creates a {@code FriendCard} with the given {@code Friend} and index to display.
     */
    public FriendCard(Friend friend, int displayedIndex) {
        super(FXML);
        this.friend = friend;
        id.setText(displayedIndex + ". ");
        name.setText(friend.getName().toString());
        passport.setText("Passport: " + friend.getPassport().toString());
        mobile.setText("Mobile: " + friend.getMobile().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        FriendCard card = (FriendCard) other;
        return id.getText().equals(card.id.getText())
                && friend.equals(card.friend);
    }
}
