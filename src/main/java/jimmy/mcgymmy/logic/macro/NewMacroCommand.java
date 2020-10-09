package jimmy.mcgymmy.logic.macro;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import jimmy.mcgymmy.logic.commands.CommandExecutable;
import jimmy.mcgymmy.logic.commands.CommandResult;
import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.logic.macro.exceptions.DuplicateMacroException;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.model.Model;

public class NewMacroCommand implements CommandExecutable {
    private final MacroList macroList;
    private final String argumentDeclaration;
    private final String[] statements;

    public NewMacroCommand(MacroList macroList, String argumentDeclaration, String[] statements) {
        this.macroList = macroList; // should this be part of the model?
        this.argumentDeclaration = argumentDeclaration;
        this.statements = statements;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ParserUtil.HeadTailString headTail = ParserUtil.HeadTailString.splitString(argumentDeclaration);
        Options options = this.parseOptions(headTail.getTail());
        Macro newMacro = new Macro(headTail.getHead(), Arrays.asList(statements), options);
        try {
            this.macroList.addMacro(newMacro);
            return new CommandResult(newMacro.getName() + " successfully added.");
        } catch (DuplicateMacroException e) {
            throw new CommandException(e.getMessage());
        }
    }

    private Options parseOptions(String[] macroArgs) throws CommandException {
        Options options = new Options();
        try {
            for (String name : macroArgs) {
                String description = "macro argument " + name;
                Option option = new Option(name, true, description);
                option.setRequired(true);
                options.addOption(option);
            }
        } catch (IllegalArgumentException e) {
            // TODO better error message
            // TODO should we throw a command or parse exception here?
            throw new CommandException("Wrong format for macros.");
        }
        return options;
    }
}
