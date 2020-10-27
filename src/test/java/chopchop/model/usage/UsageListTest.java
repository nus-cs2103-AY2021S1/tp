package chopchop.model.usage;

import static chopchop.testutil.TypicalUsages.RECIPE_A_A;
import static chopchop.testutil.TypicalUsages.RECIPE_A_B;
import static chopchop.testutil.TypicalUsages.RECIPE_A_C;
import static chopchop.testutil.TypicalUsages.RECIPE_A_D;
import static chopchop.testutil.TypicalUsages.RECIPE_A_E;
import static chopchop.testutil.TypicalUsages.RECIPE_A_F;
import static chopchop.testutil.TypicalUsages.RECIPE_B_A;
import static chopchop.testutil.TypicalUsages.RECIPE_B_B;
import static chopchop.testutil.TypicalUsages.RECIPE_B_C;
import static chopchop.testutil.TypicalUsages.RECIPE_B_D;
import static chopchop.testutil.TypicalUsages.RECIPE_B_E;
import static chopchop.testutil.TypicalUsages.RECIPE_B_F;
import static chopchop.testutil.TypicalUsages.RECIPE_C_A;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import chopchop.commons.util.Pair;
import chopchop.model.UsageList;
import chopchop.testutil.TypicalUsages;

public class UsageListTest {
    //nice chrono order first item being the oldest and last being the future.
    public static final UsageList<RecipeUsage> REC_USAGE_1 = TypicalUsages.getRecipeUsageList();
    public static final UsageList<IngredientUsage> IND_USAGE_2 = TypicalUsages.getIngredientUsageList();
    public static final UsageList<RecipeUsage>
        REVERSE_ORDER_REC = new UsageList<>(Arrays.asList(RECIPE_A_F, RECIPE_B_F, RECIPE_A_E,
        RECIPE_B_E, RECIPE_A_D, RECIPE_B_D, RECIPE_A_C, RECIPE_B_C, RECIPE_A_B, RECIPE_B_B, RECIPE_A_A, RECIPE_B_A));

    @Test
    public void getUsageList_success() {
        assert(REVERSE_ORDER_REC.getUsageList().equals(Arrays.asList(RECIPE_A_F, RECIPE_B_F, RECIPE_A_E,
            RECIPE_B_E, RECIPE_A_D, RECIPE_B_D, RECIPE_A_C, RECIPE_B_C, RECIPE_A_B, RECIPE_B_B, RECIPE_A_A,
            RECIPE_B_A)));
    }

    @Test void getUsageCountandSetAll_success() {
        assert(IND_USAGE_2.getUsageCount() == 12);
        assert(new UsageList<RecipeUsage>(new ArrayList<>()).getUsageCount() == 0);
        var elemlst12 = new UsageList<RecipeUsage>(new ArrayList<>());
        elemlst12.setAll(REC_USAGE_1);
        assert(elemlst12.getUsageCount() == 12);
        elemlst12.setAll(new UsageList<>());
        assert(elemlst12.getUsageCount() == 0);
    }

    @Test
    public void getMostUsed_success() {
        UsageList<RecipeUsage> testList = new UsageList<>();
        testList.add(RECIPE_B_A);
        testList.add(RECIPE_A_A);
        testList.add(RECIPE_A_A);
        testList.add(RECIPE_A_E);
        testList.add(RECIPE_A_B);
        testList.add(RECIPE_A_B);
        testList.add(RECIPE_C_A);
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
