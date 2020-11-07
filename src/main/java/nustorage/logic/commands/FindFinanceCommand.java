package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_AFTER_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_BEFORE_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_HAS_INVENTORY;
import static nustorage.logic.parser.CliSyntax.PREFIX_ID;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

import nustorage.commons.core.Messages;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * Finds and lists all finance records by ID, dates, or links to inventory records
 */
public class FindFinanceCommand extends Command {

    public static final String COMMAND_WORD = "find_finance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all finance records whose which ID contains "
            + "the specified ID string, or occurring before or after the given date times, "
            + "or those tagged / not tagged to items "
            + "and displays them as a list with index numbers.\n"
            + "Parameters:  "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_AFTER_DATETIME + "[DATE]] "
            + "[" + PREFIX_BEFORE_DATETIME + "[DATE]] "
            + "[" + PREFIX_HAS_INVENTORY + "[YES/NO]]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_ID + "123 "
            + PREFIX_AFTER_DATETIME + "2019-10-30 "
            + PREFIX_BEFORE_DATETIME + "2020-03-20 "
            + PREFIX_HAS_INVENTORY + "y";

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

    /**
     * Returns the predicate used to filter finance record, mainly for testing purposes
     *
     * @return Predicate used to filter finance record
     */
    public Predicate<FinanceRecord> getPredicate() {
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

        if (other == this) {
            return true; // short circuit if same object
        }

        if (other instanceof FindFinanceCommand) {
            FindFinanceCommand command = (FindFinanceCommand) other;

            return (this.idMatch.equals(command.idMatch)
                    && this.afterDatetime.equals(command.afterDatetime)
                    && this.beforeDatetime.equals(command.beforeDatetime)
                    && this.filterInventory.equals(command.filterInventory));
        }

        return false;
    }
}
