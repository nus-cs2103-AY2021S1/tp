package com.eva.commons.util;

import static com.eva.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.ScriptException;

import org.junit.jupiter.api.Test;


public class ScriptEngineUtilTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadScriptTest");
    private static final Path NON_EXISTING_PATH = TEST_DATA_FOLDER.resolve("nonExistingScript.js");
    private static final Path FAULT_SCRIPT_PATH = TEST_DATA_FOLDER.resolve("faultyJavaScript.js");

    @Test
    public void execute_nonExistingFile_fileNotFoundExceptionThrown() {
        assertThrows(FileNotFoundException.class, () ->
                ScriptEngineUtil.executeScriptFile(NON_EXISTING_PATH.toString()));
    }

    @Test
    public void execute_faultyScript_scriptExceptionThrown() {
        assertThrows(ScriptException.class, () ->
                ScriptEngineUtil.executeScriptFile(FAULT_SCRIPT_PATH.toString()));
    }
}
