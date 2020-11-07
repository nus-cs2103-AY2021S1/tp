package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LoadCommand.
 */
public class LoadCommandResult {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LoadScriptTest");
    private static final Path NORMAL_JS_FILE = TEST_DATA_FOLDER.resolve("normalJavaScript.js");

    private Model model = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalPersonDatabase(),
            getTypicalStaffDatabase(), getTypicalApplicantDatabase(), new UserPrefs());

    /**
     * execute a normal JavaScript file.
     */
    @Test
    public void execute_normalJavaScript_success() {
        LoadCommand command = new LoadCommand(NORMAL_JS_FILE.toString());
        assertCommandSuccess(command, model, LoadCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
