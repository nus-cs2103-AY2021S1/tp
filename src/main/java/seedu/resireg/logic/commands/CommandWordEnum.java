package seedu.resireg.logic.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.resireg.logic.parser.AddAliasCommandParser;
import seedu.resireg.logic.parser.AddCommandParser;
import seedu.resireg.logic.parser.AddRoomCommandParser;
import seedu.resireg.logic.parser.AllocateCommandParser;
import seedu.resireg.logic.parser.DeallocateCommandParser;
import seedu.resireg.logic.parser.DeleteAliasCommandParser;
import seedu.resireg.logic.parser.DeleteCommandParser;
import seedu.resireg.logic.parser.DeleteRoomCommandParser;
import seedu.resireg.logic.parser.EditCommandParser;
import seedu.resireg.logic.parser.FindCommandParser;
import seedu.resireg.logic.parser.ListRoomCommandParser;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ReallocateCommandParser;
import seedu.resireg.logic.parser.RestoreCommandParser;
import seedu.resireg.logic.parser.SetBinExpiryCommandParser;
import seedu.resireg.logic.parser.exceptions.EditRoomCommandParser;
import seedu.resireg.model.AppMode;

/**
 * Enums representing all the commands in ResiReg. Each enum is bound to its command word, help, parser and a
 * list of AppModes that the command is available in. This enum is the single source of truth about all commands
 * in ResiReg, so developers who want to add new commands must add an enum to this class.
 */
public enum CommandWordEnum {
    // ================== All modes =============================
    // basics
    EXIT_COMMAND(ExitCommand.COMMAND_WORD, ExitCommand.HELP, args -> new ExitCommand(), AppMode.values()),
    HELP_COMMAND(HelpCommand.COMMAND_WORD, HelpCommand.HELP, HelpCommand::new, AppMode.values()),

    REDO_COMMAND(RedoCommand.COMMAND_WORD, RedoCommand.HELP, args -> new RedoCommand(), AppMode.values()),
    UNDO_COMMAND(UndoCommand.COMMAND_WORD, UndoCommand.HELP, args -> new UndoCommand(), AppMode.values()),

    ADD_ALIAS_COMMAND(AddAliasCommand.COMMAND_WORD, AddAliasCommand.HELP, new AddAliasCommandParser()::parse,
            AppMode.values()),
    DELETE_ALIAS_COMMAND(DeleteAliasCommand.COMMAND_WORD, DeleteAliasCommand.HELP,
            new DeleteAliasCommandParser()::parse, AppMode.values()),
    LIST_ALIAS_COMMAND(ListAliasCommand.COMMAND_WORD, ListAliasCommand.HELP, args -> new ListAliasCommand(),
            AppMode.values()),
    // rooms
    LIST_ROOM_COMMAND(ListRoomCommand.COMMAND_WORD, ListRoomCommand.HELP, new ListRoomCommandParser()::parse,
            AppMode.NEW, AppMode.NORMAL),
    EDIT_ROOM_COMMAND(EditRoomCommand.COMMAND_WORD, EditRoomCommand.HELP, new EditRoomCommandParser()::parse,
            AppMode.NEW, AppMode.NORMAL),

