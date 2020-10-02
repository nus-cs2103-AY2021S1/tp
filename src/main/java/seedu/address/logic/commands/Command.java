package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParameterConflictException;
import seedu.address.logic.parser.parameter.AbstractParameter;
import seedu.address.logic.parser.parameter.OptionalParameter;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.logic.parser.parameter.ParameterConverter;
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

    /**
     * Registers a parameter for use in the parameter set.
     * Only registered parameters will be used in parsing.
     * @param parameter parameter to be registered.
     */
    protected void registerParameter(AbstractParameter parameter) {
        try {
            this.parameterSet.addParameter(parameter);
        } catch (ParameterConflictException e) {
            // Commands can only be created by the developers so any parameter conflicts
            // should not happen, and should fail here during testing.
            assert false : e.getMessage();
        }
    }

    /**
     * Creates a new required parameter for this command. Also registers it for parsing in the parameter set,
     * i.e. will be used in the parser.
     * @param name Name of the parameter.
     * @param flag flag used in the command, e.g. '-c' in 'add banana -c 100'
     * @param description short description of what the parameter represents, e.g. 'carbs as an integer'
     * @param example example value, e.g. '100'
     * @param converter Function to parse the value from a String to T. May throw a ParseError.
     */
    protected <T> Parameter<T> addParameter(String name, String flag, String description,
                                            String example, ParameterConverter<T> converter) {
        Parameter<T> parameter = new Parameter<>(name, flag, description, example, converter);
        this.registerParameter(parameter);
        return parameter;
    }

    /**
     * Creates a new required parameter for this command. Parameter will contain the raw input from the user.
     * Also registers it for parsing in the parameter set, i.e. will be used in the parser.
     * @param name Name of the parameter.
     * @param flag flag used in the command, e.g. '-c' in 'add banana -c 100'
     * @param description short description of what the parameter represents, e.g. 'carbs as an integer'
     * @param example example value, e.g. '100'
     */
    protected Parameter<String> addParameter(String name, String flag, String description, String example) {
        return this.addParameter(name, flag, description, example, (s) -> s);
    }

    /**
     * Creates a new optional parameter for this command. Also registers it for parsing in the parameter set,
     * i.e. will be used in the parser.
     * @param name Name of the parameter.
     * @param flag flag used in the command, e.g. '-c' in 'add banana -c 100'
     * @param description short description of what the parameter represents, e.g. 'carbs as an integer'
     * @param example example value, e.g. '100'
     * @param converter Function to parse the value from a String to T. May throw a ParseError.
     */
    protected <T> OptionalParameter<T> addOptionalParameter(String name, String flag, String description,
                                                            String example, ParameterConverter<T> converter) {
        OptionalParameter<T> parameter = new OptionalParameter<>(name, flag, description, example, converter);
        this.registerParameter(parameter);
        return parameter;
    }

    /**
     * Creates a new optional parameter for this command. Parameter will contain the raw input from the user.
     * Also registers it for parsing in the parameter set, i.e. will be used in the parser.
     * @param name Name of the parameter.
     * @param flag flag used in the command, e.g. '-c' in 'add banana -c 100'
     * @param description short description of what the parameter represents, e.g. 'carbs as an integer'
     * @param example example value, e.g. '100'
     */
    protected OptionalParameter<String> addOptionalParameter(String name, String flag, String description,
                                                             String example) {
        return this.addOptionalParameter(name, flag, description, example, (s) -> s);
    }

    public ParameterSet getParameterSet() {
        return parameterSet;
    }
}
