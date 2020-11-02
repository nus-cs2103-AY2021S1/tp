package seedu.stock.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.stock.Stock;

public class StockViewWindow extends UiPart<Region> {

    private static final String FXML = "StockViewWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(StockViewWindow.class);
    private String serialNumberOfStock;

    @FXML
    private ListView<String> stockView;

    /**
     * Default constructor for Statistics Window tab.
     */
    public StockViewWindow() {
        super(FXML);
    }

    /**
     * Creates a {@code StockListPanel} with the given {@code ObservableList}.
     */
    public StockViewWindow(ObservableList<String> fieldList) {
        super(FXML);
        String[] headerSerialNumber = fieldList.get(1).split(": ", 2);
        this.serialNumberOfStock = headerSerialNumber[1];
        stockView.setItems(fieldList);
        stockView.setCellFactory(listView -> new StockViewWindow.StockViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stock} using a {@code StockCard}.
     */
    class StockViewCell extends ListCell<String> {
        @Override
        protected void updateItem(String field, boolean empty) {
            super.updateItem(field, empty);

            if (empty || field == null) {
                setGraphic(null);
                setText(null);
            } else {
                String[] headerBody = field.split(": ", 2);
                setGraphic(new StockViewCard(upperFirst(headerBody[1]), headerBody[0]).getRoot());
            }
        }
    }

    public boolean isUsedBefore() {
        return this.serialNumberOfStock != null;
    }

    public Optional<Stock> getStockToView(ObservableList<Stock> stockList) {

        String serialNumberString = serialNumberOfStock;
        Optional<Stock> stockToView = Optional.empty();
        // Find the stock to view
        for (Stock currentStock : stockList) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            if (currentStockSerialNumber.equals(serialNumberString)) {
                stockToView = Optional.of(currentStock);
                break;
            }
        }
        return stockToView;
    }


    /**
     * Returns the input string with the first letter being upper-cased
     * @param toChange
     * @return input string with first letter being upper-cased
     */
    public String upperFirst(String toChange) {
        String upperCase = toChange.substring(0, 1).toUpperCase();
        String lowerCase = toChange.substring(1);

        return upperCase + lowerCase;
    }


}
