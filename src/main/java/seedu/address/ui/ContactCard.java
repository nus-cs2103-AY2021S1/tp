package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.contact.Contact;

/**
 * An UI component that displays information of a {@code Contact}.
 */
public class ContactCard extends UiPart<Region> {

    private static final String FXML = "ContactListCard.fxml";

    public final Contact contact;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label isImportant;
    @FXML
    private Label email;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane contactTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Contact} and index to display.
     */
    public ContactCard(Contact contact, int displayedIndex) {

        super(FXML);
        this.contact = contact;
        id.setText(displayedIndex + ". ");
        name.setText(contact.getName().toString());
        if (contact.isImportant()) {
            isImportant.setStyle("-fx-background-color: #708090");
            isImportant.setText("Important");
        }
        email.setText(contact.getEmail().toString());
        if (contact.getTelegram().isPresent()) {
            telegram.setText(contact.getTelegram().get().toString());
        } else {
            telegram.setText("-");
        }
        contact.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> contactTags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactCard)) {
            return false;
        }

        // state check
        ContactCard card = (ContactCard) other;
        return id.getText().equals(card.id.getText())
                && contact.equals(card.contact);
    }
}
