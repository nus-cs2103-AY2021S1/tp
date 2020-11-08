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
     * Default constructor for Stock View tab.
     */
    public StockViewWindow() {
        super(FXML);
    }

    /**
     * Creates a {@code StockViewWindow} with the given {@code ObservableList}.
     */
    public StockViewWindow(ObservableList<String> fieldList) {
        super(FXML);
        String[] headerSerialNumber = fieldList.get(1).split(": ", 2);
        this.serialNumberOfStock = headerSerialNumber[1];
        stockView.setItems(fieldList);
        stockView.setCellFactory(listView -> new StockViewWindow.StockViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a details of a stock
     * using a {@code StockViewCard}.
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
                String header = headerBody[0];
                String body = headerBody[1];

                // Serial number is displayed in all uppercase
                if (header.equals("Serial Number")) {
                    body = body.toUpperCase();
                } else {
                    // the rest of the details are displayed with first character upper cased
                    // and the rest lowered
                    body = upperCaseFirstCharacter(body);
                }

                setGraphic(new StockViewCard(body, header).getRoot());
            }
        }
    }

    public Optional<Stock> getStockToView(ObservableList<Stock> stockList) {
        assert stockList != null;

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
     * @param toChange the string to change
     * @return input string with first letter being upper-cased
     */
    public String upperCaseFirstCharacter(String toChange) {

        String upperCased = "";
        if (toChange != null && !toChange.isEmpty()) {
            String upperCase = toChange.substring(0, 1).toUpperCase();
            String lowerCase = toChange.substring(1);
            upperCased = upperCase + lowerCase;
        }

        return upperCased;
    }

}
