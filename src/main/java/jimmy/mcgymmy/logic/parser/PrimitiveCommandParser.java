package jimmy.mcgymmy.logic.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.commands.AddCommand;
import jimmy.mcgymmy.logic.commands.ClearCommand;
import jimmy.mcgymmy.logic.commands.Command;
import jimmy.mcgymmy.logic.commands.DeleteCommand;
import jimmy.mcgymmy.logic.commands.EditCommand;
import jimmy.mcgymmy.logic.commands.ExitCommand;
import jimmy.mcgymmy.logic.commands.FindCommand;
import jimmy.mcgymmy.logic.commands.HelpCommand;
import jimmy.mcgymmy.logic.commands.ListCommand;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
import jimmy.mcgymmy.logic.parser.parameter.AbstractParameter;
import jimmy.mcgymmy.logic.parser.parameter.ParameterSet;

/**
 * Parser for Primitive (non-macro) McGymmy commands.
 */
public class PrimitiveCommandParser {
    private final Map<String, Supplier<Command>> commandTable;
    private final CommandLineParser parser;
    /**
     * Creates a new McGymmyParser
     */
    public PrimitiveCommandParser() {
        this.commandTable = new HashMap<>();
        this.addDefaultCommands();
        this.parser = new DefaultParser();
    }

    private void addDefaultCommands() {
        this.addCommand(AddCommand.COMMAND_WORD, AddCommand::new);
        this.addCommand(EditCommand.COMMAND_WORD, EditCommand::new);
        this.addCommand(DeleteCommand.COMMAND_WORD, DeleteCommand::new);
        this.addCommand(ClearCommand.COMMAND_WORD, ClearCommand::new);
        this.addCommand(ExitCommand.COMMAND_WORD, ExitCommand::new);
        this.addCommand(FindCommand.COMMAND_WORD, FindCommand::new);
        this.addCommand(ListCommand.COMMAND_WORD, ListCommand::new);
        this.addCommand(HelpCommand.COMMAND_WORD, HelpCommand::new);
    }

    /**
     * Parses a raw input string from the user into an executable Command.
     *
     * @param text raw input from the user
     * @return Command if parsing is successful
     * @throws ParseException if command is not found
     * @throws ParseException if a required argument to the command is not supplied
     * @throws ParseException if an argument to the command is not in the correct format
     */
    public Command parse(String text) throws ParseException {
        ParserUtil.HeadTailString headTail = ParserUtil.HeadTailString.splitString(text);
        if (headTail.getHead().equals("")) {
            throw new ParseException("Please enter a command.");
        }
        return parsePrimitiveCommand(headTail.getHead(), headTail.getTail());
    }

    /**
     * Parses a raw input string from the user into an executable Command.
     *
     * @param commandName name of the command. Typically the first word in the line.
     * @param arguments array of String arguments to the command.
     * @return Command if parsing is successful
     * @throws ParseException if command is not found
     * @throws ParseException if a required argument to the command is not supplied
     * @throws ParseException if an argument to the command is not in the correct format
     */
    public Command parsePrimitiveCommand(String commandName, String[] arguments) throws ParseException {
        if (!this.commandTable.containsKey(commandName)) {
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
        Command result = this.commandTable.get(commandName).get();
        ParameterSet parameterSet = result.getParameterSet();
        Options options = parameterSet.asOptions();
        try {
            CommandLine cmd = this.parser.parse(options, arguments);
            this.provideValuesToParameterSet(cmd, parameterSet);
            return result;
        } catch (org.apache.commons.cli.ParseException | ParseException e) {
            String message = e.getMessage() + "\n" + this.getUsage(commandName, parameterSet);
            throw new ParseException(message);
        }
    }

    /**
     * Helper function that takes values in the commons-cli CommandLine object
     * and puts them in the parameterList
     * TODO do we need to refactor this?
     * @param cmd          CommandLine object to take values from
     * @param parameterSet parameterSet to put values in
     * @throws ParseException if any of the parameter's conversion functions breaks (wrongly formatted argument)
     */
    private void provideValuesToParameterSet(CommandLine cmd, ParameterSet parameterSet) throws ParseException {
        List<AbstractParameter> parameterList = parameterSet.getParameterList();
        for (AbstractParameter parameter : parameterList) {
            String flag = parameter.getFlag();
            if (flag.equals("")) {
                List<String> unconsumedArgs = cmd.getArgList();
                if (unconsumedArgs.size() == 0) {
                    String message = parameterSet.getUnnamedParameter()
                            .map(param -> String.format("Missing required option: %s", param.getName()))
                            .orElseGet(() -> "");
                    throw new ParseException(message);
                }
                parameter.setValue(String.join(" ", unconsumedArgs));
            } else {
                String[] values = cmd.getOptionValues(flag);
                if (values == null) {
                    // optional value that was not supplied by user.
                    continue;
                }
                parameter.setValue(String.join(" ", values));
            }
        }
    }

    /** TODO factor this into parserUtil
     * Adds a new command into the parser.
     * @param name            Name of command to be added
     * @param commandSupplier a constructor of the command taking no arguments
     */
    void addCommand(String name, Supplier<Command> commandSupplier) {
        assert !this.commandTable.containsKey(name) : name + " command has already been added";
        this.commandTable.put(name, commandSupplier);
    }

    // Creates the usage string using commons-cli's HelpFormatter and the createExampleCommand function
    private String getUsage(String commandName, ParameterSet parameterSet) {
        Options options = parameterSet.asOptions();
        String formattedHelp = ParserUtil.getUsageFromHelpFormatter(commandName,
                getUnnamedParameterUsage(parameterSet), options);
        return formattedHelp + "\nEXAMPLE: " + this.createExampleCommand(commandName, parameterSet.getParameterList());
    }

    private String getUnnamedParameterUsage(ParameterSet parameterSet) {
        return parameterSet.getUnnamedParameter()
                .map(param -> String.format("<arg> %s: %s", param.getName(), param.getDescription()))
                .orElseGet(() -> "");
    }

    private String createExampleCommand(String commandName, List<AbstractParameter> parameterList) {
        return commandName + " " + parameterList.stream()
                .map(p -> p.getFlag().equals("") ? p.getExample() : "-" + p.getFlag() + " " + p.getExample())
                .collect(Collectors.joining(" "));
    }

    public Set<String> getRegisteredCommands() {
        return this.commandTable.keySet();
    }

    public Boolean hasCommand(String command) {
        return this.getRegisteredCommands().contains(command);
    }
}
