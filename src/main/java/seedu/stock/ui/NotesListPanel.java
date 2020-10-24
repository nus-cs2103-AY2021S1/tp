package seedu.stock.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.stock.Stock;

import java.util.logging.Logger;

public class NotesListPanel extends UiPart<Region> {

    private static final String FXML = "StockListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StockListPanel.class);

    @FXML
    private ListView<Stock> notesListView;

    /**
     * Creates a {@code StockListPanel} with the given {@code ObservableList}.
     */
    public NotesListPanel(ObservableList<Stock> stockList) {
        super(FXML);
        notesListView.setItems(stockList);
        notesListView.setCellFactory(listView -> new NotesListPanel.StockListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stock} using a {@code StockCard}.
     */
    class StockListViewCell extends ListCell<Stock> {
        @Override
        protected void updateItem(Stock stock, boolean empty) {
            super.updateItem(stock, empty);

            if (empty || stock == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StockCard(stock, getIndex() + 1).getRoot());
            }
        }
    }
}
