package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ADD_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.ModuleInfoSearcher;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.tag.Tag;
import seedu.address.nusmods.ModuleInfo;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private final ModuleInfoSearcher moduleInfoSearcher;

    /**
     * Constructs an AddCommandParser.
     */
    public AddCommandParser() {
        moduleInfoSearcher = new ModuleInfoSearcher();
    }

    /**
     * Constructs an AddCommandParser with a custom ModuleInfoSearcher object.
     * This is mainly used for stubbing.
     *
     * @param moduleInfoSearcher - the custom ModuleInfoSearcher
     */
    public AddCommandParser(ModuleInfoSearcher moduleInfoSearcher) {
        this.moduleInfoSearcher = moduleInfoSearcher;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format or if the module does
     * not exist
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADD_USAGE));
        }
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getPreamble());
        String moduleCodeText = StringUtil.ignoreCase(moduleCode.toString());
        ModuleInfo moduleInfo;

        try {
            moduleInfo = moduleInfoSearcher.searchModule(moduleCodeText);
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        ModuleTitle moduleTitle = ParserUtil.parseModuleTitle(moduleInfo.getTitle());
        ModularCredits modularCredits = ParserUtil.parseModularCredits(moduleInfo.getModuleCredit());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Module module = new Module(moduleCode, moduleTitle, modularCredits, tagList);

        return new AddCommand(module);
    }
}
