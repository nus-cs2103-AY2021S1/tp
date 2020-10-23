package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.storage.JsonAdaptedCommandWordAlias.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalCommandWordAliases.ROOMS_R;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.logic.commands.AddCommand;
import seedu.resireg.model.alias.Alias;
import seedu.resireg.model.alias.CommandWord;

public class JsonAdaptedCommandWordAliasTest {
    private static final String INVALID_COMMAND_WORD = "3482ndn";
    private static final String INVALID_ALIAS = AddCommand.COMMAND_WORD;

    private static final String VALID_COMMAND_WORD = ROOMS_R.getCommandWord().toString();
    private static final String VALID_ALIAS = ROOMS_R.getAlias().toString();

    @Test
    public void toModelType_validCommandWordAliasDetails_returnsCommandWordAlias() throws Exception {
        JsonAdaptedCommandWordAlias commandWordAlias = new JsonAdaptedCommandWordAlias(ROOMS_R);
        assertEquals(ROOMS_R, commandWordAlias.toModelType());
    }

    @Test
    public void toModelType_invalidCommandWord_throwsIllegalValueException() {
        JsonAdaptedCommandWordAlias commandWordAlias =
            new JsonAdaptedCommandWordAlias(INVALID_COMMAND_WORD, VALID_ALIAS);
        String expectedMessage = CommandWord.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commandWordAlias::toModelType);
    }

    @Test
    public void toModelType_nullCommandWord_throwsIllegalValueException() {
        JsonAdaptedCommandWordAlias commandWordAlias =
            new JsonAdaptedCommandWordAlias(null, VALID_ALIAS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CommandWord.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commandWordAlias::toModelType);
    }

    @Test
    public void toModelType_invalidAlias_throwsIllegalValueException() {
        JsonAdaptedCommandWordAlias commandWordAlias =
            new JsonAdaptedCommandWordAlias(VALID_COMMAND_WORD, INVALID_ALIAS);
        String expectedMessage = Alias.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commandWordAlias::toModelType);
    }

    @Test
    public void toModelType_nullAlias_throwsIllegalValueException() {
        JsonAdaptedCommandWordAlias commandWordAlias =
            new JsonAdaptedCommandWordAlias(VALID_COMMAND_WORD, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Alias.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commandWordAlias::toModelType);
    }
}

