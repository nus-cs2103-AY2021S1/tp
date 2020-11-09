package seedu.address.storage.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ModuleList;
import seedu.address.model.module.Module;
import seedu.address.storage.JsonSerializableModuleList;
import seedu.address.testutil.ModuleBuilder;

public class JsonSerializableModuleListTest {

    public static final Module CS2030 = new ModuleBuilder().withName("CS2030")
            .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2030")
            .withGradePoint(5.0).withTag("completed").build();
    public static final Module CS2101 = new ModuleBuilder().withName("CS2101")
            .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2101").build();
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105")
            .withZoomLink("Lecture", "https://nus-sg.zoom.us/CS2105").build();
    public static final Module CS1101S = new ModuleBuilder().withName("CS1101S")
            .withZoomLink("Lecture", "https://nus-sg.zoom.us/cS1101S").build();
    public static final Module IS1103 = new ModuleBuilder().withName("IS1103")
            .withZoomLink("Lecture", "https://nus-sg.zoom.us/IS1103").build();

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableModuleListTest");
    private static final Path TYPICAL_MODULES_FILE = TEST_DATA_FOLDER.resolve("typicalModuleModuleList.json");
    private static final Path INVALID_MODULES_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModuleList.json");
    private static final Path DUPLICATE_MODULES_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleModuleList.json");

    private List<Module> listOfModules = new ArrayList<>(Arrays.asList(CS2030, CS2101, CS2105, CS1101S, IS1103));

    @Test
    public void toModelType_typicalModulesFile_success() throws Exception {

        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        ModuleList moduleListFromFile = dataFromFile.toModelType();
        ModuleList moduleList = new ModuleList();
        for (Module module : listOfModules) {
            moduleList.addModule(module);
        }
        boolean equal = moduleListFromFile.equals(moduleList);
        assertEquals(moduleListFromFile, moduleList);
    }

    @Test
    public void toModelType_invalidModuleFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(INVALID_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModule_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModuleList.MESSAGE_DUPLICATE_MODULE,
                 dataFromFile::toModelType);
    }

}
