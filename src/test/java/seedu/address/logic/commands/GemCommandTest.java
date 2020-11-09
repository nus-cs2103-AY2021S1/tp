package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.commons.core.Messages.LINE;
import static seedu.address.commons.core.Messages.MESSAGE_GEM_SUCCESS;
import static seedu.address.logic.commands.RequiredCommandTest.MISSING_MODULE_1;
import static seedu.address.logic.commands.RequiredCommandTest.SINGLE_MODULE_PATH;
import static seedu.address.storage.GemCommandPaths.GEH_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GEQ_PATH;
import static seedu.address.storage.GemCommandPaths.GER_PATH;
import static seedu.address.storage.GemCommandPaths.GES_SEM1_PATH;
import static seedu.address.storage.GemCommandPaths.GET_SEM1_PATH;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;
import seedu.address.storage.GemCommandStorage;
import seedu.address.storage.JsonGradPadStorage;

public class GemCommandTest {

    public static final Path GEH_PATH_1 = Paths.get("src/main/resources/data/GEM/GEHsem1.json");
    public static final Path GET_PATH_1 = Paths.get("src/main/resources/data/GEM/GETsem1.json");
    public static final Path GES_PATH_1 = Paths.get("src/main/resources/data/GEM/GESsem1.json");

    public static final Path GEH_PATH_2 = Paths.get("src/main/resources/data/GEM/GEHsem2.json");
    public static final Path GET_PATH_2 = Paths.get("src/main/resources/data/GEM/GETsem2.json");
    public static final Path GES_PATH_2 = Paths.get("src/main/resources/data/GEM/GESsem2.json");

    public static final Path GEQ_PATH_1 = Paths.get("src/main/resources/data/GEM/GEQ.json");
    public static final Path GER_PATH_1 = Paths.get("src/main/resources/data/GEM/GER.json");

    public static final Path TEST_GEH_SEM1_PATH = Paths.get("src/main/resources/data/GEM/GEHsem1.json");
    public static final Path TEST_GEH_SEM2_PATH = Paths.get("src/main/resources/data/GEM/GEHsem2.json");

    private Model model;
    private GemCommand gemCommand = new GemCommand();
    private GemCommandStorage storage = new GemCommandStorage();
    private ObservableList<Module> testModules;

    public void setUpTestModules(Path path) throws IOException, DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(path);
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        testModules = gradPad.getModuleList();
    }

    @Test
    public void getSem1Storage_validTest() {
        GemCommandStorage actual = gemCommand.getSem1Storage();
        assertNull(actual);
    }
    @Test
    public void getSem2Storage_validTest() {
        GemCommandStorage actual = gemCommand.getSem2Storage();
        assertNull(actual);
    }
    @Test
    public void setSem1Storage_validTest() throws IOException, DataConversionException, IllegalValueException {
        setUpTestModules(TEST_GEH_SEM1_PATH);
        gemCommand.setSem1Storage();
        GemCommandStorage storage = gemCommand.getSem1Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }
    @Test
    public void setSem2Storage_validTest() throws IOException, DataConversionException, IllegalValueException {
        setUpTestModules(TEST_GEH_SEM2_PATH);
        gemCommand.setSem2Storage();
        GemCommandStorage storage = gemCommand.getSem2Storage();
        ObservableList<Module> actual = storage.getGehModules();
        assertEquals(testModules, actual);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gemCommand.execute(model));
    }
    @Test
    public void execute_validTest() throws IOException, DataConversionException {
        model = new ModelManager();

        String expectedMessage = MESSAGE_GEM_SUCCESS + "\n\n" + "Semester 1:";
        setUpTestModules(GEH_PATH_1);
        expectedMessage += "\n\nHuman Cultures\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_1);
        expectedMessage += "\n\nThinking and Expression\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_1);
        expectedMessage += "\n\nSingapore Studies\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expectedMessage += "\n\nAsking Questions\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expectedMessage += "\n\nQuantitative Reasoning\n" + gemCommand.moduleExtractor(testModules, model);

        expectedMessage += "\n\n" + LINE + "Semester 2:";
        setUpTestModules(GEH_PATH_2);
        expectedMessage += "\n\nHuman Cultures\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_2);
        expectedMessage += "\n\nThinking and Expression\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_2);
        expectedMessage += "\n\nSingapore Studies\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expectedMessage += "\n\nAsking Questions\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expectedMessage += "\n\nQuantitative Reasoning\n" + gemCommand.moduleExtractor(testModules, model);


        CommandResult expected = new CommandResult(expectedMessage);
        CommandResult actual = gemCommand.execute(model);
        assertEquals(expected, actual);
    }

    @Test
    public void moduleExtractor_validTest() throws IOException, DataConversionException {
        model = new ModelManager();
        setUpTestModules(SINGLE_MODULE_PATH);
        String expected = "\n" + MISSING_MODULE_1;
        StringBuilder temp = gemCommand.moduleExtractor(testModules, model);
        String actual = "" + temp;
        assertEquals(expected, actual);
    }

    @Test
    public void getCompiledModules_validTest() {
        String actual = gemCommand.getCompiledModules();
        assertNull(actual);
    }

    @Test
    public void setCompiledModules_invalidTest() {
        model = new ModelManager();
        Assertions.assertThrows(AssertionError.class, () -> gemCommand.setCompiledModules(storage, model));
    }

    @Test
    public void setCompiledModules_validTest() throws IOException, IllegalValueException, DataConversionException {
        model = new ModelManager();

        String expected = MESSAGE_GEM_SUCCESS + "\n\n" + "Semester 1:" + "\n\n";
        setUpTestModules(GEH_PATH_1);
        expected += "Human Cultures\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GET_PATH_1);
        expected += "\n\nThinking and Expression\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GES_PATH_1);
        expected += "\n\nSingapore Studies\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GEQ_PATH_1);
        expected += "\n\nAsking Questions\n" + gemCommand.moduleExtractor(testModules, model);
        setUpTestModules(GER_PATH_1);
        expected += "\n\nQuantitative Reasoning\n" + gemCommand.moduleExtractor(testModules, model);

        storage.setGehModules(GEH_SEM1_PATH);
        storage.setGeqModules(GEQ_PATH);
        storage.setGerModules(GER_PATH);
        storage.setGesModules(GES_SEM1_PATH);
        storage.setGetModules(GET_SEM1_PATH);
        gemCommand.setCompiledModules(storage, model);
        String actual = MESSAGE_GEM_SUCCESS + "\n\nSemester 1:\n\n" + gemCommand.getCompiledModules();

        assertEquals(expected, actual);
    }
}
