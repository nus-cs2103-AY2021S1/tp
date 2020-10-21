package seedu.expense.model;

import seedu.expense.model.alias.AliasEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the expense-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 */
public class AliasMap {


    private final HashMap<String, String> aliasMap;
    /*
    private static final HashMap<String, String> classMap = new HashMap<>();
    private static final String dir = "seedu.expense.logic.commands.";
    static {
        classMap.put(AddCommand.COMMAND_WORD, "AddCommand");
        classMap.put(ClearCommand.COMMAND_WORD, "ClearCommand");
        classMap.put(DeleteCommand.COMMAND_WORD, "DeleteCommand");
        classMap.put(EditCommand.COMMAND_WORD, "EditCommand");
        classMap.put(ExitCommand.COMMAND_WORD, "ExitCommand");
        classMap.put(FindCommand.COMMAND_WORD, "FindCommand");
        classMap.put(HelpCommand.COMMAND_WORD, "AddCommand");
        classMap.put(ListCommand.COMMAND_WORD, "AddCommand");
        classMap.put(RemarkCommand.COMMAND_WORD, "AddCommand");
        classMap.put(TopupCommand.COMMAND_WORD, "AddCommand");
    }
     */

    public AliasMap() {
        aliasMap = new HashMap<>();
    }

    /**
     * Creates an AliasMap using the AliasMap in the {@code toBeCopied}
     */
    public AliasMap(AliasMap toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
    {
        expenses = new UniqueExpenseList();
    }
    */
    //// list overwrite operations

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setAliases(List<AliasEntry> aliases) {
        for (AliasEntry e: aliases) {
            this.aliasMap.put(e.getKey(), e.getValue());
        }
    }

    /**
     * Resets the existing data of this {@code ExpenseBook} with {@code newData}.
     */
    public void resetData(AliasMap newData) {
        requireNonNull(newData);

        setAliases(newData.getAliasList());
    }

    //// alias-level operations

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the expense book.
     */
    public boolean hasAlias(AliasEntry aliasEntry) {
        requireNonNull(aliasEntry);
        return this.aliasMap.containsKey(aliasEntry.getKey());
    }

    /**
     * Adds a expense to the expense book.
     * The expense must not already exist in the expense book.
     */
    public void addAlias(AliasEntry aliasEntry) {
        aliasMap.put(aliasEntry.getKey(), aliasEntry.getValue());
    }

    /**
     * Replaces the given expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the expense book.
     * The expense identity of {@code editedExpense} must not be the same as another existing
     * expense in the expense book.
     */
    public void setAlias(AliasEntry prev, AliasEntry update) {
        requireNonNull(prev);
        requireNonNull(update);
        assert prev.getValue().equals(update.getValue()) : "The two keywords do not update same command";

        this.aliasMap.remove(prev.getKey());
        addAlias(update);
    }

    public List<AliasEntry> getAliasList() {
        List<AliasEntry> aliases = new ArrayList<>();
        for (Map.Entry<String, String> e: this.aliasMap.entrySet()) {
            aliases.add(new AliasEntry(e.getKey(), e.getValue()));
        }
        return aliases;
    }

    /**
     * Removes {@code key} from this {@code ExpenseBook}.
     * {@code key} must exist in the expense book.
     */
    public void removeAlias(AliasEntry alias) {
        aliasMap.remove(alias.getKey());
    }

    /*
    //// util methods

    @Override
    public String toString() {
        return expenses.asUnmodifiableObservableList().size() + " expenses";
        // TODO: refine later
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseBook // instanceof handles nulls
                && expenses.equals(((ExpenseBook) other).expenses));
    }

    @Override
    public int hashCode() {
        return expenses.hashCode();
    }
     */
}
