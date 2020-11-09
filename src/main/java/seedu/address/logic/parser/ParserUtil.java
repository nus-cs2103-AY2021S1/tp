package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CODE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAG;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses a {@code String ModuleTitle} into a {@code ModuleTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleTitle} is invalid.
     */
    public static ModuleTitle parseModuleTitle(String moduleTitle) throws ParseException {
        requireNonNull(moduleTitle);
        String trimmedModuleTitle = moduleTitle.trim();
        return new ModuleTitle(trimmedModuleTitle);
    }

    /**
     * Parses a {@code String code} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static ModuleCode parseModuleCode(String code) throws ParseException {
        requireNonNull(code);
        String modifiedCode = code.trim().toUpperCase();
        if (!ModuleCode.isValidModuleCode(modifiedCode)) {
            throw new ParseException(String.format(MESSAGE_INVALID_MODULE_CODE, modifiedCode));
        }
        return new ModuleCode(modifiedCode);
    }

    /**
     * Parses a {@code String credits} into a {@code ModularCredits}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code credits} is invalid.
     */
    public static ModularCredits parseModularCredits(String credits) throws ParseException {
        requireNonNull(credits);
        String trimmedCredits = credits.trim();
        return new ModularCredits(trimmedCredits);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(String.format(MESSAGE_INVALID_TAG, trimmedTag));
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        final Set<String> tagNameSet = new HashSet<>();
        for (String tagName : tags) {
            if (!tagNameSet.contains(tagName.toLowerCase())) {
                tagSet.add(parseTag(tagName));
                tagNameSet.add(tagName.toLowerCase());
            }
        }
        return tagSet;
    }
}
