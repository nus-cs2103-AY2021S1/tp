package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.predicates.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleNameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalModules;

class FindModCommandTest {
    private Model model = new ModelManager(TypicalModules.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalModules.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ModuleCodeContainsKeywordsPredicate firstCodePredicate = new ModuleCodeContainsKeywordsPredicate("first");
        ModuleCodeContainsKeywordsPredicate secondCodePredicate = new ModuleCodeContainsKeywordsPredicate("second");

        ModuleNameContainsKeywordsPredicate firstNamePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("first", "second"));
        ModuleNameContainsKeywordsPredicate secondNamePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("third", "fourth"));

        ModuleInstructorsContainsKeywordsPredicate firstInstructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList("first", "second"));
        ModuleInstructorsContainsKeywordsPredicate secondInstructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList("third", "fourth"));

        FindModCommand findModFirstCommand =
                new FindModCommand(Arrays.asList(firstCodePredicate, firstNamePredicate, firstInstructorPredicate));
        FindModCommand findModSecondCommand =
                new FindModCommand(Arrays.asList(secondCodePredicate, secondNamePredicate, secondInstructorPredicate));

        // same object -> returns true
        assertTrue(findModFirstCommand.equals(findModFirstCommand));

        // same values -> returns true
        FindModCommand findFirstCommandCopy =
                new FindModCommand(Arrays.asList(firstCodePredicate, firstNamePredicate, firstInstructorPredicate));
        assertTrue(findModFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findModFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findModFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findModFirstCommand.equals(findModSecondCommand));
    }

    @Test
    public void execute_nonMatchingKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);

        // All parameters exists
        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("MA1101");
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Linear Algebra");
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("KENSON");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_partialInstructorKeyword_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);

        // "m/ n/" parameters left out
        ModuleCodeContainsKeywordsPredicate codePredicate = null;
        ModuleNameContainsKeywordsPredicate namePredicate = null;
        // CARL exists but "i/" does not allow partial keywords
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("CAR");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneMatchingParameter_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);
        // CS2030 exists
        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("CS2030");

        // Do not exist
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Software Engineering");
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("ZOE");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_allMatchingParameters_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 1);

        // All parameters exist
        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("CS2030");
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Programming Methodology 2");
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("CARL");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS2030), model.getFilteredModuleList());
    }

    @Test
    public void execute_twoOptionalParameters_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 1);

        // "m/" parameter left out
        ModuleCodeContainsKeywordsPredicate codePredicate = null;

        // "n/" and "i/" parameters kept in
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Programming Methodology 2");
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("CARL");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS2030), model.getFilteredModuleList());
    }

    @Test
    public void execute_oneOptionalParameter_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 3);

        // "n/" and "i/" parameters kept out
        ModuleNameContainsKeywordsPredicate namePredicate = null;
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = null;

        // "m/" parameter kept, and is a partial keyword
        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("cs2");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2100, CS2103), model.getFilteredModuleList());
    }

    @Test
    public void execute_codeAndNameMatchingPartialKeyword_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 1);

        // All parameters exist
        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("CS2");
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Prog Metho");

        // "i/" left out
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = null;

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS2030), model.getFilteredModuleList());
    }

    @Test
    public void execute_nameAndInstructorMatchingMultipleKeywords_twoModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 2);

        // All parameters exist
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Programming computer");
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("George Carl");

        // "m/" left out
        ModuleCodeContainsKeywordsPredicate codePredicate = null;

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2100), model.getFilteredModuleList().filtered(composedPredicate));
    }


    private ModuleCodeContainsKeywordsPredicate prepareCodePredicate(String userInput) {
        return new ModuleCodeContainsKeywordsPredicate(userInput.trim());
    }

    private ModuleNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new ModuleNameContainsKeywordsPredicate(Arrays.asList(userInput.trim().split("\\s+")));
    }

    private ModuleInstructorsContainsKeywordsPredicate prepareInstructorPredicate(String userInput) {
        return new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList(userInput.trim().split("\\s+")));
    }
    private Predicate<Module> getComposedPredicate(List<Predicate<Module>> predicates) {
        Predicate<Module> composedPredicate = module -> true;
        for (Predicate<Module> predicate : predicates) {
            if (predicate == null) {
                continue;
            }
            composedPredicate = composedPredicate.and(predicate);
        }
        return composedPredicate;
    }
}
