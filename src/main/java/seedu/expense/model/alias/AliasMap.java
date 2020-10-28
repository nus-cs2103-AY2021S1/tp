package seedu.expense.model.alias;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.expense.logic.commands.AddCategoryCommand;
import seedu.expense.logic.commands.AddCommand;
import seedu.expense.logic.commands.AliasCommand;
import seedu.expense.logic.commands.ClearCommand;
import seedu.expense.logic.commands.DeleteCategoryCommand;
import seedu.expense.logic.commands.DeleteCommand;
import seedu.expense.logic.commands.EditCommand;
import seedu.expense.logic.commands.ExitCommand;
import seedu.expense.logic.commands.FindCommand;
import seedu.expense.logic.commands.HelpCommand;
import seedu.expense.logic.commands.ListCommand;
import seedu.expense.logic.commands.RemarkCommand;
import seedu.expense.logic.commands.SwitchCommand;
import seedu.expense.logic.commands.TopupCommand;

/**
 * Wraps all data at the expense-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 */
public class AliasMap {

    public static final String MESSAGE_RESERVED = "The [%s] keyword is reserved.";
    public static final String DUPLICATE_KEYWORD_FOUND = "The [%s] keyword already exists.";
    public static final String UNCHANGED_ALIAS = "Previous and updated alias must not be the same.";
    public static final String ALIAS_NOT_FOUND = "The [%s] alias cannot be found.";
    private static final Set<String> RESERVED_KEYWORDS;

    private final HashMap<String, String> aliasMap;

    static {
        RESERVED_KEYWORDS =
                Set.of(
                        AddCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
                        EditCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
                        HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD, RemarkCommand.COMMAND_WORD,
                        TopupCommand.COMMAND_WORD, AliasCommand.COMMAND_WORD, AddCategoryCommand.COMMAND_WORD,
                        DeleteCategoryCommand.COMMAND_WORD, SwitchCommand.COMMAND_WORD
                );
    }

    /**
     * Constructs a new {@code AliasMap}.
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

    //// list overwrite operations

    /**
     * Replaces the contents of the alias list with {@code aliases}.
     * {@code aliases} must not contain duplicate expenses.
     */
    public void setAliases(List<AliasEntry> aliases) {
        for (AliasEntry e: aliases) {
            this.aliasMap.put(e.getKey(), e.getValue());
        }
    }

    /**
     * Resets the existing data of this {@code AliasMap} with {@code newData}.
     */
    public void resetData(AliasMap newData) {
        requireNonNull(newData);

        setAliases(newData.getAliasList());
    }

    //// alias-level operations

    /**
     * Returns true if an AliasEntry with the same identity as {@code aliasEntry} exists in the alias map.
     */
    public boolean hasAlias(AliasEntry aliasEntry) {
        requireNonNull(aliasEntry);
        return this.aliasMap.containsKey(aliasEntry.getKey());
    }

    /**
     * Returns true if a String with the same identity as {@code aliasString} exists in the alias map.
     */
    public boolean hasAlias(String aliasString) {
        requireNonNull(aliasString);
        return this.aliasMap.containsKey(aliasString);
    }

    public String getValue(String aliasString) throws IllegalArgumentException {
        requireNonNull(aliasString);
        if (!this.aliasMap.containsKey(aliasString) && !RESERVED_KEYWORDS.contains(aliasString)) {
            throw new IllegalArgumentException(String.format(ALIAS_NOT_FOUND, aliasString));
        }
        if (!this.aliasMap.containsKey(aliasString) && RESERVED_KEYWORDS.contains(aliasString)) {
            return aliasString;
        }
        return this.aliasMap.get(aliasString);
    }

    /**
     * Adds an AliasEntry to the alias map.
     * The entry must not already exist in the alias map.
     */
    public void addAlias(AliasEntry aliasEntry) {
        aliasMap.put(aliasEntry.getKey(), aliasEntry.getValue());
    }

    /**
     * Replaces the given etnry {@code prev} in the list with {@code update}.
     * {@code prev} must exist in the expense book.
     * The alias of {@code update} must not be the same as another existing
     * alias in the alias map.
     */
    public void setAlias(AliasEntry prev, AliasEntry update) throws IllegalArgumentException {
        requireNonNull(prev);
        requireNonNull(update);
        assert (prev.getValue().equals(update.getValue())) : "Must replace the same value (command) alias";
        if (!this.aliasMap.containsKey(prev.getKey()) && RESERVED_KEYWORDS.contains(prev.getKey())) {
            addAlias(update);
            return;
        }
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
     * Removes {@code alias} from this {@code AliasMap}.
     * {@code alias} must exist in the expense book.
     */
    public void removeAlias(AliasEntry alias) {
        aliasMap.remove(alias.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AliasMap)) {
            return false;
        }
        return this.aliasMap.equals(((AliasMap) o).aliasMap);
    }
}
