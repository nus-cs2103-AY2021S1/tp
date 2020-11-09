package seedu.resireg.model.alias;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a CommandAlias in ResiReg.
 * Guarantees: immutable.
 */
public class CommandWordAlias {

    public static final String MESSAGE_CONSTRAINTS = "This alias is invalid as the command word doesn't exist";

    public final CommandWord commandWord;
    public final Alias alias;

    /**
     * Every field must be present and not null.
     */
    public CommandWordAlias(CommandWord commandWord, Alias alias) {
        requireAllNonNull(commandWord, alias);
        this.commandWord = commandWord;
        this.alias = alias;
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public Alias getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CommandWordAlias // instanceof handles nulls
            && alias.equals(((CommandWordAlias) other).alias) // state check
            && commandWord.equals(((CommandWordAlias) other).commandWord));
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandWord, alias);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '\"' + commandWord.toString() + '\"' + " = " + alias.toString();
    }

}
