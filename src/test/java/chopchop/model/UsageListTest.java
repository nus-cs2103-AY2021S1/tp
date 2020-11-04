package chopchop.model;

import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalUsages.RECIPE_A_A;
import static chopchop.testutil.TypicalUsages.RECIPE_A_B;
import static chopchop.testutil.TypicalUsages.RECIPE_A_E;
import static chopchop.testutil.TypicalUsages.RECIPE_B_A;
import static chopchop.testutil.TypicalUsages.RECIPE_C_A;
import static chopchop.testutil.TypicalUsages.getRecipeList;
import static chopchop.testutil.TypicalUsages.getRecipeUsageList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import chopchop.commons.util.Pair;
import chopchop.model.usage.RecipeUsage;
import chopchop.testutil.TypicalUsages;

class UsageListTest {

    @Test
    void getUsages() {
        var ul = new UsageList<>(TypicalUsages.getRecipeList());
        assertEquals(TypicalUsages.getRecipeList(), ul.getUsages());
        var emptyList = new UsageList<>();
        assertEquals(new ArrayList<>(), emptyList.getUsages());
    }

    @Test void getUsageCountandSetAll_success() {
        assert(getRecipeUsageList().getUsageCount() == 10);
        assert(new UsageList<RecipeUsage>(new ArrayList<>()).getUsageCount() == 0);
        var elemLst10 = new UsageList<RecipeUsage>(new ArrayList<>());
        elemLst10.setAll(new UsageList<>(TypicalUsages.getRecipeList()));
        assert(elemLst10.getUsageCount() == 10);
        assert(elemLst10.getUsages().equals(TypicalUsages.getRecipeList()));
        elemLst10.setAll(new UsageList<>());
        assert(elemLst10.getUsageCount() == 0);
    }

    @Test
    void add() {
        var ul = new UsageList<>();
        assertThrows(NullPointerException.class, () -> ul.add(null));
        ul.add(RECIPE_A_A);
        assertEquals(ul.getUsages(), new ArrayList<>(Collections.singletonList(RECIPE_A_A)));
    }

    @Test
    void pop() {
        var ul = new UsageList<>(getRecipeList());
        assertThrows(NullPointerException.class, () -> ul.pop(null));
        ul.pop(RECIPE_A_B.getName());
        var expectedList = getRecipeList();
        //item on the right is removed in UsageList
        expectedList.remove(RECIPE_A_A);
        assertEquals(expectedList, ul.getUsageList());
    }

    @Test
    void getUsageList() {
        var ul = new UsageList<>();

    }

    @Test
    void getUsagesAfter() {
    }

    @Test
    void getUsagesBefore() {
    }

    @Test
    void getUsagesBetween() {
    }

    @Test
    void getRecentlyUsed() {
    }

    @Test
    void getMostUsed() {
    }

    @Test
    void testEquals() {
    }


    @Test
    public void getMostUsed_success() {
        UsageList<RecipeUsage> testList = new UsageList<>();
        testList.add(RECIPE_C_A);
        testList.add(RECIPE_B_A);
        testList.add(RECIPE_A_A);
        testList.add(RECIPE_A_A);
        testList.add(RECIPE_A_E);
        testList.add(RECIPE_A_B);
        testList.add(RECIPE_A_B);

        var res = testList.getMostUsed();
        assertEquals(new ArrayList<>(Arrays.asList(
            new Pair<>(RECIPE_A_A.getName(), "No. of times made: 5"),
            new Pair<>(RECIPE_B_A.getName(), "No. of times made: 1"),
            new Pair<>(RECIPE_C_A.getName(), "No. of times made: 1")
        )), res);
    }

    @Test
    public void getRecentlyUsed_success() {
        UsageList<RecipeUsage> testList = new UsageList<>();
        testList.add(RECIPE_B_A);
        testList.add(RECIPE_A_A);
        testList.add(RECIPE_A_E);
        testList.add(RECIPE_A_B);
        testList.add(RECIPE_C_A);
        var res = testList.getRecentlyUsed(4);
        assertEquals(new ArrayList<>(Arrays.asList(
            RECIPE_A_E, RECIPE_A_B, RECIPE_A_A, RECIPE_B_A
        )), res);
    }
}
