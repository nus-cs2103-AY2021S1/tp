package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.project.Project;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Project> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Project> projectList) {
        super(FXML);
        personListView.setItems(projectList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Project} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Project> {
        @Override
        protected void updateItem(Project project, boolean empty) {
            super.updateItem(project, empty);

            if (empty || project == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(project, getIndex() + 1).getRoot());
            }
        }
    }

}
