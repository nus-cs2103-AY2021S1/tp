package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.account.entry.Entry;

import java.util.logging.Logger;

public class EntryListPanel extends UiPart<Region> {
    private static final String FXML = "EntryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EntryListPanel.class);

    @FXML
    private ListView<Entry> entryListView;

    public EntryListPanel(ObservableList<Entry> entryList) {
        super(FXML);
        entryListView.setItems(entryList);
        entryListView.setCellFactory(listView -> new EntryListViewCell());
    }

    class EntryListViewCell extends ListCell<Entry> {
        @Override
        protected void updateItem(Entry entry, boolean isEmpty) {
            super.updateItem(entry, isEmpty);

            if (isEmpty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EntryCard(entry, getIndex() + 1).getRoot());
            }
        }
    }

}
