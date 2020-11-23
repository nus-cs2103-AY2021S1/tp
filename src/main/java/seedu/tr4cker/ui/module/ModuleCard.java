package seedu.tr4cker.ui.module;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Tr4cker level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleName;
    @FXML
    private Label moduleCode;
    @FXML
    private Label id;
    @FXML
    private ListView<Task> smallTaskListView;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex, ObservableList<Task> taskList) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ") ");
        moduleName.setText(module.moduleName);
        moduleCode.setText(module.moduleCode.toString());
        smallTaskListView.setItems(taskList.filtered(
            task -> task.getModuleCode().contains(module.moduleCode)
                    && !task.isCompleted())); // only incomplete tasks shown
        smallTaskListView.setCellFactory(listView -> new ModuleTasksListViewCell());
        smallTaskListView.setPrefHeight(smallTaskListView.getItems().size() * 40 + 2);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code SmallTaskCard}.
     */
    class ModuleTasksListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SmallTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
