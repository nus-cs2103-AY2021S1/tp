package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.account.entry.Entry;

import javax.swing.plaf.synth.Region;
import java.util.Comparator;

public class EntryCard extends UiPart<Region> {

    private static final String FXML = "EntryCard.FXML";

    public final Entry entry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label amount;
    @FXML
    private Label description;
    @FXML
    private Label entryCategory;
    @FXML
    private FlowPane tags;

    public EntryCard(Entry entry, int displayIndex) {
        super(FXML);
        this.entry = entry;
        id.setText(displayIndex + ". ");
        amount.setText(entry.getAmount().toString());
        description.setText(entry.getDescription().toString());
        // TODO: change this to Catergory
        // entryCategory.setText(entry.getCategory());
        entry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntryCard)) {
            return false;
        }

        // state check
        EntryCard card = (EntryCard) other;
        return id.getText().equals(card.id.getText())
                && entry.equals(card.entry);
    }
}
