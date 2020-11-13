package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.day.Day;

/**
 * Panel containing the list of days.
 */
public class DayListPanel extends UiPart<Region> {
    private static final String FXML = "DayListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DayListPanel.class);

    @FXML
    private ListView<Day> dayListView;

    private MainWindow mainWindow;

    /**
     * Creates a {@code DayListPanel} with the given {@code ObservableList} and given {@code MainWindow}.
     */
    public DayListPanel(ObservableList<Day> dayList, MainWindow mainWindow) {
        super(FXML);
        dayListView.setItems(dayList);
        dayListView.setCellFactory(listView -> new DayListViewCell());
        this.mainWindow = mainWindow;
        logger.info("DayListPanel created");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Day} using a {@code DayCard}.
     */
    class DayListViewCell extends ListCell<Day> {
        @Override
        protected void updateItem(Day day, boolean empty) {
            super.updateItem(day, empty);

            if (empty || day == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DayCard(day, getIndex() + 1, mainWindow).getRoot());
            }
        }
    }

}
