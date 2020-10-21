package seedu.expense.model.alias;

import seedu.expense.logic.commands.*;

import java.util.*;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the expense-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 */
public class AliasMap {

    public static final String MESSAGE_RESERVED = "The [%s] keyword is reserved.";
    public static final String DUPLICATE_KEYWORD_FOUND = "The [%s] keyword already exists.";
    public static final String UNCHANGED_ALIAS = "Previous and updated alias must not be the same.";
    public static final String ALIAS_NOT_FOUND = "The [%s] alias cannot be found.";

    private final HashMap<String, String> aliasMap;
    private static final Set<String> RESERVED_KEYWORDS;
    static {
        RESERVED_KEYWORDS =
                Set.of(
                        AddCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
                        EditCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
                        HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD, RemarkCommand.COMMAND_WORD,
                        TopupCommand.COMMAND_WORD
                );
    }

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

    public boolean hasAlias(String aliasString) {
        requireNonNull(aliasString);
        return this.aliasMap.containsKey(aliasString);
    }

    public String getValue(String aliasString) throws IllegalArgumentException {
        requireNonNull(aliasString);
        if (!this.aliasMap.containsKey(aliasString)) {
            throw new IllegalArgumentException(String.format(ALIAS_NOT_FOUND, aliasString));
        }
        return this.aliasMap.get(aliasString);
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
    public void setAlias(AliasEntry prev, AliasEntry update) throws IllegalArgumentException {
        requireNonNull(prev);
        requireNonNull(update);
        assert (prev.getValue().equals(update.getValue())) : "Must replace the same value (command) alias";
        if (!this.aliasMap.containsKey(prev.getKey())) {
            throw new IllegalArgumentException(String.format(ALIAS_NOT_FOUND, prev.getKey()));
        }
        if (prev.getKey().equals(update.getKey())) {
            throw new IllegalArgumentException(UNCHANGED_ALIAS);
        }
        if (RESERVED_KEYWORDS.contains(update.getKey()) && !prev.getValue().equals(update.getKey())) {
            throw new IllegalArgumentException(String.format(MESSAGE_RESERVED, update.getKey()));
        }
        if (this.aliasMap.containsKey(update.getKey())) {
            throw new IllegalArgumentException(String.format(DUPLICATE_KEYWORD_FOUND, update.getKey()));
        }
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

}
