package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.NameContainsKeywordsPredicateForExercise;
import seedu.address.testutil.ExerciseBuilder;


public class ExerciseBookParserTest {

    private final ExerciseBookParser parser = new ExerciseBookParser();

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicateForExercise(keywords)), command);
    }

    @Test
    public void parseCommand_add() throws Exception {
        Exercise exercise = new ExerciseBuilder().build();
        AddCommand command = (AddCommand) parser
                .parseCommand(AddCommand.COMMAND_WORD + " "
                        + "n/Push up d/Testing 2254 at/09-10-2020 c/224");
        assertEquals(new AddCommand(exercise), command);
    }

    @Test
    public void parseCommand_archive() throws Exception {
        Path path = Paths.get("testing.json");
        ArchiveCommand command = (ArchiveCommand) parser.parseCommand(ArchiveCommand.COMMAND_WORD
                + " f/testing.json");
        assertEquals(new ArchiveCommand(path), command);
    }


}
