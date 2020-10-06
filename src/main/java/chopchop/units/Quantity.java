// Quantity.java

package chopchop.units;

import java.util.List;
import java.util.function.BiFunction;

import chopchop.util.Result;
import chopchop.util.StringView;

/**
 * The Quantity interface is an abstraction over various kinds of units, including but not limited to:
 * mass, volume, and dimensionless counts.
 *
 * It has no knowledge of how each unit interacts and fits with the others; it simply provides a static
 * method that helps to parse an input, eg. "300 ml" into a {@code Quantity<Volume>}.
 *
 * Each class implementing Quantity is expected to be immutable. The template type parameter {@code T}
 * should be the class itself, eg. {@code class Foo implements Quantity<Foo>}. This ensures some level
 * of type safety, such that one cannot add different quantity types together.
 *
 * There is no restriction on the actual value of the quantity, ie. they are allowed to be negative.
 *
 * Implementing classes should be responsible for implementing {@code add()} functionality between
 * different ratios (prefixes) of the same unit; eg. it should be possible to add 700g to 2kg to
 * obtain 2.7kg.
 */
public interface Quantity<T> {

    /**
     * Adds a quantity to this, and returns a new quantity (without modifying the original).
     * The input quantity can be negative to perform a subtraction.
     *
     * @param qty the addend
     * @return    a new Quantity after performing the addition.
     */
    public Quantity<T> add(Quantity<T> qty);

    /**
     * Parse a quantity and its associated unit.
     *
     * @param input the string input
     * @return      the parsed input, or an error message.
     */
    public static Result<? extends Quantity<?>> parse(String input) {

        final List<BiFunction<Double, String, Result<? extends Quantity<?>>>> knownUnits = List.of(
            Mass::of, Volume::of, Count::of
        );

        var p = new StringView(input).span(Character::isDigit);
        var num = p.fst().trim().parseDouble();
        var unit = p.snd().trim();

        // this loops through each known unit constructor, and returns the first one
        // that gives a non-error result. this way, the knowledge of unit names are
        // not duplicated here and in the actual implementation.
        return Result.flatten(
            Result.ofOptional(
                knownUnits.stream()
                    .map(fn -> num.then(n -> fn.apply(n, unit.toString())))
                    .filter(Result::hasValue)
                    .findFirst(),
                String.format("unknown unit '%s'", unit))
            );
    }
}
