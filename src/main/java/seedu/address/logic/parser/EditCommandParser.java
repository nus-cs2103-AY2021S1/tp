package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EDIT_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.ModuleInfoSearcher;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.nusmods.ModuleInfo;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    private final ModuleInfoSearcher moduleInfoSearcher;

    /**
     * Constructs an EditCommandParser.
     */
    public EditCommandParser() {
        moduleInfoSearcher = new ModuleInfoSearcher();
    }

    /**
     * Constructs an EditCommandParser with a custom ModuleInfoSearcher object.
     * This is mainly used for stubbing.
     *
     * @param moduleInfoSearcher - the custom ModuleInfoSearcher
     */
    public EditCommandParser(ModuleInfoSearcher moduleInfoSearcher) {
        this.moduleInfoSearcher = moduleInfoSearcher;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CODE, PREFIX_TAG);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_EDIT_USAGE));
        }

        ModuleCode code = ParserUtil.parseModuleCode(argMultimap.getPreamble());
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        ModuleInfo moduleInfo;

        if (argMultimap.getValue(PREFIX_CODE).isPresent()) {
            ModuleCode newCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_CODE).get());
            try {
                moduleInfo = moduleInfoSearcher.searchModule(newCode.toString());
                editModuleDescriptor.setModuleCode(newCode);
                editModuleDescriptor.setModuleTitle(ParserUtil.parseModuleTitle(moduleInfo.getTitle()));
                editModuleDescriptor.setModularCredits(
                    ParserUtil.parseModularCredits(moduleInfo.getModuleCredit()));
            } catch (CommandException e) {
                throw new ParseException(e.getMessage());
            }
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editModuleDescriptor::setTags);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditCommand(code, editModuleDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
