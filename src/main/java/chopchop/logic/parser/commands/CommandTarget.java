// CommandTarget.java

package chopchop.logic.parser.commands;

import java.util.Optional;

/**
 * Just a very simple enumeration to represent the "target" of a command, which is either
 * recipes (eg. add recipe) or ingredients (eg. add ingredient).
 */
public enum CommandTarget {
    RECIPE,
    INGREDIENT;

    /**
     * Creates a {@code CommandTarget} from its name.
     */
    public static Optional<CommandTarget> of(String str) {
        if (str.equals("recipe")) {
            return Optional.of(RECIPE);
        } else if (str.equals("ingredient")) {
            return Optional.of(INGREDIENT);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Creates a {@code CommandTarget} from its name.
     */
    public static Optional<CommandTarget> of(String str, boolean acceptsPlural) {
        if (str.equals("recipe") || (acceptsPlural && str.equals("recipes"))) {
            return Optional.of(RECIPE);
        } else if (str.equals("ingredient") || (acceptsPlural && str.equals("ingredients"))) {
            return Optional.of(INGREDIENT);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
