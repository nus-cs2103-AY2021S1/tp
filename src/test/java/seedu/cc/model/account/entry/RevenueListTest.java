package seedu.cc.model.account.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.tag.Tag;
import seedu.cc.testutil.RevenueBuilder;

class RevenueListTest {

    @Test
    public void contains_true() {
        ObservableList<Revenue> internalList = FXCollections.observableArrayList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("art"));
        tags1.add(new Tag("craft"));
        Revenue r1 = new Revenue(new Description("sold crafts"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("art"));
        tags2.add(new Tag("painting"));
        Revenue r2 = new Revenue(new Description("sold painting"), new Amount("25"), tags2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("earrings"));
        tags3.add(new Tag("handmade"));
        Revenue r3 = new Revenue(new Description("sold earrings"), new Amount("10.50"), tags3);

        internalList.addAll(r1, r2, r3);
        assertTrue(internalList.contains(r1));
        assertTrue(internalList.contains(r2));
        assertTrue(internalList.contains(r3));
    }

    @Test
    public void contains_false() {
        ObservableList<Revenue> internalList = FXCollections.observableArrayList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("art"));
        tags1.add(new Tag("craft"));
        Revenue r1 = new Revenue(new Description("sold crafts"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("art"));
        tags2.add(new Tag("painting"));
        Revenue r2 = new Revenue(new Description("sold painting"), new Amount("25"), tags2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("earrings"));
        tags3.add(new Tag("handmade"));
        Revenue r3 = new Revenue(new Description("sold earrings"), new Amount("10.50"), tags3);

        internalList.addAll(r1, r2);
        assertFalse(internalList.contains(r3));
    }

    @Test
    public void add() {
        RevenueList revenueList = new RevenueList();
        Revenue revenue = (new RevenueBuilder()).build();
        revenueList.add(revenue);

        assertTrue(revenueList.contains(revenue));
    }

    @Test
    public void setRevenue() {
        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("art"));
        tags1.add(new Tag("crafts"));
        Revenue r1 = new Revenue(new Description("bought craft supplies"), new Amount("5.00"), tags1);
        RevenueList revenueList = new RevenueList();
        revenueList.add(r1);

        Revenue r2 = (new RevenueBuilder()).build();
        revenueList.setRevenue(r1, r2);

        assertTrue(revenueList.contains(r2));
    }

    @Test
    public void remove() {
        RevenueList revenueList = new RevenueList();
        Revenue revenue = (new RevenueBuilder()).build();
        revenueList.add(revenue);
        revenueList.remove(revenue);

        assertFalse(revenueList.contains(revenue));
    }

    @Test
    void setRevenues() {
        RevenueList revenueList = new RevenueList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("art"));
        tags1.add(new Tag("craft"));
        Revenue r1 = new Revenue(new Description("sold crafts"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("art"));
        tags2.add(new Tag("painting"));
        Revenue r2 = new Revenue(new Description("sold painting"), new Amount("25"), tags2);

        revenueList.add(r1);
        revenueList.add(r2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("earrings"));
        tags3.add(new Tag("handmade"));
        Revenue r3 = new Revenue(new Description("sold earrings"), new Amount("10.50"), tags3);

        Revenue r4 = (new RevenueBuilder()).build();
        List<Revenue> replacement = Arrays.asList(r3, r4);

        revenueList.setRevenues(replacement);

        assertFalse(revenueList.contains(r1));
        assertFalse(revenueList.contains(r2));
        assertTrue(revenueList.contains(r3));
        assertTrue(revenueList.contains(r4));
    }

    @Test
    void testSetRevenues() {
        RevenueList revenueList = new RevenueList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("art"));
        tags1.add(new Tag("craft"));
        Revenue r1 = new Revenue(new Description("sold crafts"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("art"));
        tags2.add(new Tag("painting"));
        Revenue r2 = new Revenue(new Description("sold painting"), new Amount("25"), tags2);

        revenueList.add(r1);
        revenueList.add(r2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("earrings"));
        tags3.add(new Tag("handmade"));
        Revenue r3 = new Revenue(new Description("sold earrings"), new Amount("10.50"), tags3);

        Revenue r4 = (new RevenueBuilder()).build();
        List<Revenue> replacement = Arrays.asList(r3, r4);

        revenueList.setRevenues(replacement);

        assertFalse(revenueList.contains(r1));
        assertFalse(revenueList.contains(r2));
        assertTrue(revenueList.contains(r3));
        assertTrue(revenueList.contains(r4));
    }
}
