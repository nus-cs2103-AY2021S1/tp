package seedu.resireg.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.resireg.logic.commands.AddAliasCommand;
import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.logic.commands.AllocateCommand;
import seedu.resireg.logic.commands.ArchiveCommand;
import seedu.resireg.logic.commands.ClearCommand;
import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.commands.DeallocateCommand;
import seedu.resireg.logic.commands.DeleteAliasCommand;
import seedu.resireg.logic.commands.DeleteCommand;
import seedu.resireg.logic.commands.EditCommand;
import seedu.resireg.logic.commands.ExitCommand;
import seedu.resireg.logic.commands.FindCommand;
import seedu.resireg.logic.commands.Help;
import seedu.resireg.logic.commands.HelpCommand;
import seedu.resireg.logic.commands.HistoryCommand;
import seedu.resireg.logic.commands.ListAliasCommand;
import seedu.resireg.logic.commands.ListBinCommand;
import seedu.resireg.logic.commands.ListCommand;
import seedu.resireg.logic.commands.ListRoomCommand;
import seedu.resireg.logic.commands.ReallocateCommand;
import seedu.resireg.logic.commands.RedoCommand;
import seedu.resireg.logic.commands.RestoreCommand;
import seedu.resireg.logic.commands.SetBinExpiryCommand;
import seedu.resireg.logic.commands.ToggleTabSplitCommand;
import seedu.resireg.logic.commands.UndoCommand;
import seedu.resireg.logic.parser.AddAliasCommandParser;
import seedu.resireg.logic.parser.AddCommandParser;
import seedu.resireg.logic.parser.AllocateCommandParser;
import seedu.resireg.logic.parser.DeallocateCommandParser;
import seedu.resireg.logic.parser.DeleteAliasCommandParser;
import seedu.resireg.logic.parser.DeleteCommandParser;
import seedu.resireg.logic.parser.EditCommandParser;
import seedu.resireg.logic.parser.FindCommandParser;
import seedu.resireg.logic.parser.ListRoomCommandParser;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ReallocateCommandParser;
import seedu.resireg.logic.parser.ResiRegParser;
import seedu.resireg.logic.parser.RestoreCommandParser;
import seedu.resireg.logic.parser.SetBinExpiryCommandParser;
import seedu.resireg.model.AppMode;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * Manages the mapping between command words and parsers, and command words and their help messages.
 * <p>
 * Developers can add new commands by modifying the constructor for this class. This class ensures that all
 * commands (1) are bound to their parsing logic and (2) their documentation can be checked using the help command.
 */
public class CommandMapper {

    private CommandMap commandMap;

