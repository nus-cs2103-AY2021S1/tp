package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.EFF_COM;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.ModuleInfoRetriever;

public class ModuleInfoRetrieverTest {

    private ModuleInfoRetriever retriever = new ModuleInfoRetriever();

    @Test
    public void retrieveValidModule_correctResult() {
        assertEquals(retriever.retrieve(EFF_COM.getModuleName().fullModName).get("title"),
                 "Effective Communication for Computing Professionals");
        assertEquals(retriever.retrieve(EFF_COM.getModuleName().fullModName).get("moduleCredit"), "4");
    }

    @Test
    public void retrieveInvalidModule_returnInvalidMap() {
        assertEquals(retriever.retrieve("FAKEMODULE").get("title"), "N/A");
        assertEquals(retriever.retrieve("FAKEMODULE").get("moduleCredit"), "N/A");
    }

    @Test
    public void setGradeBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGradeBookFilePath(null));
    }
}
