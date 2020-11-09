package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Grade;
import seedu.address.model.module.ModularCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.model.semester.Semester;
import seedu.address.model.semester.SemesterManager;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
    public static ModuleName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim().toUpperCase();
        if (!ModuleName.isValidModName(trimmedName)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedName);
    }

    /**
     * Parses a {@code String semester} into a {@code Semester}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semester} is invalid.
     */
    public static Semester parseSemester(String semester) throws ParseException {
        requireNonNull(semester);
        String trimmedSemester = semester.trim();
        if (!SemesterManager.isValidSemester(trimmedSemester)) {
            throw new ParseException(Messages.MESSAGE_INVALID_SEMESTER);
        }
        return Semester.valueOf(trimmedSemester);
    }

    /**
     * Parses a {@code String modularCredit} into a {@code ModularCredit}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid and not an integer.
     */
    public static ModularCredit parseModularCredit(String modularCredit) throws ParseException {
        requireNonNull(modularCredit);
        String trimmedModularCredit = modularCredit.trim();
        try {
            int integerModularCredit = Integer.parseInt(trimmedModularCredit);
            if (!ModularCredit.isValidModularCredit(integerModularCredit)) {
                throw new ParseException(ModularCredit.MESSAGE_INVALID_MODULAR_CREDIT);
            }
            return new ModularCredit(integerModularCredit);
        } catch (NumberFormatException e) {
            throw new ParseException(ModularCredit.MESSAGE_INVALID_MODULAR_CREDIT);
        }
    }

    /**
     * Parses a {@code String grade} into an {@code Grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim().toUpperCase();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    /**
     * Parses a {@code String goal} into a {@code Goal}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code goal} is invalid.
     */
    public static GoalTarget parseGoal(String goal) throws ParseException {
        requireNonNull(goal);
        String trimmedGoal = goal.trim();

        try {
            int intGoal = Integer.parseInt(trimmedGoal);
            if (!GoalTarget.isValidGoal(intGoal)) {
                throw new ParseException(GoalTarget.MESSAGE_CONSTRAINTS);
            }
            return new GoalTarget(intGoal);
        } catch (final NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalTarget.MESSAGE_CONSTRAINTS));
        }
    }

    /**
     * Parses a {@code String userInput} to ensure no trailing words after level.
     * @param userInput user input of any.
     * @throws ParseException if user input is present after level.
     */
    public static void parseGoalLevel(String userInput) throws ParseException {
        requireNonNull(userInput);
        String trimmedGoal = userInput.trim();

        if (!trimmedGoal.equals("")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SetCommand.MESSAGE_USAGE));
        }
    }
}
