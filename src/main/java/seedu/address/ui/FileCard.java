package seedu.address.ui;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.AppUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * An UI component that displays information of a {@code File}.
 */
public class FileCard extends UiPart<Region> {

    private static final String FXML = "FileCard.fxml";
    private static final Image FILE_ICON = AppUtil.getImage("/images/file_icon.png");
    private static final Image FOLDER_ICON = AppUtil.getImage("/images/folder_icon.png");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final File file;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView icon;
    @FXML
    private Label fileName;

    /**
     * Creates a {@code FileCard} with the given {@code File}.
     */
    public FileCard(File file) {
        super(FXML);
        this.file = file;
        fileName.setText(file.getName());

        if (file.isDirectory()) {
            icon.setImage(FOLDER_ICON);
        } else {
            icon.setImage(FILE_ICON);
        }
    }

    /**
     * Changes the current directory to this path.
     */
    public void cdToThisPath() {
        MainWindow mainWindow = MainWindow.getInstance();
        if (mainWindow == null) {
            return;
        }

        String command = "cd ./" + fileName.getText();
        try {
            mainWindow.executeCommand(command);
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
        if (!(other instanceof FileCard)) {
            return false;
        }

        // state check
        FileCard card = (FileCard) other;
        return file.equals(card.file);
    }
}
