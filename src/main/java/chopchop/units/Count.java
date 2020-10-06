// Count.java

package chopchop.units;

import chopchop.util.Result;

public class Count implements Quantity<Count> {

    private double value = 0;

    private Count(double value) {
        this.value = value;
    }

    @Override
    public Result<Count> add(Quantity<Count> qty) {

        assert qty instanceof Count;
        var cnt = (Count) qty;
        return Result.of(new Count(this.value + cnt.value));
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.value);
    }

    public static Result<Count> of(double value, String unit) {
        if (unit.isEmpty()) {
            return Result.of(new Count(value));
        } else {
            return Result.error("count should not have units (found '%s')", unit);
        }
    }

    public static Count of(double value) {
        return new Count(value);
    }
}
