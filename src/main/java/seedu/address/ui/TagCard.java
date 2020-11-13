package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Tag}.
 */
public class TagCard extends UiPart<Region> {

    private static final String FXML = "TagCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tag tag;

    @FXML
    private HBox cardPane;
    @FXML
    private Label tagName;
    @FXML
    private Label id;
    @FXML
    private Label fileAddress;
    @FXML
    private HBox labels;

    /**
     * Creates a {@code PersonCode} with the given {@code Tag} and index to display.
     */
    public TagCard(Tag tag, int displayedIndex) {
        super(FXML);
        this.tag = tag;
        id.setText(displayedIndex + ". ");
        tagName.setText(tag.getTagName().tagName);
        fileAddress.setText(tag.getFileAddress().value);
        for (seedu.address.model.label.Label label : tag.getLabels()) {
            String labelText = " " + label.getLabel() + " ";
            Label labelBox = new Label(labelText);
            labelBox.getStyleClass().add("label-box");
            labels.getChildren().add(labelBox);
        }
    }

    /**
     * Displays the information of the tag in the main window.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    public void showTagInfo() {
        MainWindow mainWindow = MainWindow.getInstance();
        if (mainWindow == null) {
            return;
        }

        String showInfoCommand = ShowCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_TAG_NAME + tag.getTagName();
        try {
            mainWindow.executeCommand(showInfoCommand);
        } catch (CommandException | ParseException exception) {
            // do nothing
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCard)) {
            return false;
        }

        // state check
        TagCard card = (TagCard) other;
        return id.getText().equals(card.id.getText())
                && tag.equals(card.tag);
    }
}
