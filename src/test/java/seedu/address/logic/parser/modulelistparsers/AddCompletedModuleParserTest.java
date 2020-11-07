package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADEPOINT_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADEPOINT_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MC_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_POINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.AddCompletedModuleCommand;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ModuleBuilder;



public class AddCompletedModuleParserTest {
    private static final String multipleMCs = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_2
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String multipleGPs = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_2
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_4
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String multipleNames = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_ES2660
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String multipleTags = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
            + " " + PREFIX_TAG + VALID_TAG_LECTURE
            + " " + PREFIX_TAG + VALID_TAG_TUTORIAL;

    private static final String validInput = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String validInputNoTag = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
            + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5;

    private static final String validInputNoModularCredits = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_TAG + VALID_TAG_LECTURE;

    private static final String validInputOnlyName = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private AddCompletedModuleParser parser = new AddCompletedModuleParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_4).withGradePoint(5.0).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, validInput, new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_tagFieldAbsent_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_4).withGradePoint(5.0).build();

        assertParseSuccess(parser, validInputNoTag, new AddCompletedModuleCommand(expectedModule));
    }

    /*@Test
    public void parse_modularCreditsFieldAbsent_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withTag(VALID_TAG_LECTURE).build();

        assertParseSuccess(parser, validInputNoModularCredits, new AddModuleCommand(expectedModule));
    }*/

    /*@Test
    public void parse_onlyNamePresent_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T).build();

        assertParseSuccess(parser, validInputOnlyName, new AddModuleCommand(expectedModule));
    }*/

    @Test
    public void parse_preambleWhiteSpace_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_4).withGradePoint(5.0).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validInput,
                new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleNames_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_ES2660)
                .withMC(VALID_MC_4).withGradePoint(5.0).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, multipleNames, new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleGradePoints_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_2).withGradePoint(5.0).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, multipleGPs, new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleModularCredits_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_2).withGradePoint(5.0).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, multipleMCs, new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_multipleTags_success() {
        Module expectedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2103T)
                .withMC(VALID_MC_4).withGradePoint(5.0).withTag(VALID_TAG_TUTORIAL).withTag(VALID_TAG_LECTURE).build();
        assertParseSuccess(parser, multipleTags, new AddCompletedModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCompletedModuleCommand.MESSAGE_USAGE);
        String missingName = " " + PREFIX_MODULAR_CREDITS + VALID_MC_4 + " " + PREFIX_GRADE_POINT
                + VALID_GRADEPOINT_5 + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, missingName, expectedMessage);
    }

    @Test
    public void parse_compulsoryModularCreditsFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCompletedModuleCommand.MESSAGE_USAGE);
        String missingName = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T + " " + PREFIX_GRADE_POINT
                + VALID_GRADEPOINT_5 + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, missingName, expectedMessage);
    }

    @Test
    public void parse_compulsoryGradePointFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCompletedModuleCommand.MESSAGE_USAGE);
        String missingName = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
                + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4 + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, missingName, expectedMessage);
    }

    @Test
    public void parse_compulsoryAllFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompletedModuleCommand.MESSAGE_USAGE);
        String missingAll = "";
        assertParseFailure(parser, missingAll, expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        String invalidName = " " + PREFIX_NAME + "@@@@@@@ " + PREFIX_MODULAR_CREDITS + VALID_MC_4
                + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
                + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, invalidName, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidModularCredits_failure() {
        String invalidMC = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T + " " + PREFIX_MODULAR_CREDITS + "-2"
                + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
                + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, invalidMC, ModularCredits.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidGradePoint_failure() {
        String invalidMC = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T + " " + PREFIX_MODULAR_CREDITS + "-2"
                + " " + PREFIX_GRADE_POINT + "-2"
                + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, invalidMC, ModularCredits.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTags_failure() {
        String invalidTags = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T + " " + PREFIX_MODULAR_CREDITS + VALID_MC_4
                + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5
                + " " + PREFIX_TAG + "@#@#";
        assertParseFailure(parser, invalidTags, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompletedModuleCommand.MESSAGE_USAGE);
        String nonEmptyPreamble = "add " + PREFIX_NAME + VALID_MODULENAME_CS2103T + " " + PREFIX_MODULAR_CREDITS
                + VALID_MC_4 + " " + PREFIX_GRADE_POINT + VALID_GRADEPOINT_5 + " " + PREFIX_TAG + VALID_TAG_LECTURE;
        assertParseFailure(parser, nonEmptyPreamble, expectedMessage);
    }
}
