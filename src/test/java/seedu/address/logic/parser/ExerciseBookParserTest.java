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
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.NameContainsKeywordsPredicateForExercise;
import seedu.address.model.exercise.PropertiesMatchPredicateForExercise;
import seedu.address.testutil.ExerciseBuilder;


public class ExerciseBookParserTest {

    private final ExerciseBookParser parser = new ExerciseBookParser();

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        Name name = new Name("Push Up");
        Description description = new Description("test");
        Date date = new Date("10-10-2020");
        Calories calories = new Calories("224");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " "
                        + "n/Push Up d/test at/10-10-2020 c/224 k/foo bar baz");
        assertEquals(new FindCommand(new PropertiesMatchPredicateForExercise(name,
                description, date, calories, keywords)), command);
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
