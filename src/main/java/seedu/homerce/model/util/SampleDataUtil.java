package seedu.homerce.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Email;
import seedu.homerce.model.client.Name;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyAppointmentManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.model.util.attributes.Title;

/**
 * Contains utility methods for populating {@code Homerce} with sample data.
 */
public class SampleDataUtil {

    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static Service[] getSampleServices() {
        return new Service[]{
            new Service(new Title("Lash Lift"), new Duration(0.5), new Amount(38.0)).addServiceCode("SC000"),
            new Service(new Title("Nail Extension"), new Duration(0.5), new Amount(28.9)).addServiceCode("SC001"),
            new Service(new Title("Pedicure"), new Duration(0.5), new Amount(18.5)).addServiceCode("SC003"),
            new Service(new Title("Hair Treatment"), new Duration(1.5), new Amount(88.9)).addServiceCode("SC007"),
            new Service(new Title("Manicure"), new Duration(1.0), new Amount(21.5)).addServiceCode("SC005"),
        };
    }

    public static Expense[] getSampleExpenses() {
        return new Expense[]{
            new Expense(new Description("Conditioner"), new IsFixed("n"), new Amount(15.0),
                    new Date("10-10-2020"), new Tag("HairSupplies")),
            new Expense(new Description("Lash Tint"), new IsFixed("n"), new Amount(20.0),
                    new Date("10-12-2020"), new Tag("LashSupplies")),
            new Expense(new Description("Nail Polish"), new IsFixed("n"), new Amount(10.0),
                    new Date("09-12-2020"), new Tag("NailSupplies")),
            new Expense(new Description("Chair"), new IsFixed("y"), new Amount(25.0),
                    new Date("10-10-2020"), new Tag("Equipment")),
            new Expense(new Description("Lash Extension Glue"), new IsFixed("n"), new Amount(45.0),
                    new Date("01-12-2020"), new Tag("LashSupplies")),
        };
    }

    private static Appointment[] getSampleAppointments() {
        Client[] sampleClients = getSampleClients();
        Service[] sampleServices = getSampleServices();
        return new Appointment[]{
            new Appointment(
                new Date("02-11-2020"), new TimeOfDay("1000"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("02-11-2020"), new TimeOfDay("1330"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("03-11-2020"), new TimeOfDay("1030"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("03-11-2020"), new TimeOfDay("1330"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("04-11-2020"), new TimeOfDay("0900"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("05-11-2020"), new TimeOfDay("0900"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("05-11-2020"), new TimeOfDay("1330"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("06-11-2020"), new TimeOfDay("1030"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("07-11-2020"), new TimeOfDay("0930"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("08-11-2020"), new TimeOfDay("1230"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("09-11-2020"), new TimeOfDay("0900"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("10-11-2020"), new TimeOfDay("1030"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("10-11-2020"), new TimeOfDay("1400"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("11-11-2020"), new TimeOfDay("0930"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("12-11-2020"), new TimeOfDay("1000"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("12-11-2020"), new TimeOfDay("1230"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("13-11-2020"), new TimeOfDay("1000"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("13-11-2020"), new TimeOfDay("1230"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("14-11-2020"), new TimeOfDay("0900"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("14-11-2020"), new TimeOfDay("1130"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("14-11-2020"), new TimeOfDay("1430"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("15-11-2020"), new TimeOfDay("0900"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("15-11-2020"), new TimeOfDay("1330"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("17-11-2020"), new TimeOfDay("1030"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("18-11-2020"), new TimeOfDay("1000"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("18-11-2020"), new TimeOfDay("1200"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("18-11-2020"), new TimeOfDay("1430"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("19-10-2020"), new TimeOfDay("0930"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("19-11-2020"), new TimeOfDay("1230"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("20-11-2020"), new TimeOfDay("0930"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("21-11-2020"), new TimeOfDay("1130"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("21-11-2020"), new TimeOfDay("1530"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("22-11-2020"), new TimeOfDay("1030"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("23-11-2020"), new TimeOfDay("1230"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("24-10-2020"), new TimeOfDay("1130"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("25-11-2020"), new TimeOfDay("1030"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("26-11-2020"), new TimeOfDay("0900"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("26-11-2020"), new TimeOfDay("1130"),
                sampleClients[4], sampleServices[0]
            ),
            new Appointment(
                new Date("27-11-2020"), new TimeOfDay("0900"),
                sampleClients[0], sampleServices[4]
            ),
            new Appointment(
                new Date("27-11-2020"), new TimeOfDay("1230"),
                sampleClients[1], sampleServices[3]
            ),
            new Appointment(
                new Date("28-10-2020"), new TimeOfDay("1030"),
                sampleClients[2], sampleServices[4]
            ),
            new Appointment(
                new Date("28-11-2020"), new TimeOfDay("1330"),
                sampleClients[3], sampleServices[1]
            ),
            new Appointment(
                new Date("28-11-2020"), new TimeOfDay("1300"),
                sampleClients[4], sampleServices[0]
            ),
        };
    }

    public static ReadOnlyServiceManager getSampleServiceManager() {
        ServiceManager sampleServiceManager = new ServiceManager();
        for (Service sampleService : getSampleServices()) {
            sampleServiceManager.addService(sampleService);
        }
        return sampleServiceManager;
    }

    public static Revenue[] getSampleRevenue() {
        return new Revenue[]{
            new Revenue(new Service(new Title("Lash Lift"), new Duration(0.5), new Amount(38.0))
                .addServiceCode("SC000"), new Date("20-10-2020")),
            new Revenue(new Service(new Title("Lash Lift"), new Duration(0.5), new Amount(38.0))
                .addServiceCode("SC000"), new Date("21-10-2020")),
            new Revenue(new Service(new Title("Lash Lift"), new Duration(0.5), new Amount(38.0))
                .addServiceCode("SC000"), new Date("21-10-2020")),
            new Revenue(new Service(new Title("Nail Extension"), new Duration(0.5), new Amount(28.9))
                .addServiceCode("SC001"), new Date("22-10-2020")),
            new Revenue(new Service(new Title("Pedicure"), new Duration(0.5), new Amount(18.5))
                .addServiceCode("SC003"), new Date("22-10-2020")),
            new Revenue(new Service(new Title("Hair Treatment"), new Duration(1.5), new Amount(88.9))
                .addServiceCode("SC007"), new Date("22-10-2020")),
            new Revenue(new Service(new Title("Manicure"), new Duration(1.0), new Amount(21.5))
                .addServiceCode("SC005"), new Date("22-10-2020"))
        };
    }

    public static ReadOnlyRevenueTracker getSampleRevenueTracker() {
        RevenueTracker sampleRevenueTracker = new RevenueTracker();
        for (Revenue sampleRevenue : getSampleRevenue()) {
            sampleRevenueTracker.addRevenue(sampleRevenue);
        }
        return sampleRevenueTracker;
    }

    public static ReadOnlyClientManager getSampleClientManager() {
        ClientManager sampleAb = new ClientManager();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    public static ReadOnlyExpenseTracker getSampleExpenseTracker() {
        ExpenseTracker sampleExpenseTracker = new ExpenseTracker();
        for (Expense sampleExpense : getSampleExpenses()) {
            sampleExpenseTracker.addExpense(sampleExpense);
        }
        return sampleExpenseTracker;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

    public static ReadOnlyAppointmentManager getSampleAppointmentManager() {
        AppointmentManager sampleAppointmentManager = new AppointmentManager();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAppointmentManager.addAppointment(sampleAppointment);
        }
        return sampleAppointmentManager;
    }

}
