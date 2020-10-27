package seedu.address.ui;

import java.io.File;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.explorer.CurrentPath;

/**
 * Panel of the file explorer.
 */
public class FileExplorerPanel extends UiPart<Region> {
    private static final String FXML = "FileExplorerPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    private CurrentPath currentPath;

    @FXML
    private Label currentPathDisplay;
    @FXML
    private ListView<File> fileListView;

    /**
     * Create a default {@code FileExplorerPanel} that cannot display current path and file list.
     */
    public FileExplorerPanel() {
        super(FXML);
    }

    /**
     * Sets the current path and the list of children files.
     *
     * @param currentPath the current path
     * @param fileList the list of children files
     */
    public void setData(CurrentPath currentPath, ObservableList<File> fileList) {
        this.currentPath = currentPath;
        fileListView.setItems(fileList);
        fileListView.setCellFactory(listView -> new FileListViewCell());
        updateCurrentPath();
    }

    /**
     * Updates the current path displayed on UI.
     */
    public void updateCurrentPath() {
        currentPathDisplay.setText(currentPath.getAddress().value);
    }

    /**
     * Changes the current directory to the parent directory.
     */
    public void cdToParentPath() {
        MainWindow mainWindow = MainWindow.getInstance();

        String command = "cd ../";
        try {
            mainWindow.executeCommand(command);
        } catch (CommandException | ParseException exception) {
            // do nothing
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code File} using a {@code FileCard}.
     */
    class FileListViewCell extends ListCell<File> {
        @Override
        protected void updateItem(File file, boolean empty) {
            super.updateItem(file, empty);

            if (empty || file == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FileCard(file).getRoot());
            }
        }
    }

}
