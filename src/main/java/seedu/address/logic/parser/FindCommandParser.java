package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_GROUP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentGroupPredicate;
import seedu.address.model.studentgroup.StudentGroup;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Logger logger = LogsCenter.getLogger(FindCommandParser.class);
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        logger.log(Level.INFO, "going to start parsing find command");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER,
                        PREFIX_BLOCK, PREFIX_STUDENT_GROUP, PREFIX_FLOOR,
                        PREFIX_ROOM_NUMBER, PREFIX_MATRICULATION_NUMBER);

        List<Predicate<Person>> predicates = parsePredicates(argMultimap);
        return new FindCommand(predicates);
    }

    private Predicate<Person> getStudentGroupPredicate(Collection<String> studentGroups) throws ParseException {
        logger.log(Level.INFO, "getting student group predicate");
        return new StudentGroupPredicate(parseStudentGroupsForFind(studentGroups));
    }

    /**
     * Parses {@code Collection<String> studentGroups} into a {@code Set<StudentGroup>}.
     * If {@code studentGroups} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<StudentGroup>} containing zero studentGroups.
     *
     * @throws ParseException if {@code studentGroups} contain invalid student group name.
     */
    private Set<StudentGroup> parseStudentGroupsForFind(Collection<String> studentGroups)
        throws ParseException {
        assert studentGroups != null;

        Collection<String> studentGroupNames = studentGroups.size() == 1 && studentGroups.contains("")
            ? Collections.emptySet() : studentGroups;
        return ParserUtil.parseStudentGroups(studentGroupNames);
    }

    /**
     * Parses {@code ArgumentMultimap argMultimap} into a {@code List<Predicate<Person>>}.
     * @throws ParseException if {@code list of predicates} is empty or if parsing each argument throws an exception.
     */
    private List<Predicate<Person>> parsePredicates(ArgumentMultimap argMultimap) throws ParseException {
        List<Predicate<Person>> predicates = parseRoomPredicates(argMultimap);
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicates.add(ParserUtil.parseNameContainsKeywordPredicate(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            predicates.add(ParserUtil.parseGenderMatchPredicate(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).isPresent()) {
            predicates.add(ParserUtil.parseMatriculationNumberMatchPredicate(
                    argMultimap.getValue(PREFIX_MATRICULATION_NUMBER).get()));
        }
        if (!argMultimap.getAllValues(PREFIX_STUDENT_GROUP).isEmpty()) {
            predicates.add(getStudentGroupPredicate(argMultimap.getAllValues(PREFIX_STUDENT_GROUP)));
        }
        if (predicates.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return predicates;
    }

    /**
     * Takes room related predicates from {@code ArgumentMultimap argMultimap} and returns
     * a {@code List<Predicate<Person>> roomPredicates} of room-related predicates.
     * @throws ParseException if parsing each argument throws an exception.
     */
    private List<Predicate<Person>> parseRoomPredicates(ArgumentMultimap argMultimap) throws ParseException {
        List<Predicate<Person>> roomPredicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_BLOCK).isPresent()) {
            roomPredicates.add(ParserUtil.parseRoomInBlockPredicate(argMultimap.getValue(PREFIX_BLOCK).get()));
        }
        if (argMultimap.getValue(PREFIX_FLOOR).isPresent()) {
            roomPredicates.add(ParserUtil.parseRoomInFloorPredicate(argMultimap.getValue(PREFIX_FLOOR).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            roomPredicates
                    .add(ParserUtil.parseRoomMatchesNumberPredicate(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        return roomPredicates;
    }
}
