package seedu.zookeep.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import seedu.zookeep.commons.core.index.Index;
import seedu.zookeep.commons.util.StringUtil;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.feedtime.FeedTimeComparator;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final String TRAILING_ZEROES_REGEX = "^0+(?!$)";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String Id} into a {@code Id}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Id} is invalid.
     */
    public static Id parseId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim().replaceFirst(TRAILING_ZEROES_REGEX, "");
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(trimmedId);
    }

    /**
     * Parses a {@code String species} into a {@code Species}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code species} is invalid.
     */
    public static Species parseSpecies(String species) throws ParseException {
        requireNonNull(species);
        String trimmedSpecies = species.trim();
        if (!Species.isValidSpecies(trimmedSpecies)) {
            throw new ParseException(Species.MESSAGE_CONSTRAINTS);
        }
        return new Species(trimmedSpecies);
    }

    /**
     * Parses a {@code String medicalCondition} into a {@code MedicalCondition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code medicalCondition} is invalid.
     */
    public static MedicalCondition parseMedicalCondition(String medicalCondition) throws ParseException {
        requireNonNull(medicalCondition);
        String trimmedMedicalCondition = medicalCondition.trim();
        if (!MedicalCondition.isValidMedicalConditionName(trimmedMedicalCondition)) {
            throw new ParseException(MedicalCondition.MESSAGE_CONSTRAINTS);
        }
        return new MedicalCondition(trimmedMedicalCondition);
    }

    /**
     * Parses {@code Collection<String> medicalConditions} into a {@code Set<MedicalCondition>}.
     */
    public static Set<MedicalCondition> parseMedicalConditions(
            Collection<String> medicalConditions) throws ParseException {
        requireNonNull(medicalConditions);
        final Set<MedicalCondition> medicalConditionSet = new HashSet<>();
        for (String medicalConditionName : medicalConditions) {
            medicalConditionSet.add(parseMedicalCondition(medicalConditionName));
        }
        return medicalConditionSet;
    }

    /**
     * Parses {@code Collection<String> feedTimes} into a {@code Set<FeedTime>}.
     */
    public static Set<FeedTime> parseFeedTimes(Collection<String> feedTimes) throws ParseException {
        requireNonNull(feedTimes);
        final Set<FeedTime> feedTimeSet = new TreeSet<>(new FeedTimeComparator());
        for (String feedTimeText : feedTimes) {
            feedTimeSet.add(parseFeedTime(feedTimeText));
        }
        return feedTimeSet;
    }

    /**
     * Parses a {@code String feedTimeText} into a {@code FeedTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code feedTimeText} is invalid.
     */
    public static FeedTime parseFeedTime(String feedTimeText) throws ParseException {
        requireNonNull(feedTimeText);
        String trimmedFeedTimeText = feedTimeText.trim();
        if (!FeedTime.isValidFeedTime(trimmedFeedTimeText)) {
            throw new ParseException(FeedTime.MESSAGE_CONSTRAINTS);
        }
        return new FeedTime(trimmedFeedTimeText);
    }
}
