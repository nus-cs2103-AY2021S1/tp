// Volume.java

package chopchop.units;

import chopchop.util.Result;

public class Volume implements Quantity<Volume> {

    private static double RATIO_LITRE       = 1.0;
    private static double RATIO_MILLILITRE  = 0.001;
    private static double RATIO_CUP         = 0.250;    // we're using the metric cup, for obvious reasons.
    private static double RATIO_TEASPOON    = 0.005;    // and metric tea and tablespoons.
    private static double RATIO_TABLESPOON  = 0.015;

    private static String UNIT_LITRE        = "l";
    private static String UNIT_MILLILITRE   = "ml";
    private static String UNIT_CUP          = "cup";
    private static String UNIT_CUPS         = "cups";   // this is for convenience.
    private static String UNIT_TEASPOON     = "tsp";
    private static String UNIT_TABLESPOON   = "tbsp";

    private double value = 0;
    private double ratio = 0;

    private Volume(double value, double ratio) {
        this.value = value;
        this.ratio = ratio;

        // see the comment in Mass.java for an explanation of this.
        if (this.ratio == 1 || this.ratio == 0.001) {
            if (this.value > 5000) {
                this.ratio *= 1000;
                this.value /= 1000;
            }
        }
    }

    @Override
    public Result<Volume> add(Quantity<Volume> qty) {

        assert qty instanceof Volume;
        var vol = (Volume) qty;
        var newval = this.value + (vol.value * vol.ratio);

        return Result.of(new Volume(newval, this.ratio));
    }

    @Override
    public String toString() {
        String unit = "";

        if (this.ratio == RATIO_MILLILITRE) {
            unit = UNIT_MILLILITRE;
        } else if (this.ratio == RATIO_LITRE) {
            unit = UNIT_LITRE;
        } else if (this.ratio == RATIO_CUP) {
            unit = (this.value == 1 ? UNIT_CUP : UNIT_CUPS);
        } else if (this.ratio == RATIO_TABLESPOON) {
            unit = UNIT_TABLESPOON;
        } else if (this.ratio == RATIO_TEASPOON) {
            unit = UNIT_TEASPOON;
        } else {
            unit = "?";
        }

        return String.format("%.2f%s", this.value, unit);
    }

    public static Result<Volume> of(double value, String unit) {

        double ratio = 1;

        if (unit.equals(UNIT_MILLILITRE)) {
            ratio = RATIO_MILLILITRE;
        } else if (unit.equals(UNIT_LITRE)) {
            ratio = RATIO_LITRE;
        } else if (unit.equals(UNIT_CUP) || unit.equals(UNIT_CUPS)) {
            ratio = RATIO_CUP;
        } else if (unit.equals(UNIT_TABLESPOON)) {
            ratio = RATIO_TABLESPOON;
        } else if (unit.equals(UNIT_TEASPOON)) {
            ratio = RATIO_TEASPOON;
        } else {
            return Result.error("unknown unit '%s'", unit);
        }

        return Result.of(new Volume(value, ratio));
    }

    public static Volume millilitres(double value) {
        return new Volume(value, RATIO_MILLILITRE);
    }

    public static Volume litres(double value) {
        return new Volume(value, RATIO_LITRE);
    }

    public static Volume cups(double value) {
        return new Volume(value, RATIO_CUP);
    }

    public static Volume teaspoons(double value) {
        return new Volume(value, RATIO_TEASPOON);
    }

    public static Volume tablespoons(double value) {
        return new Volume(value, RATIO_TABLESPOON);
    }
}
