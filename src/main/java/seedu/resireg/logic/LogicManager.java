package seedu.resireg.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.logic.commands.Command;
import seedu.resireg.logic.commands.CommandResult;
import seedu.resireg.logic.commands.CommandWordEnum;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.logic.parser.Parser;
import seedu.resireg.logic.parser.ResiRegParser;
import seedu.resireg.logic.parser.exceptions.ParseException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";

    private static final Map<String, Parser<Command>> commandWordToParserMap = Arrays.stream(CommandWordEnum.values())
            .collect(Collectors.toMap(CommandWordEnum::getCommandWord, CommandWordEnum::getCommandParser));

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CommandHistory history;
    private boolean isModified;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.history = new CommandHistory();

        model.getResiReg().addListener(observable -> isModified = true);
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        isModified = false;

        CommandResult commandResult;
        Map<String, Parser<Command>> map = new HashMap<>(commandWordToParserMap);
        addAliases(map, model.getCommandWordAliases());
        try {
            Command command = ResiRegParser.parseCommand(commandText, map);
            commandResult = command.execute(model, storage, history);
        } finally {
            history.add(commandText);
        }

        if (isModified) {
            logger.info("Modification present. Saving to file.");
            try {
                storage.saveResiReg(model.getResiReg());
                storage.saveUserPrefs(model.getUserPrefs());
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }

        return commandResult;
    }

    private void addAliases(Map<String, Parser<Command>> map, List<CommandWordAlias> aliasList) {
        for (CommandWordAlias alias : aliasList) {
            String commandWord = alias.getCommandWord().commandWord;
            String aliasString = alias.getAlias().toString();
            if (map.containsKey(commandWord)) {
                map.put(aliasString, map.get(commandWord));
            }
        }
    }

    @Override
    public ReadOnlyResiReg getResiReg() {
        return model.getResiReg();
    }

    @Override
    public Semester getSemester() {
        return model.getSemester();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return model.getFilteredRoomList();
    }

    @Override
    public ObservableList<Allocation> getFilteredAllocationList() {
        return model.getFilteredAllocationList();
    }

    @Override
    public ObservableList<BinItem> getFilteredBinItemList() {
        return model.getFilteredBinItemList();
    }

    @Override
    public Path getResiRegFilePath() {
        return model.getResiRegFilePath();
    }

    @Override
    public ObservableList<String> getHistory() {
        return history.getHistory();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
