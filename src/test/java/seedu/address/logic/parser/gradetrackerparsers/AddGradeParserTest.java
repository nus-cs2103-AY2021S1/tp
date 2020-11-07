package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.gradetrackercommands.AddGradeCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Grade;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddGradeParserTest {
    private static final String VALID_INPUT = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_GRADE + VALID_GRADE_1;

    private static final String MULTIPLE_MODULE_NAMES =  " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2030
            + " " + PREFIX_GRADE + VALID_GRADE_1;

    private static final String MULTIPLE_GRADES =  " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_GRADE + VALID_GRADE_1
            + " " + PREFIX_GRADE + VALID_GRADE_2;

    private static final String INVALID_MODULE_NAME =  " " + PREFIX_NAME + INVALID_MODULENAME_CSA200
            + " " + PREFIX_GRADE + VALID_GRADE_1;

    private static final String INVALID_GRADE_FIELD =  " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_GRADE + INVALID_GRADE;

    private static final String MISSING_MODULE_NAME = " " + PREFIX_GRADE + VALID_GRADE_1;

    private static final String MISSING_GRADE = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private AddGradeParser parser = new AddGradeParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Grade expectedGrade = new Grade(VALID_GRADE_1);
        assertParseSuccess(parser, VALID_INPUT, new AddGradeCommand(expectedModuleName, expectedGrade));
    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Grade expectedGrade = new Grade(VALID_GRADE_1);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_INPUT,
                new AddGradeCommand(expectedModuleName, expectedGrade));
    }

    @Test
    public void parse_multipleModuleNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2030);
        Grade expectedGrade = new Grade(VALID_GRADE_1);
        assertParseSuccess(parser, MULTIPLE_MODULE_NAMES, new AddGradeCommand(expectedModuleName, expectedGrade));
    }

    @Test
    public void parse_multipleGrades_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Grade expectedGrade = new Grade(VALID_GRADE_2);
        assertParseSuccess(parser, MULTIPLE_GRADES, new AddGradeCommand(expectedModuleName, expectedGrade));
    }

    @Test
    public void parse_compulsoryModuleNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_MODULE_NAME, expectedMessage);
    }

    @Test
    public void parse_compulsoryGradeFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_GRADE, expectedMessage);
    }

    @Test
    public void parse_invalidModuleName_failure() {
        assertParseFailure(parser, INVALID_MODULE_NAME, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidGrade_failure() {
        assertParseFailure(parser, INVALID_GRADE_FIELD, Grade.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGradeCommand.MESSAGE_USAGE);
        String nonEmptyPreamble = "add " + VALID_INPUT;
        assertParseFailure(parser, nonEmptyPreamble, expectedMessage);
    }
}
