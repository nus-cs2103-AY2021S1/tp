package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_AFTER_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_BEFORE_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_ID;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

import nustorage.commons.core.Messages;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindFinanceCommand extends Command {

    public static final String COMMAND_WORD = "find_finance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all finance records whose which ID contains "
            + "the specified ID string, or occurring before or after the given date times, "
            + "and displays them as a list with index numbers.\n"
            + "Parameters:  "
            + "[" + PREFIX_ID + "ID]"
            + "[" + PREFIX_AFTER_DATETIME + "[DATE]]"
            + "[" + PREFIX_BEFORE_DATETIME + "[DATE]]\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private Optional<String> idMatch;
    private Optional<LocalDate> afterDatetime;
    private Optional<LocalDate> beforeDatetime;
    private Optional<Boolean> filterInventory;

    /**
     * Creates a command that searches for finance records
     */
    public FindFinanceCommand() {
        idMatch = Optional.empty();
        afterDatetime = Optional.empty();
        beforeDatetime = Optional.empty();
        filterInventory = Optional.empty();
    }

    /**
     * Sets the command to search for finance records with ID containing {@Code idtoMatch}
     *
     * @param idToMatch Sub-ID for finding finance records
     */
    public FindFinanceCommand setIdMatch(String idToMatch) {
        idMatch = Optional.of(idToMatch);
        return this;
    }

    /**
     * Sets the command to search for finance records which took place after {@Code date}
     *
     * @param date Date to search finance records from
     */
    public FindFinanceCommand setAfterDatetime(LocalDate date) {
        afterDatetime = Optional.of(date);
        return this;
    }

    /**
     * Sets the command to search for finance records which took place before {@Code date}
     *
     * @param date Date to search finance records until
     */
    public FindFinanceCommand setBeforeDatetime(LocalDate date) {
        beforeDatetime = Optional.of(date);
        return this;
    }

    /**
     * Sets the command to search for finance records with or without inventory
     *
     * @param hasInventory Filter with inventory if true, else filter without
     */
    public FindFinanceCommand setHasInventory(boolean hasInventory) {
        filterInventory = Optional.of(hasInventory);
        return this;
    }

    private Predicate<FinanceRecord> getPredicate() {
        return record -> {

            if (filterInventory.isPresent() && filterInventory.get() != record.taggedToInventory()) {
                return false;
            }

            if (idMatch.isPresent() && !(record.getID() + "").contains(idMatch.get())) {
                return false;
            }

            if (afterDatetime.isPresent() && record.getDateTime().toLocalDate().isBefore(afterDatetime.get())) {
                return false;
            }

            if (beforeDatetime.isPresent() && record.getDateTime().toLocalDate().isAfter(beforeDatetime.get())) {
                return false;
            }

            return true;
        };
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFinanceList(getPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_FINANCE_LISTED_OVERVIEW, model.getFilteredFinanceList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindFinanceCommand // instanceof handles nulls
                && this.getPredicate().equals(((FindFinanceCommand) other).getPredicate())); // state check
    }
}
