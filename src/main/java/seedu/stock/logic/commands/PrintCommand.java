package seedu.stock.logic.commands;

import javafx.collections.ObservableList;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * Copies all stocks in the inventory into a csv file.
 */
public class PrintCommand extends Command {
    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_SUCCESS = "CSV file successfully made.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies all stocks in the inventory into a csv file.\n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final char CSV_SEPARATOR = ',';


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Stock> stockBookList = model.getStockBook().getStockList();
        Path csvFilePath = model.getUserPrefs().getCsvFilePath();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath.toString()));
            writer.append(makeFileCreationTime());
            writer.append(makeTitleHeader());
            stockBookList.forEach(stock -> {
                try {
                    writer.append(printStock(stock));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Converts the stock into csv format.
     *
     * @param stock Stock to be converted into csv format.
     * @return String of the given stock in the csv format.
     */
    public String printStock(Stock stock) {
        return new StringBuilder()
                .append(stock.getSerialNumber()).append(CSV_SEPARATOR)
                .append(stock.getName()).append(CSV_SEPARATOR)
                .append(stock.getSource()).append(CSV_SEPARATOR)
                .append(stock.getQuantity()).append(CSV_SEPARATOR)
                .append(stock.getLocation()).append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Sets up the headers of the csv file.
     *
     * @return String header in the csv format.
     */
    public String makeTitleHeader() {
        return new StringBuilder()
                .append("Serial Number").append(CSV_SEPARATOR)
                .append("Name").append(CSV_SEPARATOR)
                .append("Source of stock").append(CSV_SEPARATOR)
                .append("Quantity").append(CSV_SEPARATOR)
                .append("Location in warehouse").append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Gets the timing which the csv file is created in csv format.
     *
     * @return String of the given stock in the csv format.
     */
    public String makeFileCreationTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy 'at' HH:mm.");
        Date date = new Date(System.currentTimeMillis());

        return new StringBuilder()
                .append("Stock list updated as of: ").append(formatter.format(date))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString();
    }
}
