// Mass.java

package chopchop.units;

import chopchop.util.Result;

public class Mass implements Quantity<Mass> {

    private double value = 0;
    private double ratio = 0;

    private Mass(double value, double ratio) {
        this.value = value;
        this.ratio = ratio;

        // adjust the ratio if we have to. don't want to show 0.0001kg for 100mg,
        // nor 15000g for 15kg. for now, the cutoff is 5; ie. if you have 5001g of something,
        // it'll show as 5.00kg. if you have 0.499kg, it'll show as 499g. of course, this
        // only works for SI units. if you decide to be a dunce and use customary or imperial
        // units, ownself settle.
        if (this.ratio == 1 || this.ratio == 0.001) {
            if (this.value > 5000) {
                this.ratio *= 1000;
                this.value /= 1000;
            }
        } else if (this.ratio == 1000) {
            if (this.value < 0.500) {
                this.ratio /= 1000;
                this.value *= 1000;
            }
        }
    }

    @Override
    public Result<Mass> add(Quantity<Mass> qty) {

        assert qty instanceof Mass;
        var mass = (Mass) qty;
        var newval = this.value + (mass.value * mass.ratio);

        return Result.of(new Mass(newval, this.ratio));
    }

    @Override
    public String toString() {
        var unit = "";
        if (this.ratio == 1) {
            unit = "g";
        } else if (this.ratio == 1000) {
            unit = "kg";
        } else if (this.ratio == 0.001) {
            unit = "mg";
        } else {
            unit = "?";
        }

        return String.format("%.2f%s", this.value, unit);
    }

    public static Result<Mass> of(double value, String unit) {
        switch (unit) {
        case "mg":  return Result.of(milligrams(value));
        case "g":   return Result.of(grams(value));
        case "kg":  return Result.of(kilograms(value));
        default:    return Result.error("invalid unit '%s'", unit);
        }
    }

    public static Mass grams(double value) {
        return new Mass(value, 1.0);
    }

    public static Mass milligrams(double value) {
        return new Mass(value, 0.001);
    }

    public static Mass kilograms(double value) {
        return new Mass(value, 1000);
    }
}
