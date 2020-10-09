package jimmy.mcgymmy.logic.macro;

import java.util.Arrays;

import jimmy.mcgymmy.logic.commands.CommandExecutable;
import jimmy.mcgymmy.logic.commands.CommandResult;
import jimmy.mcgymmy.logic.commands.exceptions.CommandException;
import jimmy.mcgymmy.logic.macro.exceptions.DuplicateMacroException;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;
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
        try {
            Macro newMacro = new Macro(headTail.getHead(), headTail.getTail(), this.statements);
            this.macroList.addMacro(newMacro);
            return new CommandResult(newMacro.getName() + " successfully added.");
        } catch (DuplicateMacroException | ParseException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
