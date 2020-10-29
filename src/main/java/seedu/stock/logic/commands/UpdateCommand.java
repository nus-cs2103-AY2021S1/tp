package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.stock.commons.util.CollectionUtil;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * Updates an existing stock in the stock book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the stock with "
            + "the given serial number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_SERIAL_NUMBER + "SERIAL NUMBER "
            + PREFIX_INCREMENT_QUANTITY + "QUANTITY "
            + PREFIX_NEW_QUANTITY + "NEW QUANTITY "
            + PREFIX_NAME + "NAME "
            + PREFIX_SOURCE + "SOURCE "
            + PREFIX_LOCATION + "LOCATION \n"
            + "Note that only one of " + PREFIX_INCREMENT_QUANTITY
            + "and " + PREFIX_NEW_QUANTITY
            + "can be specified. \n"
            + "Note that quantities should only have values from 0"
            + "and 2,147,483,647\n"
            + "You may provide more than one serial number \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SERIAL_NUMBER + "CS2103 "
            + PREFIX_INCREMENT_QUANTITY + "2103 "
            + PREFIX_NAME + "CS2103 "
            + PREFIX_SOURCE + "National University of Singapore "
            + PREFIX_LOCATION + "Group 3 ";

    public static final String MESSAGE_UPDATE_STOCK_SUCCESS = "Updated Stock: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_STOCK = "This stock already exists in the stock book.";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND = "Stock with given serial number does not exists";
    public static final String MESSAGE_TOO_MANY_QUANTITY_PREFIXES = "You can only use one of the prefix iq/ or nq/";

    private final UpdateStockDescriptor updateStockDescriptor;

    /**
     * Constructs a new update command.
     *
     * @param updateStockDescriptor Details to be updated.
     */
    public UpdateCommand(UpdateStockDescriptor updateStockDescriptor) {
        requireNonNull(updateStockDescriptor);

        this.updateStockDescriptor = updateStockDescriptor;
    }

    @Override
    public String toString() {
        return "UpdateCommand:\n" + updateStockDescriptor;
    }

    /**
     * Executes the update command and returns the result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return The result of successful execution.
     * @throws CommandException If there are any errors.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStockList(Model.PREDICATE_SHOW_ALL_STOCKS);
        List<Stock> lastShownStocks = model.getFilteredStockList();

        List<SerialNumber> indexes = updateStockDescriptor.getSerialNumbers();
        List<String> serials = indexes.stream().map((serial) -> serial.getSerialNumberAsString().trim())
                .collect(Collectors.toCollection(ArrayList::new));
        List<Stock> stocksToUpdate = new ArrayList<>();
        List<Stock> updatedStocks = new ArrayList<>();

        // Find stocks to be updated
        for (Stock currentStock : lastShownStocks) {
            String currentStockSerialNumber = currentStock.getSerialNumber().getSerialNumberAsString();
            boolean anyMatches = false;

            for (String currentSerialNumber : serials) {
                if (currentSerialNumber.equals(currentStockSerialNumber)) {
                    anyMatches = true;
                }
            }

            if (anyMatches) {
                stocksToUpdate.add(currentStock);
            }
        }

        // Some serial numbers do not exist
        if (serials.size() != stocksToUpdate.size()) {
            throw new CommandException(MESSAGE_SERIAL_NUMBER_NOT_FOUND);
        }

        // Update stocks
        for (Stock stockToUpdate: stocksToUpdate) {
            Stock updatedStock = createUpdatedStock(stockToUpdate, updateStockDescriptor);

            if (!stockToUpdate.isSameStock(updatedStock) && model.hasStock(updatedStock)) {
                throw new CommandException(MESSAGE_DUPLICATE_STOCK);
            }

            model.setStock(stockToUpdate, updatedStock);
            updatedStocks.add(updatedStock);
        }

        return new CommandResult(String.format(MESSAGE_UPDATE_STOCK_SUCCESS, stocksAsString(updatedStocks)));
    }

    /**
     * Displays the list of stocks in a clearer view, with each subsequent stock moved to the next line.
     *
     * @param stockList The list of stocks to be converted to String.
     * @return The String listing all stocks in the list.
     */
    private String stocksAsString(List<Stock> stockList) {
        String stocksAsString = "";
        for (int i = 0; i < stockList.size(); i++) {
            stocksAsString += "\n" + stockList.get(i).toString();
        }
        return stockList.size() == 0 ? "No stocks updated" : stocksAsString;
    }

    /**
     * Creates the stock with updated attributes.
     *
     * @param stockToUpdate The stock in the list to be updated.
     * @param updateStockDescriptor The collection of values to be updated.
     * @return The stock with updated attributes.
     */
    private static Stock createUpdatedStock(Stock stockToUpdate, UpdateStockDescriptor updateStockDescriptor)
            throws CommandException {
        assert stockToUpdate != null;

        Quantity updatedQuantity = updateStockDescriptor.getQuantity().orElse(stockToUpdate.getQuantity());
        Name updatedName = updateStockDescriptor.getName().orElse(stockToUpdate.getName());
        Source updatedSource = updateStockDescriptor.getSource().orElse(stockToUpdate.getSource());
        Location updatedLocation = updateStockDescriptor.getLocation().orElse(stockToUpdate.getLocation());
        SerialNumber stockSerialNumber = stockToUpdate.getSerialNumber();
        Optional<QuantityAdder> quantityAdder = updateStockDescriptor.getQuantityAdder();

        if (!quantityAdder.isEmpty()) {
            QuantityAdder valueToBeAdded = quantityAdder.get();
            Optional<Quantity> result = valueToBeAdded.incrementQuantity(updatedQuantity);
            updatedQuantity = result.orElseThrow(() -> new CommandException(Quantity.MESSAGE_CONSTRAINTS));
        }

        Stock result = new Stock(updatedName, stockSerialNumber, updatedSource, updatedQuantity, updatedLocation);

        if (stockToUpdate.getNotes().size() > 0) {
            List<Note> noteList = stockToUpdate.getNotes();

            for(Note note : noteList) {
                result.addNote(note);
            }
        }
        if (stockToUpdate.getIsBookmarked()) {
            result.setBookmarked();
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        // state check
        UpdateCommand castedOther = (UpdateCommand) other;
        return updateStockDescriptor.equals(((UpdateCommand) other).updateStockDescriptor);
    }

    public static class UpdateStockDescriptor {

        // Identity fields
        private Name name;
        private List<SerialNumber> serialNumbers;

        // Data fields
        private Source source;
        private Quantity quantity;
        private Location location;
        private QuantityAdder quantityAdder;

        public UpdateStockDescriptor() { }

        /**
         * Copy constructor.
         *
         * @param toCopy Object to be copied.
         */
        public UpdateStockDescriptor(UpdateStockDescriptor toCopy) {
            setName(toCopy.name);
            setSerialNumbers(toCopy.serialNumbers);
            setSource(toCopy.source);
            setQuantity(toCopy.quantity);
            setLocation(toCopy.location);
            setQuantityAdder(toCopy.quantityAdder);
        }

        /**
         * Checks if any updates exists.
         *
         * @return A boolean value indicating if an update exists.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, serialNumbers, source, quantity, location);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSerialNumbers(List<SerialNumber> serialNumbers) {
            this.serialNumbers = serialNumbers;
        }

        public List<SerialNumber> getSerialNumbers() {
            assert serialNumbers != null;
            return serialNumbers;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Optional<Source> getSource() {
            return Optional.ofNullable(source);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setQuantityAdder(QuantityAdder quantityAdder) {
            this.quantityAdder = quantityAdder;
        }

        public Optional<QuantityAdder> getQuantityAdder() {
            return Optional.ofNullable(quantityAdder);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        @Override
        public String toString() {
            return "UpdateStockDescriptor:\n"
                    + name + "\n"
                    + serialNumbers.toString() + "\n"
                    + source + "\n"
                    + quantity + "\n"
                    + location + "\n"
                    + quantityAdder;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateStockDescriptor)) {
                return false;
            }

            // state check
            UpdateStockDescriptor castedOther = (UpdateStockDescriptor) other;

            return getName().equals(castedOther.getName())
                    && getSerialNumbers().equals(castedOther.getSerialNumbers())
                    && getSource().equals(castedOther.getSource())
                    && getQuantity().equals(castedOther.getQuantity())
                    && getLocation().equals(castedOther.getLocation())
                    && getQuantityAdder().equals(castedOther.getQuantityAdder());
        }
    }
}
