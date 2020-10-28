package seedu.stock.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.Stock;

/**
 * An Immutable StockBook that is serializable to JSON format.
 */
@JsonRootName(value = "stockbook")
class JsonSerializableStockBook {

    public static final String MESSAGE_DUPLICATE_STOCK = "Stocks list contains duplicate stock(s).";

    private final List<JsonAdaptedStock> stocks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStockBook} with the given stocks.
     */
    @JsonCreator
    public JsonSerializableStockBook(@JsonProperty("stocks") List<JsonAdaptedStock> stocks) {
        this.stocks.addAll(stocks);
    }

    /**
     * Converts a given {@code ReadOnlyStockBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStockBook}.
     */
    public JsonSerializableStockBook(ReadOnlyStockBook source) {
        stocks.addAll(source.getStockList().stream().map(JsonAdaptedStock::new).collect(Collectors.toList()));
    }

    /**
     * Converts this stock book into the model's {@code StockBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StockBook toModelType() throws IllegalValueException {
        StockBook stockBook = new StockBook();
        for (JsonAdaptedStock jsonAdaptedStock : stocks) {
            Stock stock = jsonAdaptedStock.toModelType();
            if (stockBook.hasStock(stock)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STOCK);
            }
            stockBook.addStock(stock);
        }
        return stockBook;
    }

}
