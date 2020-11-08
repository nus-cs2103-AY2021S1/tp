package seedu.cc.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.model.account.entry.Revenue;

public class RevenueListPanel extends UiPart<Region> {
    private static final String FXML = "RevenueListPanel.fxml";
    private static final String REVENUE_STRING = "Revenues";
    private final Logger logger = LogsCenter.getLogger(RevenueListPanel.class);

    @FXML
    private Label revenueLabel;

    @FXML
    private ListView<Revenue> revenueListView;

    /**
     * Creates a {@code ExpenseListPanel} with the given {@code ObservableList}.
     */
    public RevenueListPanel(ObservableList<Revenue> revenues) {
        super(FXML);
        revenueLabel.setText(REVENUE_STRING);
        revenueListView.setItems(revenues);
        revenueListView.setCellFactory(listView -> new RevenueListViewCell());
    }

    class RevenueListViewCell extends ListCell<Revenue> {
        @Override
        protected void updateItem(Revenue entry, boolean isEmpty) {
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
