package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULES_LISTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2103;
import static seedu.address.testutil.TypicalModules.CS50;

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
    public void execute_allParamNonMatchingKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);

        // All unmatching arguments
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
    public void execute_someParamNonMatchingKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);

        // "m/ n/" parameters left out
        ModuleCodeContainsKeywordsPredicate codePredicate = null;
        ModuleNameContainsKeywordsPredicate namePredicate = null;
        // "ZOE" does not exists
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("ZOE");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        // "m/ i/" parameters left out
        codePredicate = null;
        instructorPredicate = null;
        // No module named "Introduction to computer methodology"
        namePredicate = prepareNamePredicate("Introduction to computer methodology");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        // "n/ i/" parameters left out
        namePredicate = null;
        instructorPredicate = null;
        // No module with code "CS2105"
        codePredicate = prepareCodePredicate("CS2105");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        // "m/" parameters left out
        codePredicate = null;
        // No module named "Computer Security and no module with instructor Zoe"
        namePredicate = prepareNamePredicate("Computer Security");
        instructorPredicate = prepareInstructorPredicate("ZOE");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_atLeastOneNonMatchingParameter_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 0);

        ModuleCodeContainsKeywordsPredicate codePredicate = prepareCodePredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Software Engineering");
        // Instructor non matching
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("FIONA");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        codePredicate = prepareCodePredicate("CS2103");
        instructorPredicate = prepareInstructorPredicate("BENSON");
        // Name non matching
        namePredicate = prepareNamePredicate("Intrduction to compute science");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        namePredicate = prepareNamePredicate("Software Engineering");
        instructorPredicate = prepareInstructorPredicate("BENSON");
        // Code non matching
        codePredicate = prepareCodePredicate("CS2102");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());

        instructorPredicate = prepareInstructorPredicate("BENSON");
        // Name, code non matching
        codePredicate = prepareCodePredicate("CS2102");
        namePredicate = prepareNamePredicate("Computer Organization");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_matchingNameParameter_oneModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 1);

        ModuleCodeContainsKeywordsPredicate codePredicate = null;
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = null;
        // One word
        ModuleNameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Science");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS50), model.getFilteredModuleList());
        // Full name
        namePredicate = prepareNamePredicate("Introduction to Computer Science");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS50), model.getFilteredModuleList());
        // Mixed case
        namePredicate = prepareNamePredicate("INtRoDUcTiOn tO cOmpUteR ScieNCE");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS50), model.getFilteredModuleList());
        // Sub words
        namePredicate = prepareNamePredicate("Intro o comp Sci");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS50), model.getFilteredModuleList());
    }

    @Test
    public void execute_matchingInstructorParameter_modulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULES_LISTED, 2);

        ModuleCodeContainsKeywordsPredicate codePredicate = null;
        ModuleNameContainsKeywordsPredicate namePredicate = null;
        // One word
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate = prepareInstructorPredicate("CARL");

        List<Predicate<Module>> predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        FindModCommand command = new FindModCommand(predicateList);
        Predicate<Module> composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2103), model.getFilteredModuleList());
        // Full name
        instructorPredicate = prepareInstructorPredicate("CARL KURZ");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2103), model.getFilteredModuleList());
        // Mixed case
        instructorPredicate = prepareInstructorPredicate("cARl kUrZ");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2103), model.getFilteredModuleList());
        // Sub words
        instructorPredicate = prepareInstructorPredicate("CAR RZ");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2030, CS2103), model.getFilteredModuleList());
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

        // All parameters exist, but partial keywords
        codePredicate = prepareCodePredicate("CS2");
        namePredicate = prepareNamePredicate("Prog Metho 2");
        instructorPredicate = prepareInstructorPredicate("CAR");

        predicateList = Arrays.asList(codePredicate, namePredicate, instructorPredicate);
        command = new FindModCommand(predicateList);
        composedPredicate = getComposedPredicate(predicateList);
        expectedModel.updateFilteredModuleList(composedPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(CS2030), model.getFilteredModuleList());
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
