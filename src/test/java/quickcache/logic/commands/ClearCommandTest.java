package quickcache.logic.commands;

import org.junit.jupiter.api.Test;

import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.QuickCache;
import quickcache.model.UserPrefs;
import quickcache.testutil.TypicalFlashcards;

public class ClearCommandTest {

    @Test
    public void execute_emptyQuickCache_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyQuickCache_success() {
        Model model = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());
        expectedModel.setQuickCache(new QuickCache());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
