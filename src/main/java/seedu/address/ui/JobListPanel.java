package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.information.Job;

public class JobListPanel extends UiPart<Region> {
    private static final String FXML = "JobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private MainWindow mainWindow;

    @FXML
    private ListView<Job> jobListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public JobListPanel(ObservableList<Job> jobList, MainWindow mainWindow) {
        super(FXML);
        jobListView.setItems(jobList);
        jobListView.setCellFactory(listView -> new JobListPanel.JobListViewCell(mainWindow));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class JobListViewCell extends ListCell<Job> {

        private MainWindow mainWindow;

        public JobListViewCell(MainWindow mainWindow) {
            this.mainWindow = mainWindow;
        }
        @Override
        protected void updateItem(Job job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JobCard(job, getIndex() + 1).getRoot());
                setOnMouseClicked(event -> mainWindow.updateDetailedJobPanel(job));
            }
        }
    }
}
