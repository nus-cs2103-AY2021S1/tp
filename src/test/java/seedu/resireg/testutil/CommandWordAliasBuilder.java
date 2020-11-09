package seedu.resireg.testutil;

import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * A utility class to help with building Student objects.
 */
public class CommandWordAliasBuilder {

    public static final String DEFAULT_COMMAND_WORD = "rooms";
    public static final String DEFAULT_ALIAS = "r";

    private CommandWord commandWord;
    private Alias alias;


    /**
     * Creates a {@code RoomBuilder} with the default details.
     */
    public CommandWordAliasBuilder() {
        commandWord = new CommandWord(DEFAULT_COMMAND_WORD);
        alias = new Alias(DEFAULT_ALIAS);
    }

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public CommandWordAliasBuilder(CommandWordAlias commandWordAliasToCopy) {
        commandWord = commandWordAliasToCopy.getCommandWord();
        alias = commandWordAliasToCopy.getAlias();
    }

    /**
     * Sets the {@code Floor} of the {@code Room} that we are building.
     */
    public CommandWordAliasBuilder withCommandWord(String commandWord) {
        this.commandWord = new CommandWord(commandWord);
        return this;
    }

    /**
     * Sets the {@code RoomType} of the {@code Student} that we are building.
     */
    public CommandWordAliasBuilder withAlias(String alias) {
        this.alias = new Alias(alias);
        return this;
    }


    public CommandWordAlias build() {
        return new CommandWordAlias(commandWord, alias);
    }

}
