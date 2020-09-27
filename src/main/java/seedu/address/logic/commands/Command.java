package seedu.address.logic.commands;

import java.util.function.Function;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParameterConflictException;
import seedu.address.logic.parser.parameter.AbstractParameter;
import seedu.address.logic.parser.parameter.OptionalParameter;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.logic.parser.parameter.ParameterSet;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    private final ParameterSet parameterSet;

    protected Command() {
        this.parameterSet = new ParameterSet();
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    protected void registerParameter(AbstractParameter parameter) {
        try {
            this.parameterSet.addParameter(parameter);
        } catch (ParameterConflictException e) {
            // Commands can only be created by the developers so any parameter conflicts
            // should not happen, and should fail here during testing.
            assert false : e.getMessage();
        }
    }

    protected <T> Parameter<T> addParameter(String name, String flag, String description,
                                            String example, Function<String, T> converter) {
        Parameter<T> parameter = new Parameter<>(name, flag, description, example, converter);
        this.registerParameter(parameter);
        return parameter;
    }

    protected Parameter<String> addParameter(String name, String flag, String description, String example) {
        return this.addParameter(name, flag, description, example, (s) -> s);
    }

    protected <T> OptionalParameter<T> addOptionalParameter(String name, String flag, String description,
                                                            String example, Function<String, T> converter) {
        OptionalParameter<T> parameter = new OptionalParameter<>(name, flag, description, example, converter);
        this.registerParameter(parameter);
        return parameter;
    }

    protected OptionalParameter<String> addOptionalParameter(String name, String flag, String description,
                                                             String example) {
        return this.addOptionalParameter(name, flag, description, example, (s) -> s);
    }

    public ParameterSet getParameterSet() {
        return parameterSet;
    }
}
