package jimmy.mcgymmy.logic.macro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import jimmy.mcgymmy.logic.commands.CommandExecutable;
import jimmy.mcgymmy.logic.commands.CommandResult;
import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.logic.parser.PrimitiveCommandParser;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
import jimmy.mcgymmy.model.Model;

/**
 * Macro class that deals with user-created macros.
 */
public class Macro {
    private final String name;
    private final List<String> rawCommands;
    private final Options options;

    // Package private because only NewMacroCommand should create this
    Macro(String name, List<String> commands, Options options) {
        this.name = name;
        this.rawCommands = commands;
        this.options = options;
    }

    public String getName() {
        return this.name;
    }

    public Options getOptions() {
        return this.options;
    }

    public CommandExecutable commandInstance(CommandLine args) {
        return new CommandExecutable() {
            @Override
            public CommandResult execute(Model model) throws CommandException {
                return executeWith(model, args);
            }
        };
    }

    public CommandResult executeWith(Model model, CommandLine args) throws CommandException {
        String[] rawCommands = this.substituteAll(args);
        List<String> messagesToUser = new ArrayList<>();
        List<CommandExecutable> commandExecutables = this.parseCommands(rawCommands);
        int lastCommandIndex = 0;
        try {
            for (lastCommandIndex = 0; lastCommandIndex < commandExecutables.size(); lastCommandIndex++) {
                 CommandResult result = commandExecutables.get(lastCommandIndex).execute(model);
                 if (result.isExit() || result.isShowHelp()) {
                     return result;
                 }
                 messagesToUser.add(result.getFeedbackToUser());
            }
            return new CommandResult(String.join("\n", messagesToUser));
        } catch (CommandException e) {
            /* note: not factoring out code below because its only used here and its
               purpose/what it's doing is obvious, and factoring it out will be very messy. */
            String errorLocation = "\n\nAn error occurred when executing this command:\n"
                + rawCommands[lastCommandIndex];
            String doneCommands = lastCommandIndex == 0 ? ""
                : "\n\nThe following commands executed successfully:\n"
                + String.join("\n", Arrays.copyOfRange(rawCommands, 0, lastCommandIndex));
            String notDoneCommands = lastCommandIndex + 1 == rawCommands.length ? ""
                : "\n\n The following commands were not executed:\n"
                + String.join("\n", Arrays.copyOfRange(rawCommands, lastCommandIndex + 1, rawCommands.length));

            String message = e.getMessage() + errorLocation + doneCommands + notDoneCommands;
            throw new CommandException(message);
        }
    }

    private String[] substituteAll(CommandLine args) {
        String[] output = new String[this.rawCommands.size()];
        for (int i = 0; i < this.rawCommands.size(); i++) {
            output[i] = substitute(args, this.rawCommands.get(i));
        }
        return output;
    }

    private List<CommandExecutable> parseCommands(String[] rawCommands) throws CommandException {
        PrimitiveCommandParser parser = new PrimitiveCommandParser();
        List<CommandExecutable> commandExecutables = new ArrayList<>();
        try {
            for (String rawCommand : rawCommands) {
                commandExecutables.add(parser.parse(rawCommand));
            }
            return commandExecutables;

        } catch (ParseException e) {
            // TODO better error message
            throw new CommandException("Macro failed to parse: " + e.getMessage());
        }
    }

    private String substitute(CommandLine args, String line) {
        // TODO possibly use a StringBuilder here instead for performance.
        for (Iterator<Option> i = args.iterator(); i.hasNext(); ) {
            Option option = i.next();
            String from = option.getOpt();
            String to = args.getOptionValue(from);
            line = line.replaceAll("\\\\" + from, to);
        }

        String unusedArgs = String.join(" ", args.getArgList());
        // regex is matching for "\$".
        return line.replaceAll("\\\\\\$", unusedArgs).trim();
    }
}
