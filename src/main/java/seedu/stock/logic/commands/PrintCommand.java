package seedu.stock.logic.commands;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME_DESCRIPTION;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.util.FileUtil;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

/**
 * Copies all stocks in the inventory into a csv file.
 */
public class PrintCommand extends Command {
    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_SUCCESS = "CSV file successfully made.";

    public static final String MESSAGE_FAILURE = "Error occurred when generating the csv file. ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Copies all stocks in the inventory into a csv file.\n"
            + "Format: "
            + COMMAND_WORD + " "
            + PREFIX_FILE_NAME + PREFIX_FILE_NAME_DESCRIPTION + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_FILE_NAME + "stocks";

    public static final char CSV_SEPARATOR = ',';

    public static final String CSV_TAG = ".csv";

    private static final Logger logger = LogsCenter.getLogger(PrintCommand.class);

    private final String csvFileName;

    public PrintCommand(String fileName) {
        csvFileName = fileName + CSV_TAG;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null!";
        logger.log(Level.INFO, "Starting to execute print command");

        ObservableList<Stock> stockBookList = model.getStockBook().getStockList();
        Path csvFilePath = model.getUserPrefs().getCsvFilePath().resolve(csvFileName);

        try {
            FileUtil.createIfMissing(csvFilePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath.toString()));
            writer.append(makeFileCreationTime());
            writer.append(makeTitleHeader());
            for (Stock stock: stockBookList) {
                writer.append(printStock(stock));
            }
            writer.close();
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_FAILURE + ex.getMessage());
        }

        logger.log(Level.INFO, "Finished printing stocks successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Converts the stock into csv format.
     *
     * @param stock Stock to be converted into csv format.
     * @return String of the given stock in the csv format.
     */
    private String printStock(Stock stock) {
        return new StringBuilder()
                .append(commaFormatter(stock.getSerialNumber().toString())).append(CSV_SEPARATOR)
                .append(commaFormatter(stock.getName().toString())).append(CSV_SEPARATOR)
                .append(commaFormatter(stock.getSource().toString())).append(CSV_SEPARATOR)
                .append(stock.getQuantity().toString()).append(CSV_SEPARATOR)
                .append(stock.getQuantity().lowQuantity).append(CSV_SEPARATOR)
                .append(commaFormatter(stock.getLocation().toString())).append(CSV_SEPARATOR)
                .append(bookmarkToString(stock)).append(CSV_SEPARATOR)
                .append(commaFormatter(stock.notesToString(stock.getNotes()))).append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Sets up the headers of the csv file.
     *
     * @return String header in the csv format.
     */
    private String makeTitleHeader() {
        return new StringBuilder()
                .append("Serial Number").append(CSV_SEPARATOR)
                .append("Name").append(CSV_SEPARATOR)
                .append("Source of stock").append(CSV_SEPARATOR)
                .append("Quantity").append(CSV_SEPARATOR)
                .append("Low Quantity").append(CSV_SEPARATOR)
                .append("Location in warehouse").append(CSV_SEPARATOR)
                .append("Bookmark").append(CSV_SEPARATOR)
                .append("Notes").append(CSV_SEPARATOR)
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Gets the timing which the csv file is created in csv format.
     *
     * @return String that describes the creation time of file in csv format.
     */
    private String makeFileCreationTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy 'at' HH:mm.");
        Date date = new Date(System.currentTimeMillis());

        return new StringBuilder()
                .append("Stock list updated as of: ").append(formatter.format(date))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Formats the string to so that special characters in a column would not affect
     * the integrity of the csv file generated. The whole string in the column will remain in the column.
     *
     * @return String of the formatted input to be stored in a csv column.
     */
    private String commaFormatter(String string) {
        return String.format("\"%s\"", string);
    }

    /**
     * Converts bookmark in stock to a string value.
     *
     * @return String of whether the given stock is bookmarked.
     */
    private String bookmarkToString(Stock stock) {
        if (stock.getIsBookmarked()) {
            return "Yes";
        }
        return "No";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return other == this // short circuit if same object
                || (other instanceof PrintCommand // instanceof handles nulls
                && csvFileName.equals(((PrintCommand) other).csvFileName)); // state check
    }
}
