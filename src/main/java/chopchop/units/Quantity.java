// Quantity.java

package chopchop.units;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import chopchop.util.Result;
import chopchop.util.StringView;

public interface Quantity<T> {

    static List<BiFunction<Double, String, Result<? extends Quantity<?>>>> knownUnits = List.of(
        Mass::of, Volume::of, Count::of
    );

    public Result<? extends Quantity<T>> add(Quantity<T> qty);

    public static Result<? extends Quantity<?>> parse(String input) {

        var p = new StringView(input).span(Character::isDigit);
        var num = p.fst().parseDouble();
        var unit = p.snd();

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