    /**
     * Creates a new CommandMapper with all the commands supported by the application bound appropriately.
     * Developers who want to add new commands need to modify this constructor.
     */
    public CommandMapper(AppMode appMode, List<CommandWordAlias> aliases) {
        commandMap = new CommandMap();
        // note: new Parser()::parse assumes that Parser does not depend on state

        switch (appMode) {
        case NORMAL:
            commandMap.addCommand(AddCommand.COMMAND_WORD, AddCommand.HELP, new AddCommandParser()::parse);
            commandMap.addCommand(ArchiveCommand.COMMAND_WORD, ArchiveCommand.HELP, unused -> new ArchiveCommand());
            commandMap.addCommand(ClearCommand.COMMAND_WORD, ClearCommand.HELP, unused -> new ClearCommand());
            commandMap.addCommand(DeleteCommand.COMMAND_WORD, DeleteCommand.HELP, new DeleteCommandParser()::parse);
            commandMap.addCommand(EditCommand.COMMAND_WORD, EditCommand.HELP, new EditCommandParser()::parse);
            commandMap.addCommand(ExitCommand.COMMAND_WORD, ExitCommand.HELP, unused -> new ExitCommand());
            commandMap.addCommand(FindCommand.COMMAND_WORD, FindCommand.HELP, new FindCommandParser()::parse);
            commandMap.addCommand(HelpCommand.COMMAND_WORD, HelpCommand.HELP, HelpCommand::new);
            commandMap.addCommand(ListCommand.COMMAND_WORD, ListCommand.HELP, unused -> new ListCommand());
            commandMap.addCommand(ListRoomCommand.COMMAND_WORD, ListRoomCommand.HELP, new ListRoomCommandParser()::parse);
            commandMap.addCommand(AllocateCommand.COMMAND_WORD, AllocateCommand.HELP, new AllocateCommandParser()::parse);
            commandMap.addCommand(DeallocateCommand.COMMAND_WORD, DeallocateCommand.HELP,
                    new DeallocateCommandParser()::parse);
            commandMap.addCommand(ReallocateCommand.COMMAND_WORD, ReallocateCommand.HELP,
                    new ReallocateCommandParser()::parse);
            commandMap.addCommand(RedoCommand.COMMAND_WORD, RedoCommand.HELP, unused -> new RedoCommand());
            commandMap.addCommand(UndoCommand.COMMAND_WORD, UndoCommand.HELP, unused -> new UndoCommand());
            commandMap.addCommand(AddAliasCommand.COMMAND_WORD, AddAliasCommand.HELP, new AddAliasCommandParser()::parse);
            commandMap.addCommand(DeleteAliasCommand.COMMAND_WORD, DeleteAliasCommand.HELP,
                    new DeleteAliasCommandParser()::parse);
            commandMap.addCommand(ListAliasCommand.COMMAND_WORD, ListAliasCommand.HELP, unused -> new ListAliasCommand());
            commandMap.addCommand(ToggleTabSplitCommand.COMMAND_WORD, ToggleTabSplitCommand.HELP,
                    unused -> new ToggleTabSplitCommand());
            commandMap.addCommand(HistoryCommand.COMMAND_WORD, HistoryCommand.HELP, unused -> new HistoryCommand());
            commandMap.addCommand(RestoreCommand.COMMAND_WORD, RestoreCommand.HELP,
                    new RestoreCommandParser()::parse);
            commandMap.addCommand(ListBinCommand.COMMAND_WORD, ListBinCommand.HELP, unused -> new ListBinCommand());
            commandMap.addCommand(SetBinExpiryCommand.COMMAND_WORD, SetBinExpiryCommand.HELP,
                    new SetBinExpiryCommandParser()::parse);
            break;
        case NEW:

            break;
        default:
            assert false: "Unhandled AppMode in CommandMapper!";
        }

        for (CommandWordAlias commandWordAlias : aliases) {
            commandMap.addAliasCommand(commandWordAlias.getAlias().toString(),
                commandWordAlias.getCommandWord().toString());
        }
    }

    public Map<String, Help> getCommandWordToHelpMap() {
        return commandMap.getCommandWordToHelpMap();
    }


    /**
     * Returns a parser which parses user inputs and returns the appropriate Command.
     *
     * @return Parser.
     */
    ResiRegParser getParser() {
        return new ResiRegParser(commandMap.getCommandWordToParserMap());
    }

    private static class CommandMap {
        private HashMap<String, Help> commandWordToHelp;
        private HashMap<String, Parser<Command>> commandWordToParser;

        private CommandMap() {
            commandWordToHelp = new HashMap<>();
            commandWordToParser = new HashMap<>();
        }

        void addCommand(String commandWord, Help help, Parser<Command> parser) {
            commandWordToHelp.put(commandWord, help);
            commandWordToParser.put(commandWord, parser);
        }

        void addAliasCommand(String alias, String commandWord) {
            // ignore the alias if it's not present
            if (commandWordToParser.containsKey(alias)) {
                commandWordToHelp.put(alias, commandWordToHelp.get(commandWord));
                commandWordToParser.put(alias, commandWordToParser.get(commandWord));
            }
        }

        Map<String, Help> getCommandWordToHelpMap() {
            return Collections.unmodifiableMap(commandWordToHelp);
        }

        Map<String, Parser<Command>> getCommandWordToParserMap() {
            return Collections.unmodifiableMap(commandWordToParser);
        }
    }
}



