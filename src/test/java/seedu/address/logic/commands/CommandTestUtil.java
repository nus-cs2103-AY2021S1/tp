package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.AnimalContainsKeywordsPredicate;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ARCHIE = "Archie";
    public static final String VALID_NAME_BAILEY = "Bailey";
    public static final String VALID_NAME_PASHA = "Pasha";

    public static final String VALID_ID_ARCHIE = "123";
    public static final String VALID_ID_BAILEY = "456";
    public static final String VALID_ID_PASHA = "1234";

    public static final String VALID_SPECIES_ARCHIE = "German Shepherd";
    public static final String VALID_SPECIES_BAILEY = "Golden Retriever";
    public static final String VALID_MEDICAL_CONDITION_ARTHRITIS = "arthritis";
    public static final String VALID_MEDICAL_CONDITION_OBESE = "obese";

    public static final String NAME_DESC_ARCHIE = " " + PREFIX_NAME + VALID_NAME_ARCHIE;
    public static final String NAME_DESC_BAILEY = " " + PREFIX_NAME + VALID_NAME_BAILEY;
    public static final String ID_DESC_ARCHIE = " " + PREFIX_ID + VALID_ID_ARCHIE;
    public static final String ID_DESC_BAILEY = " " + PREFIX_ID + VALID_ID_BAILEY;
    public static final String SPECIES_DESC_ARCHIE = " " + PREFIX_SPECIES + VALID_SPECIES_ARCHIE;
    public static final String SPECIES_DESC_BAILEY = " " + PREFIX_SPECIES + VALID_SPECIES_BAILEY;
    public static final String MEDICAL_CONDITION_DESC_OBESE =
            " " + PREFIX_MEDICAL_CONDITION + VALID_MEDICAL_CONDITION_OBESE;
    public static final String MEDICAL_CONDITION_DESC_ARTHRITIS =
            " " + PREFIX_MEDICAL_CONDITION + VALID_MEDICAL_CONDITION_ARTHRITIS;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "911a"; // 'a' not allowed in IDs
    public static final String INVALID_SPECIES_DESC = " " + PREFIX_SPECIES; // empty string not allowed for species
    public static final String INVALID_MEDICAL_CONDITION_DESC =
            " " + PREFIX_MEDICAL_CONDITION + "healthy*"; // '*' not allowed in medicalConditions

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditAnimalDescriptor DESC_ARCHIE;
    public static final EditAnimalDescriptor DESC_BAILEY;

    static {
        DESC_ARCHIE = new EditAnimalDescriptorBuilder().withName(VALID_NAME_ARCHIE)
                .withId(VALID_ID_ARCHIE).withSpecies(VALID_SPECIES_ARCHIE)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE).build();
        DESC_BAILEY = new EditAnimalDescriptorBuilder().withName(VALID_NAME_BAILEY)
                .withId(VALID_ID_BAILEY).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the zookeep book, filtered animal list and selected animal in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ZooKeepBook expectedZooKeepBook = new ZooKeepBook(actualModel.getZooKeepBook());
        List<Animal> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAnimalList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedZooKeepBook, actualModel.getZooKeepBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredAnimalList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the animal at the given {@code targetIndex} in the
     * {@code model}'s zookeep book.
     */
    public static void showAnimalAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAnimalList().size());

        Animal animal = model.getFilteredAnimalList().get(targetIndex.getZeroBased());
        final String[] splitName = animal.getName().fullName.split("\\s+");
        model.updateFilteredAnimalList(new AnimalContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAnimalList().size());
    }

}
