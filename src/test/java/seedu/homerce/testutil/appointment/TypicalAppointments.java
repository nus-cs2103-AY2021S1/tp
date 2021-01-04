package seedu.homerce.testutil.appointment;

import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.BENSON;
import static seedu.homerce.testutil.client.TypicalClients.CARL;
import static seedu.homerce.testutil.service.TypicalServices.HAIR_TREATMENT;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.AppointmentManager;

public class TypicalAppointments {

    public static final Appointment OCT_25_2020_2PM_HAIR_TREATMENT_ALICE = new AppointmentBuilder()
        .withDate("25-10-2020")
        .withTimeOfDay("1400")
        .withClient(ALICE)
        .withService(HAIR_TREATMENT)
        .build();

    public static final Appointment OCT_25_2020_230PM_LASH_LIFT_BENSON = new AppointmentBuilder()
        .withDate("25-10-2020")
        .withTimeOfDay("1430")
        .withClient(BENSON)
        .withService(LASH_LIFT)
        .build();

    public static final Appointment OCT_26_2020_11AM_MANICURE_CARL = new AppointmentBuilder()
        .withDate("26-10-2020")
        .withTimeOfDay("1100")
        .withClient(CARL)
        .withService(MANICURE)
        .build();

    public static final Appointment NOV_11_2020_12PM_LASH_LIFT_BENSON = new AppointmentBuilder()
        .withDate("11-11-2020")
        .withTimeOfDay("1430")
        .withClient(BENSON)
        .withService(LASH_LIFT)
        .build();

    public static final Appointment OCT_27_2020_12PM_LASH_LIFT_BENSON = new AppointmentBuilder()
            .withDate("27-10-2020")
            .withTimeOfDay("1200")
            .withClient(BENSON)
            .withService(LASH_LIFT)
            .build();

    private TypicalAppointments() {
    } // prevents instantiation

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE,
            OCT_25_2020_230PM_LASH_LIFT_BENSON,
            OCT_26_2020_11AM_MANICURE_CARL)
        );
    }

    public static AppointmentManager getTypicalAppointmentManager() {
        AppointmentManager appointmentManager = new AppointmentManager();
        appointmentManager.addAppointment(new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build());
        appointmentManager.addAppointment(new AppointmentBuilder(OCT_26_2020_11AM_MANICURE_CARL).build());
        appointmentManager.addAppointment(new AppointmentBuilder(NOV_11_2020_12PM_LASH_LIFT_BENSON).build());
        return appointmentManager;
    }
}
