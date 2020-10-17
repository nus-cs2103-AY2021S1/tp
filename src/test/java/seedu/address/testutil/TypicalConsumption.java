package seedu.address.testutil;

import static seedu.address.testutil.TypicalRecipes.BURGER;
import static seedu.address.testutil.TypicalRecipes.EGGS;
import static seedu.address.testutil.TypicalRecipes.ENCHILADAS;
import static seedu.address.testutil.TypicalRecipes.FLORENTINE;
import static seedu.address.testutil.TypicalRecipes.MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.NOODLE;
import static seedu.address.testutil.TypicalRecipes.PASTA;
import static seedu.address.testutil.TypicalRecipes.PATTY;
import static seedu.address.testutil.TypicalRecipes.PORK;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;
import static seedu.address.testutil.TypicalRecipes.SOUP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.consumption.Consumption;

public class TypicalConsumption {
    public static final Consumption EAT_SANDWICH = new ConsumptionBuilder().withRecipe(SANDWICH).build();
    public static final Consumption EAT_PASTA = new ConsumptionBuilder().withRecipe(PASTA).build();
    public static final Consumption EAT_PORK = new ConsumptionBuilder().withRecipe(PORK).build();
    public static final Consumption EAT_FLORENTINE = new ConsumptionBuilder().withRecipe(FLORENTINE).build();
    public static final Consumption EAT_ENCHILADAS = new ConsumptionBuilder().withRecipe(ENCHILADAS).build();
    public static final Consumption EAT_EGGS = new ConsumptionBuilder().withRecipe(EGGS).build();
    public static final Consumption EAT_PATTY = new ConsumptionBuilder().withRecipe(PATTY).build();

    // Manually added
    public static final Consumption EAT_BURGER = new ConsumptionBuilder().withRecipe(BURGER).build();
    public static final Consumption EAT_SOUP = new ConsumptionBuilder().withRecipe(SOUP).build();

    // Manually added - Consumption's details found in {@code CommandTestUtil}
    public static final Consumption EAT_NOODLE = new ConsumptionBuilder().withRecipe(NOODLE).build();
    public static final Consumption EAT_MARGARITAS = new ConsumptionBuilder().withRecipe(MARGARITAS).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalConsumption() {} // prevents instantiation

    /**
     * Returns an {@code WishfulShrinking} with all the typical consumption.
     */
    public static WishfulShrinking getTypicalWishfulShrinking() {
        WishfulShrinking ab = new WishfulShrinking();
        for (Consumption consumption : getTypicalConsumption()) {
            ab.addConsumption(consumption);
        }
        return ab;
    }

    public static List<Consumption> getTypicalConsumption() {
        return new ArrayList<>(Arrays.asList(EAT_SANDWICH, EAT_PASTA, EAT_PORK,
                EAT_FLORENTINE, EAT_ENCHILADAS, EAT_EGGS, EAT_PATTY));
    }
}
