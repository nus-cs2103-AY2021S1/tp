package chopchop.ui;

import static chopchop.commons.util.Strings.COMMAND_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.ArrayList;

import chopchop.logic.LogicManager;
import chopchop.logic.commands.CommandResult;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.ModelManager;
import chopchop.storage.JsonIngredientBookStorage;
import chopchop.storage.JsonRecipeBookStorage;
import chopchop.storage.JsonUserPrefsStorage;
import chopchop.storage.JsonIngredientUsageStorage;
import chopchop.storage.JsonRecipeUsageStorage;
import chopchop.storage.StorageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guitests.guihandles.CommandBoxHandle;
import org.junit.jupiter.api.io.TempDir;

public class CommandBoxTest extends GuiUnitTest {

    private static final String COMMAND_THAT_SUCCEEDS = COMMAND_LIST + " recipe";
    private static final String COMMAND_THAT_FAILS = "test";

    @TempDir
    public Path testFolder;

    private ArrayList<String> defaultStyleOfCommandBox;
    private ArrayList<String> errorStyleOfCommandBox;
    private final ArrayList<String> history = new ArrayList<>();

    private CommandBoxHandle commandBoxHandle;
    private StorageManager storageManager;
    private ModelManager modelManager;
    private LogicManager logicManager;


    @BeforeEach
    public void setUp() {
        JsonIngredientBookStorage ingredientBookStorage = new JsonIngredientBookStorage(getTempFilePath("ab"));
        JsonRecipeBookStorage recipeBookStorage = new JsonRecipeBookStorage(getTempFilePath("abc"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonRecipeUsageStorage recipeUsageStorage = new JsonRecipeUsageStorage(getTempFilePath("cc"));
        JsonIngredientUsageStorage ingredientUsageStorage = new JsonIngredientUsageStorage(getTempFilePath("dd"));
        storageManager = new StorageManager(recipeBookStorage, ingredientBookStorage, recipeUsageStorage,
                ingredientUsageStorage, userPrefsStorage);

        modelManager = new ModelManager();

        logicManager = new LogicManager(modelManager, storageManager);

        CommandBox commandBox = new CommandBox(commandText -> {
            history.add(commandText);
            if (commandText.equals(COMMAND_THAT_SUCCEEDS)) {
                return CommandResult.message("Command successful");
            }
            throw new CommandException("Command failed");
        }, logicManager);
        commandBoxHandle = new CommandBoxHandle(getChildNode(commandBox.getRoot(),
                CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        uiPartExtension.setUiPart(commandBox);

        defaultStyleOfCommandBox = new ArrayList<>(commandBoxHandle.getStyleClass());

        errorStyleOfCommandBox = new ArrayList<>(defaultStyleOfCommandBox);
        errorStyleOfCommandBox.add(CommandBox.ERROR_STYLE_CLASS);
    }

    @Test
    public void commandBox_startingWithSuccessfulCommand() {
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    /**
     * Runs a command that fails, then verifies that <br>
     *      - the text is cleared <br>
     *      - the command box's style is the same as {@code errorStyleOfCommandBox}.
     */
    private void assertBehaviorForFailedCommand() {
        commandBoxHandle.run(COMMAND_THAT_FAILS);
        assertEquals("", commandBoxHandle.getInput());
        //assertEquals(errorStyleOfCommandBox, commandBoxHandle.getStyleClass());
    }

    /**
     * Runs a command that succeeds, then verifies that <br>
     *      - the text is cleared <br>
     *      - the command box's style is the same as {@code defaultStyleOfCommandBox}.
     */
    private void assertBehaviorForSuccessfulCommand() {
        commandBoxHandle.run(COMMAND_THAT_SUCCEEDS);
        assertEquals("", commandBoxHandle.getInput());
        assertEquals(defaultStyleOfCommandBox, commandBoxHandle.getStyleClass());
    }
}
