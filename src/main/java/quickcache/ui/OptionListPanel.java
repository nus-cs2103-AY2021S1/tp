package quickcache.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import quickcache.commons.core.LogsCenter;

/**
 * Panel containing the list of options.
 */
public class OptionListPanel extends UiPart<Region> {
    private static final String FXML = "OptionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(OptionListPanel.class);

    @FXML
    private ListView<String> optionListView;

    /**
     * Creates a {@code OptionListPanel} with the given {@code ObservableList}.
     */
    public OptionListPanel(ObservableList<String> optionList) {
        super(FXML);
        optionListView.setItems(optionList);
        optionListView.setCellFactory(listView -> new OptionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Option} using an {@code OptionCard}.
     */
    class OptionListViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String option, boolean empty) {
            super.updateItem(option, empty);

            if (empty || option == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new OptionCard(option, getIndex() + 1).getRoot());
            }
        }
    }

}
