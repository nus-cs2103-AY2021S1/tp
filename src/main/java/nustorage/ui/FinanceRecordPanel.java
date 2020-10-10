package nustorage.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import nustorage.commons.core.LogsCenter;
import nustorage.model.record.FinanceRecord;

import javafx.scene.layout.Region;
import java.util.logging.Logger;

public class FinanceRecordPanel extends UiPart<Region> {
    private static final String FXML = "FinanceListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FinanceRecordPanel.class);

    @javafx.fxml.FXML
    private ListView<FinanceRecord> financeListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FinanceRecordPanel(ObservableList<FinanceRecord> financeList) {
        super(FXML);
        financeListView.setItems(financeList);
        financeListView.setCellFactory(listView -> new FinanceRecordPanel.FinanceListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class FinanceListViewCell extends ListCell<FinanceRecord> {
        @Override
        protected void updateItem(FinanceRecord financeRecord, boolean empty) {
            super.updateItem(financeRecord, empty);

            if (empty || financeRecord == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FinanceRecordCard(financeRecord, getIndex() + 1).getRoot());
            }
        }
    }

}
