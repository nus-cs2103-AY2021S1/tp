package seedu.address.model.person.calorie;

public class Output extends Calorie{

    private Exercise exercise;

    public Output(CalorieCount calorieCount, Time time, Exercise exercise) {
        super(calorieCount, time);
        this.exercise = exercise;
    }

    public Exercise getExercise(){
        return exercise;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Exercise: ")
                .append(getExercise())
                .append(" Time: ")
                .append(getTime())
                .append(" Calorie Burnt: ")
                .append(getCalorieCount());

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Output(new CalorieCount("100"),new Time("18:00"),new Exercise("running")));
    }
}
