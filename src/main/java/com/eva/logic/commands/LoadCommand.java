package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.eva.logic.commands.exceptions.CommandException;
import com.eva.model.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": brings up list view of all staffs or applicants.\n"
            + "Usage: " + COMMAND_WORD + " PATH_TO_SCRIPT";

    public static final String MESSAGE_SUCCESS = "Script loaded.";

    public static final String MESSAGE_FILE_NOT_FOUND = "Script file not found.";

    public static final String MESSAGE_SCRIPT_ERROR = "An error occurred when loading script.";

    private final String scriptFilePath;

    public LoadCommand(String scriptFilePath) {
        this.scriptFilePath = scriptFilePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByExtension("js");
        try {
            FileReader reader = new FileReader(scriptFilePath);
            engine.eval(reader);
        } catch (FileNotFoundException err) {
            return new CommandResult(MESSAGE_FILE_NOT_FOUND);
        } catch (ScriptException err) {
            return new CommandResult(MESSAGE_SCRIPT_ERROR);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
