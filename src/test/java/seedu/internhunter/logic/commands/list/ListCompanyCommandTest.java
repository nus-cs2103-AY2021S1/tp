package seedu.internhunter.logic.commands.list;


import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;

public class ListCompanyCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandResult commandResult;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(model.getUnfilteredCompanyList(), new ItemList<>(), new ItemList<>(),
                new UserPrefs());
        commandResult = new CommandResult(ListCompanyCommand.MESSAGE_SUCCESS, false, false, false, true);
    }

    @Test
    public void execute_listCompanyIsNotFiltered_showsSameList() {
        expectedModel.setCompanyViewIndex(INDEX_FIRST);
        assertCommandSuccess(new ListCompanyCommand(), model, commandResult, expectedModel);
    }

    @Test
    public void execute_listCompanyIsFiltered_showsEverything() {
        showCompanyAtIndex(model, INDEX_FIRST);
        expectedModel.setCompanyViewIndex(INDEX_FIRST);
        assertCommandSuccess(new ListCompanyCommand(), model, commandResult, expectedModel);
    }
}
