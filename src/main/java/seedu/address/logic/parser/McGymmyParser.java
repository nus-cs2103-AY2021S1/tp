package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.parameter.AbstractParameter;
import seedu.address.logic.parser.parameter.ParameterSet;

/**
 * Parser for McGymmy commands.
 */
public class McGymmyParser {
    private final Map<String, Supplier<Command>> commandTable;
    private final CommandLineParser parser;
    private final HelpFormatter formatter;

    /**
     * Creates a new McGymmyParser
     */
    public McGymmyParser() {
        this.commandTable = new HashMap<>();
        this.addDefaultCommands();
        this.parser = new DefaultParser();
        this.formatter = new HelpFormatter();
    }

    private void addDefaultCommands() {
        this.addCommand(AddCommand.COMMAND_WORD, AddCommand::new);
        this.addCommand(ExitCommand.COMMAND_WORD, EditCommand::new);
        this.addCommand(DeleteCommand.COMMAND_WORD, DeleteCommand::new);
        this.addCommand(ClearCommand.COMMAND_WORD, ClearCommand::new);
        this.addCommand(ExitCommand.COMMAND_WORD, ExitCommand::new);
        this.addCommand(FindCommand.COMMAND_WORD, FindCommand::new);
        this.addCommand(ListCommand.COMMAND_WORD, ListCommand::new);
        this.addCommand(HelpCommand.COMMAND_WORD, HelpCommand::new);
    }

    /**
     * Parses a raw input string from the user into an executable Command.
     * @param text raw input from the user
     * @return Command if parsing is successful
     * @throws ParseException if command is not found
     * @throws ParseException if a required argument to the command is not supplied
     * @throws ParseException if an argument to the command is not in the correct format
     */
    public Command parse(String text) throws ParseException {
        String[] splitInput = text.split(" ");
        if (splitInput.length < 1) {
            throw new ParseException("Please enter a command.");
        }
        String commandName = splitInput[0];
        if (!this.commandTable.containsKey(commandName)) {
            // TODO: clean up MESSAGE class
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        Command result = this.commandTable.get(commandName).get();
        ParameterSet parameterSet = result.getParameterSet();
        Options options = parameterSet.asOptions();
        List<AbstractParameter> parameterList = parameterSet.getParameterList();
        try {
            CommandLine cmd = this.parser.parse(options, Arrays.copyOfRange(splitInput, 1, splitInput.length));
            this.provideValuesToParameterList(cmd, parameterList);
            return result;
        } catch (org.apache.commons.cli.ParseException e) {
            String message = e.getMessage() + "\n" + this.getUsage(commandName, options, parameterList);
            throw new ParseException(message);
        }
    }

    /**
     * Helper function that takes values in the commons-cli CommandLine object
     * and puts them in the parameterList
     * @param cmd CommandLine object to take values from
     * @param parameterList parameterList to put values in
     * @throws ParseException if any of the parameter's conversion functions breaks (wrongly formatted argument)
     */
    private void provideValuesToParameterList(CommandLine cmd, List<AbstractParameter> parameterList)
        throws ParseException {
        for (AbstractParameter parameter : parameterList) {
            String flag = parameter.getFlag();
            if (flag.equals("")) {
                List<String> unconsumedArgs = cmd.getArgList();
                if (unconsumedArgs.size() == 0) {
                    // TODO: improve message?
                    throw new ParseException("argument not supplied.");
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

    /**
     * Adds a new command into the parser.
     * TODO: change Command to an 'Executable' interface for macros
     * @param name Name of command to be added
     * @param commandSupplier a constructor of the command taking no arguments
     */
    public void addCommand(String name, Supplier<Command> commandSupplier) {
        if (this.commandTable.containsKey(name)) {
            // TODO throw some exception?
        }
        this.commandTable.put(name, commandSupplier);
    }

    // Creates the usage string using commons-cli's HelpFormatter and the createExampleCommand function
    private String getUsage(String commandName, Options options, List<AbstractParameter> parameterList) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        formatter.printHelp(
            printWriter,
            formatter.getWidth(),
            commandName,
            "",
            options,
            formatter.getLeftPadding(),
            formatter.getDescPadding(),
            "");
        stringWriter.write("\nEXAMPLE: ");
        stringWriter.write(this.createExampleCommand(commandName, parameterList));
        return stringWriter.toString();
    }

    private String createExampleCommand(String commandName, List<AbstractParameter> parameterList) {
        return commandName + " " + parameterList.stream()
            .map(p -> "-" + p.getFlag() + " " + p.getExample())
            .collect(Collectors.joining(" "));
    }

    public Set<String> getRegisteredCommands() {
        return this.commandTable.keySet();
    }
}
