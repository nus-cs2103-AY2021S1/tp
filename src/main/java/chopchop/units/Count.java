// Count.java

package chopchop.units;

import chopchop.util.Result;

public class Count implements Quantity<Count> {

    private final double value;

    private Count(double value) {
        this.value = value;
    }

    @Override
    public Count add(Quantity<Count> qty) {

        assert qty instanceof Count;
        var cnt = (Count) qty;
        return new Count(this.value + cnt.value);
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.value);
    }

    /**
     * Returns representation of the count.
     *
     * @param value the numerical value of the quantity
     * @param unit  the unit string, which should be empty.
     * @return      the quantity, if the unit was valid.
     */
    public static Result<Count> of(double value, String unit) {
        if (unit.isEmpty()) {
            return Result.of(new Count(value));
        } else {
            return Result.error("count should not have units (found '%s')", unit);
        }
    }

    /**
     * Returns a unitless count
     *
     * @param value the number of things
     * @return      the quantity
     */
    public static Count of(double value) {
        return new Count(value);
    }
}
