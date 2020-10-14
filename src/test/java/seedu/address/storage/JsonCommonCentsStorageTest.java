package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.BUYFLOWERPOTS;
import static seedu.address.testutil.TypicalEntries.BUYROSESEEDS;
import static seedu.address.testutil.TypicalEntries.PAYRENT;
import static seedu.address.testutil.TypicalEntries.getTypicalCommonCents;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CommonCents;
import seedu.address.model.ReadOnlyCommonCents;

public class JsonCommonCentsStorageTest {
}
