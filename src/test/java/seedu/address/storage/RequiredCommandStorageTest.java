package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.ScienceCommandTest.INVALID_PATH;
import static seedu.address.storage.RequiredCommandMessages.FOUNDATION_PATH;
import static seedu.address.storage.RequiredCommandMessages.PRECLUSION_PATH;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyGradPad;
import seedu.address.model.module.Module;

public class RequiredCommandStorageTest {
    public static final String TEST_FOUNDATION_PATH = "src/main/resources/data/foundationmodules.json";
    public static final String TEST_SCIENCE_PATH = "src/main/resources/data/sciencemodules.json";
    public static final String TEST_PRECLUSION_PATH = "src/main/resources/data/precludedmodules.json";
    private RequiredCommandStorage storage = new RequiredCommandStorage();
    private ObservableList<Module> requiredFoundation;
    private Map preclusionMap;
    public void setUpRequiredFoundation() throws DataConversionException {
        JsonGradPadStorage storage = new JsonGradPadStorage(Paths.get(TEST_FOUNDATION_PATH));
        ReadOnlyGradPad gradPad = storage.readGradPad().get();
        requiredFoundation = gradPad.getModuleList();
    }
    public void setUpPreclusionMap() throws DataConversionException {
        preclusionMap = JsonUtil.readJsonFile(Paths.get(TEST_PRECLUSION_PATH), Map.class).get();
    }

    @Test
    public void getPreclusionMap_validTest() {
        Map<String, String> actual = storage.getPreclusionMap();
        assertNull(actual);
    }

    @Test
    public void getRequiredFoundation_validTest() {
        ObservableList<Module> actual = storage.getRequiredFoundation();
        assertNull(actual);
    }
    @Test
    public void getRequiredITprof_validTest() {
        ObservableList<Module> actual = storage.getRequiredITprof();
        assertNull(actual);
    }
    @Test
    public void getRequiredMath_validTest() {
        ObservableList<Module> actual = storage.getRequiredMath();
        assertNull(actual);
    }
    @Test
    public void getRequiredScience_validTest() {
        ObservableList<Module> actual = storage.getRequiredScience();
        assertNull(actual);
    }
    @Test
    public void getRequiredInternship_validTest() {
        ObservableList<Module> actual = storage.getRequiredInternship();
        assertNull(actual);
    }

    @Test
    public void setPreclusionMapInvalidPath_throwsIOexpception() {
        assertThrows(IOException.class, ()-> storage.setPreclusionMap(INVALID_PATH));
    }
    @Test
    public void setRequiredFoundationInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setRequiredFoundation(INVALID_PATH));
    }
    @Test
    public void setRequiredITprofInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setRequiredITprof(INVALID_PATH));
    }
    @Test
    public void setRequiredMathInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setRequiredMath(INVALID_PATH));
    }
    @Test
    public void setRequiredScienceInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setRequiredScience(INVALID_PATH));
    }
    @Test
    public void setRequiredInternshipInvalidPath_throwsIOexception() {
        assertThrows(IOException.class, () -> storage.setRequiredInternship(INVALID_PATH));
    }

    @Test
    public void setPreclusionMapValidPath_success() throws IOException, DataConversionException {
        setUpPreclusionMap();
        Map expected = preclusionMap;
        storage.setPreclusionMap(PRECLUSION_PATH);
        Map<String, String> actual = storage.getPreclusionMap();
        assertEquals(expected, actual);
    }

    @Test
    public void setRequiredFoundationValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> expected = requiredFoundation;
        storage.setRequiredFoundation(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getRequiredFoundation();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredITprofValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> expected = requiredFoundation;
        storage.setRequiredITprof(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getRequiredITprof();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredMathValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> actual = requiredFoundation;
        storage.setRequiredMath(FOUNDATION_PATH);
        ObservableList<Module> expected = storage.getRequiredMath();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredScienceValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> expected = requiredFoundation;
        storage.setRequiredScience(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getRequiredScience();
        assertEquals(expected, actual);
    }
    @Test
    public void setRequiredInternshipValidPath_success() throws IOException,
            DataConversionException, IllegalValueException {
        setUpRequiredFoundation();
        ObservableList<Module> expected = requiredFoundation;
        storage.setRequiredInternship(FOUNDATION_PATH);
        ObservableList<Module> actual = storage.getRequiredInternship();
        assertEquals(expected, actual);
    }
}
