package jimmy.mcgymmy.logic.parser.parameter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import jimmy.mcgymmy.logic.commands.CommandExecutable;
import jimmy.mcgymmy.logic.macro.Macro;
import jimmy.mcgymmy.logic.macro.MacroList;
import jimmy.mcgymmy.logic.macro.NewMacroCommand;
import jimmy.mcgymmy.logic.parser.ParserUtil;
import jimmy.mcgymmy.logic.parser.PrimitiveCommandParser;
import jimmy.mcgymmy.logic.parser.exceptions.ParseException;

// TODO: docs
public class McGymmyParser {
    private final MacroList macroList;
    private final PrimitiveCommandParser primitiveCommandParser;

    public McGymmyParser() {
        this.primitiveCommandParser = new PrimitiveCommandParser();
        this.macroList = new MacroList(this.primitiveCommandParser.getRegisteredCommands());
    }

    // TODO: this
    public CommandExecutable parse(String text) throws ParseException {
        ParserUtil.HeadTailString headTail = ParserUtil.HeadTailString.splitString(text);
        String commandName = headTail.getHead();
        if (commandName.equals("macro")) {
            return parseCreateMacro(text);
        } else if (this.macroList.hasMacro(commandName)) {
            return this.parseRunMacro(commandName, headTail.getTail());
        } else if (this.primitiveCommandParser.hasCommand(commandName)) {
            return this.primitiveCommandParser.parsePrimitiveCommand(commandName, headTail.getTail());
        } else {
            // TODO
            throw new ParseException("Error: unrecognised command");
        }
    }

    /**
     * Creates a new macro using the String declaration.
     *
     * @param declaration Macro declaration string. Format in the user guide.
     * @return Macro that was created
     * @throws ParseException if declaration has the wrong format.
     */
    private CommandExecutable parseCreateMacro(String declaration) throws ParseException {
        // note: following line also trims whitespace between semicolons.
        ParserUtil.HeadTailString headTail = ParserUtil.HeadTailString.splitString(declaration, " *; *");
        return new NewMacroCommand(this.macroList, headTail.getHead(), headTail.getTail());
    }

    private CommandExecutable parseRunMacro(String commandName, String[] arguments) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        Macro macro = this.macroList.getMacro(commandName);
        Options options = macro.getOptions();
        try {
            CommandLine args = commandLineParser.parse(options, arguments);
            return macro.commandInstance(args);
        } catch (org.apache.commons.cli.ParseException e) {
            String formattedHelp = ParserUtil.getUsageFromHelpFormatter(commandName, "", options);
            throw new ParseException(formattedHelp);
        }
    }
}