    // ================== Normal mode =============================
    // misc
    TOGGLE_TAB_SPLIT_COMMAND(ToggleTabSplitCommand.COMMAND_WORD, ToggleTabSplitCommand.HELP,
        args -> new ToggleTabSplitCommand(), AppMode.values()),
    HISTORY_COMMAND(HistoryCommand.COMMAND_WORD, HistoryCommand.HELP, args -> new HistoryCommand()),
    CLEAR_COMMAND(ClearCommand.COMMAND_WORD, ClearCommand.HELP, args -> new ClearCommand()),
    // bin
    RESTORE_COMMAND(RestoreCommand.COMMAND_WORD, RestoreCommand.HELP, new RestoreCommandParser()::parse),
    LIST_BIN_COMMAND(ListBinCommand.COMMAND_WORD, ListBinCommand.HELP, args -> new ListBinCommand()),
    SET_BIN_EXPIRY_COMMAND(SetBinExpiryCommand.COMMAND_WORD, SetBinExpiryCommand.HELP,
            new SetBinExpiryCommandParser()::parse),
    // students
    ADD_COMMAND(AddCommand.COMMAND_WORD, AddCommand.HELP, new AddCommandParser()::parse),
    DELETE_COMMAND(DeleteCommand.COMMAND_WORD, DeleteCommand.HELP, new DeleteCommandParser()::parse),
    EDIT_COMMAND(EditCommand.COMMAND_WORD, EditCommand.HELP, new EditCommandParser()::parse),
    FIND_COMMAND(FindCommand.COMMAND_WORD, FindCommand.HELP, new FindCommandParser()::parse),
    LIST_COMMAND(ListCommand.COMMAND_WORD, ListCommand.HELP, args -> new ListCommand()),
    // allocations
    ALLOCATE_COMMAND(AllocateCommand.COMMAND_WORD, AllocateCommand.HELP, new AllocateCommandParser()::parse),
    DEALLOCATE_COMMAND(DeallocateCommand.COMMAND_WORD, DeallocateCommand.HELP, new DeallocateCommandParser()::parse),
    REALLOCATE_COMMAND(ReallocateCommand.COMMAND_WORD, ReallocateCommand.HELP, new ReallocateCommandParser()::parse),
    // archiving
    ARCHIVE_COMMAND(ArchiveCommand.COMMAND_WORD, ArchiveCommand.HELP, unused -> new ArchiveCommand()),

    // ================== New mode =============================
    ADD_ROOM_COMMAND(AddRoomCommand.COMMAND_WORD, AddRoomCommand.HELP, new AddRoomCommandParser()::parse, AppMode.NEW),
    DELETE_ROOM_COMMAND(DeleteRoomCommand.COMMAND_WORD, DeleteRoomCommand.HELP, new DeleteRoomCommandParser()::parse,
            AppMode.NEW),
    FINALIZE_ROOMS_COMMAND(FinalizeRoomsCommand.COMMAND_WORD, FinalizeRoomsCommand.HELP,
        args -> new FinalizeRoomsCommand(), AppMode.NEW);

    // cache the maps from the helper functions
    private static Map<AppMode, Map<String, CommandWordEnum>> appModeToEnumMap = new HashMap<>();
    private static Map<AppMode, Map<String, Parser<Command>>> appModeToParserMap = new HashMap<>();

    private String commandWord;
    private Parser<Command> commandParser;
    private Help help;
    private List<AppMode> appModes;

    CommandWordEnum(String commandWord, Help help, Parser<Command> commandParser, AppMode ... appModes) {
        this.commandWord = commandWord;
        this.help = help;
        this.commandParser = commandParser;
        this.appModes = Arrays.asList(appModes);
    }

    CommandWordEnum(String commandWord, Help help, Parser<Command> commandParser) {
        this(commandWord, help, commandParser, AppMode.NORMAL);
    }

    // static helpers
    public static Map<String, CommandWordEnum> getCommandWordMap(AppMode appMode) {
        if (!(appModeToEnumMap.containsKey(appMode))) {
            Map<String, CommandWordEnum> map = Arrays.stream(values())
                    .filter(cwEnum -> cwEnum.appModes.contains(appMode))
                    .collect(Collectors.toMap(CommandWordEnum::getCommandWord, cwEnum -> cwEnum));
            appModeToEnumMap.put(appMode, map);
        }
        return appModeToEnumMap.get(appMode);
    }

    public static Map<String, Parser<Command>> getCommandWordToParserMap(AppMode appMode) {
        if (!(appModeToParserMap.containsKey(appMode))) {
            Map<String, Parser<Command>> map = Arrays.stream(values())
                    .filter(cwEnum -> cwEnum.appModes.contains(appMode))
                    .collect(Collectors.toMap(CommandWordEnum::getCommandWord, CommandWordEnum::getCommandParser));
            appModeToParserMap.put(appMode, map);
        }
        return appModeToParserMap.get(appMode);
    }

    public Parser<Command> getCommandParser() {
        return commandParser;
    }

    public Help getHelp() {
        return help;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String toString() {
        return commandWord;
    }

    public List<AppMode> getAppModes() {
        return Collections.unmodifiableList(appModes);
    }


}
