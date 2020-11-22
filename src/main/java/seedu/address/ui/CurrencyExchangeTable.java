package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.expense.CurrencyExchangeRate;

public class CurrencyExchangeTable extends UiPart<Stage> {
    public static final String URL = "https://www.xe.com/currency/sgd-singapore-dollar.html";
    public static final String MESSAGE = "You can find some useful currency exchange rates here!\n"
            + "Please refer to this online website for more accurate and in-time exchange rates.\n"
            + "\n" + URL;
    private static final String FXML = "CurrencyExchangeTable.fxml";

    private TableView<CurrencyExchangeRate> tableView = new TableView<>();
    private final Logger logger = LogsCenter.getLogger(CurrencyExchangeTable.class);

    private TableColumn currency = new TableColumn("Currency");
    private TableColumn exchangeRate = new TableColumn("Exchange rate (1.00 SGD)");

    private ObservableList<CurrencyExchangeRate> list = FXCollections.observableArrayList();

    @FXML
    private Button copyButton;

    @javafx.fxml.FXML
    private Label label;

    /**
     * Creates a new CurrencyExchangeTable.
     *
     * @param root Stage to use as the root of the StatisticsWindow.
     */
    public CurrencyExchangeTable(Stage root) {
        super(FXML, root);
        label.setGraphic(tableView);
        label.setMinWidth(255);

        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.Q == event.getCode()) {
                getRoot().hide();
            }
        }
        );
    }

    /**
     * Creates a new HelpWindow.
     */
    public CurrencyExchangeTable() {
        this(new Stage());
    }

    /**
     * Constructs the exchange rate table.
     */
    public void constructTable() {
        currency.setCellValueFactory(new PropertyValueFactory<>("currency"));
        exchangeRate.setCellValueFactory(new PropertyValueFactory<>("exchangeRate"));
        list.addAll(new CurrencyExchangeRate("AED", String.format("%.8f", 3.673005 / 1.36745)),
                new CurrencyExchangeRate("AFN", String.format("%.8f", 76.897058 / 1.36745)),
                new CurrencyExchangeRate("ALL", String.format("%.8f", 106.399855 / 1.36745)),
                new CurrencyExchangeRate("AMD", String.format("%.8f", 493.150079 / 1.36745)),
                new CurrencyExchangeRate("ANG", String.format("%.8f", 1.794897 / 1.36745)),
                new CurrencyExchangeRate("AOA", String.format("%.8f", 662.941003 / 1.36745)),
                new CurrencyExchangeRate("ARS", String.format("%.8f", 78.3152 / 1.36745)),
                new CurrencyExchangeRate("AUD", String.format("%.8f", 1.42508 / 1.36745)),
                new CurrencyExchangeRate("AWG", String.format("%.8f", 1.8 / 1.36745)),
                new CurrencyExchangeRate("AZN", String.format("%.8f", 1.700615 / 1.36745)),
                new CurrencyExchangeRate("BAM", String.format("%.8f", 1.670957 / 1.36745)),
                new CurrencyExchangeRate("BBD", String.format("%.8f", 2.018907 / 1.36745)),
                new CurrencyExchangeRate("BDT", String.format("%.8f", 84.888486 / 1.36745)),
                new CurrencyExchangeRate("BGN", String.format("%.8f", 1.673433 / 1.36745)),
                new CurrencyExchangeRate("BHD", String.format("%.8f", 0.377075 / 1.36745)),
                new CurrencyExchangeRate("BIF", String.format("%.8f", 1940 / 1.36745)),
                new CurrencyExchangeRate("BMD", String.format("%.8f", 1 / 1.36745)),
                new CurrencyExchangeRate("BND", String.format("%.8f", 1.36626 / 1.36745)),
                new CurrencyExchangeRate("BOB", String.format("%.8f", 6.904488 / 1.36745)),
                new CurrencyExchangeRate("BRL", String.format("%.8f", 5.765572 / 1.36745)),
                new CurrencyExchangeRate("BSD", String.format("%.8f", 0.999927 / 1.36745)),
                new CurrencyExchangeRate("BTC", String.format("%.8f", 7.3494475e-5 / 1.36745)),
                new CurrencyExchangeRate("BTN", String.format("%.8f", 74.511335 / 1.36745)),
                new CurrencyExchangeRate("BWP", String.format("%.8f", 11.467127 / 1.36745)),
                new CurrencyExchangeRate("BYN", String.format("%.8f", 2.632181 / 1.36745)),
                new CurrencyExchangeRate("BYR", String.format("%.8f", 19600 / 1.36745)),
                new CurrencyExchangeRate("BZD", String.format("%.8f", 2.015575 / 1.36745)),
                new CurrencyExchangeRate("CAD", String.format("%.8f", 1.334925 / 1.36745)),
                new CurrencyExchangeRate("CDF", String.format("%.8f", 1965.999921 / 1.36745)),
                new CurrencyExchangeRate("CHF", String.format("%.8f", 0.91611 / 1.36745)),
                new CurrencyExchangeRate("CLF", String.format("%.8f", 0.028025 / 1.36745)),
                new CurrencyExchangeRate("CLP", String.format("%.8f", 773.295715 / 1.36745)),
                new CurrencyExchangeRate("CNY", String.format("%.8f", 6.714987 / 1.36745)),
                new CurrencyExchangeRate("COP", String.format("%.8f", 3855.5 / 1.36745)),
                new CurrencyExchangeRate("CRC", String.format("%.8f", 606.697224 / 1.36745)),
                new CurrencyExchangeRate("CUC", String.format("%.8f", 1 / 1.36745)),
                new CurrencyExchangeRate("CUP", String.format("%.8f", 26.5 / 1.36745)),
                new CurrencyExchangeRate("CVE", String.format("%.8f", 94.650032 / 1.36745)),
                new CurrencyExchangeRate("CZK", String.format("%.8f", 23.445002 / 1.36745)),
                new CurrencyExchangeRate("DJF", String.format("%.8f", 178.011679 / 1.36745)),
                new CurrencyExchangeRate("DKK", String.format("%.8f", 6.38402 / 1.36745)),
                new CurrencyExchangeRate("DOP", String.format("%.8f", 58.529885 / 1.36745)),
                new CurrencyExchangeRate("DZD", String.format("%.8f", 128.817921 / 1.36745)),
                new CurrencyExchangeRate("EGP", String.format("%.8f", 15.694803 / 1.36745)),
                new CurrencyExchangeRate("ERN", String.format("%.8f", 14.999699 / 1.36745)),
                new CurrencyExchangeRate("ETB", String.format("%.8f", 37.550172 / 1.36745)),
                new CurrencyExchangeRate("EUR", String.format("%.8f", 0.857415 / 1.36745)),
                new CurrencyExchangeRate("FJD", String.format("%.8f", 2.144979 / 1.36745)),
                new CurrencyExchangeRate("FKP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("GBP", String.format("%.8f", 0.774295 / 1.36745)),
                new CurrencyExchangeRate("GEL", String.format("%.8f", 3.2402 / 1.36745)),
                new CurrencyExchangeRate("GGP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("GHS", String.format("%.8f", 5.840363 / 1.36745)),
                new CurrencyExchangeRate("GIP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("GMD", String.format("%.8f", 51.75996 / 1.36745)),
                new CurrencyExchangeRate("GNF", String.format("%.8f", 9750.000196 / 1.36745)),
                new CurrencyExchangeRate("GTQ", String.format("%.8f", 7.794376 / 1.36745)),
                new CurrencyExchangeRate("GYD", String.format("%.8f", 209.16116 / 1.36745)),
                new CurrencyExchangeRate("HKD", String.format("%.8f", 7.75322 / 1.36745)),
                new CurrencyExchangeRate("HNL", String.format("%.8f", 24.550079 / 1.36745)),
                new CurrencyExchangeRate("HRK", String.format("%.8f", 6.4939 / 1.36745)),
                new CurrencyExchangeRate("HTG", String.format("%.8f", 63.214821 / 1.36745)),
                new CurrencyExchangeRate("HUF", String.format("%.8f", 315.239845 / 1.36745)),
                new CurrencyExchangeRate("IDR", String.format("%.8f", 14774.8 / 1.36745)),
                new CurrencyExchangeRate("ILS", String.format("%.8f", 3.42194 / 1.36745)),
                new CurrencyExchangeRate("IMP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("INR", String.format("%.8f", 74.40185 / 1.36745)),
                new CurrencyExchangeRate("IQD", String.format("%.8f", 1190 / 1.36745)),
                new CurrencyExchangeRate("IRR", String.format("%.8f", 42104.999943 / 1.36745)),
                new CurrencyExchangeRate("ISK", String.format("%.8f", 141.54015 / 1.36745)),
                new CurrencyExchangeRate("JEP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("JMD", String.format("%.8f", 145.768634 / 1.36745)),
                new CurrencyExchangeRate("JOD", String.format("%.8f", 0.708996 / 1.36745)),
                new CurrencyExchangeRate("JPY", String.format("%.8f", 104.637502 / 1.36745)),
                new CurrencyExchangeRate("KES", String.format("%.8f", 108.794649 / 1.36745)),
                new CurrencyExchangeRate("KGS", String.format("%.8f", 81.814885 / 1.36745)),
                new CurrencyExchangeRate("KHR", String.format("%.8f", 4060.000029 / 1.36745)),
                new CurrencyExchangeRate("KMF", String.format("%.8f", 421.874948 / 1.36745)),
                new CurrencyExchangeRate("KPW", String.format("%.8f", 900.017837 / 1.36745)),
                new CurrencyExchangeRate("KRW", String.format("%.8f", 1132.909655 / 1.36745)),
                new CurrencyExchangeRate("KWD", String.format("%.8f", 0.305796 / 1.36745)),
                new CurrencyExchangeRate("KYD", String.format("%.8f", 0.833244 / 1.36745)),
                new CurrencyExchangeRate("KZT", String.format("%.8f", 433.035752 / 1.36745)),
                new CurrencyExchangeRate("LAK", String.format("%.8f", 9260.000155 / 1.36745)),
                new CurrencyExchangeRate("LBP", String.format("%.8f", 1519.000112 / 1.36745)),
                new CurrencyExchangeRate("LKR", String.format("%.8f", 184.310863 / 1.36745)),
                new CurrencyExchangeRate("LRD", String.format("%.8f", 183.550338 / 1.36745)),
                new CurrencyExchangeRate("LSL", String.format("%.8f", 16.37979 / 1.36745)),
                new CurrencyExchangeRate("LTL", String.format("%.8f", 2.95274 / 1.36745)),
                new CurrencyExchangeRate("LVL", String.format("%.8f", 0.60489 / 1.36745)),
                new CurrencyExchangeRate("LYD", String.format("%.8f", 1.370072 / 1.36745)),
                new CurrencyExchangeRate("MAD", String.format("%.8f", 9.204938 / 1.36745)),
                new CurrencyExchangeRate("MDL", String.format("%.8f", 17.037461 / 1.36745)),
                new CurrencyExchangeRate("MGA", String.format("%.8f", 3924.999987 / 1.36745)),
                new CurrencyExchangeRate("MKD", String.format("%.8f", 52.639897 / 1.36745)),
                new CurrencyExchangeRate("MMK", String.format("%.8f", 1286.901071 / 1.36745)),
                new CurrencyExchangeRate("MNT", String.format("%.8f", 2863.90923 / 1.36745)),
                new CurrencyExchangeRate("MOP", String.format("%.8f", 7.983973 / 1.36745)),
                new CurrencyExchangeRate("MRO", String.format("%.8f", 357.000515 / 1.36745)),
                new CurrencyExchangeRate("MUR", String.format("%.8f", 39.954881 / 1.36745)),
                new CurrencyExchangeRate("MVR", String.format("%.8f", 15.402159 / 1.36745)),
                new CurrencyExchangeRate("MWK", String.format("%.8f", 754.999779 / 1.36745)),
                new CurrencyExchangeRate("MXN", String.format("%.8f", 21.357698 / 1.36745)),
                new CurrencyExchangeRate("MYR", String.format("%.8f", 4.157025 / 1.36745)),
                new CurrencyExchangeRate("MZN", String.format("%.8f", 73.159818 / 1.36745)),
                new CurrencyExchangeRate("NAD", String.format("%.8f", 16.379828 / 1.36745)),
                new CurrencyExchangeRate("NGN", String.format("%.8f", 381.00041 / 1.36745)),
                new CurrencyExchangeRate("NIO", String.format("%.8f", 34.698212 / 1.36745)),
                new CurrencyExchangeRate("NOK", String.format("%.8f", 9.534803 / 1.36745)),
                new CurrencyExchangeRate("NPR", String.format("%.8f", 119.218102 / 1.36745)),
                new CurrencyExchangeRate("NZD", String.format("%.8f", 1.513155 / 1.36745)),
                new CurrencyExchangeRate("OMR", String.format("%.8f", 0.385054 / 1.36745)),
                new CurrencyExchangeRate("PAB", String.format("%.8f", 0.999927 / 1.36745)),
                new CurrencyExchangeRate("PEN", String.format("%.8f", 3.611499 / 1.36745)),
                new CurrencyExchangeRate("PGK", String.format("%.8f", 3.505004 / 1.36745)),
                new CurrencyExchangeRate("PHP", String.format("%.8f", 48.4155 / 1.36745)),
                new CurrencyExchangeRate("PKR", String.format("%.8f", 160.703969 / 1.36745)),
                new CurrencyExchangeRate("PLN", String.format("%.8f", 3.9623 / 1.36745)),
                new CurrencyExchangeRate("PYG", String.format("%.8f", 7021.960982 / 1.36745)),
                new CurrencyExchangeRate("QAR", String.format("%.8f", 3.641012 / 1.36745)),
                new CurrencyExchangeRate("RON", String.format("%.8f", 4.1788 / 1.36745)),
                new CurrencyExchangeRate("RSD", String.format("%.8f", 100.824992 / 1.36745)),
                new CurrencyExchangeRate("RUB", String.format("%.8f", 78.975402 / 1.36745)),
                new CurrencyExchangeRate("RWF", String.format("%.8f", 970 / 1.36745)),
                new CurrencyExchangeRate("SAR", String.format("%.8f", 3.750324 / 1.36745)),
                new CurrencyExchangeRate("SBD", String.format("%.8f", 8.101947 / 1.36745)),
                new CurrencyExchangeRate("SCR", String.format("%.8f", 19.324765 / 1.36745)),
                new CurrencyExchangeRate("SDG", String.format("%.8f", 55.275018 / 1.36745)),
                new CurrencyExchangeRate("SEK", String.format("%.8f", 8.92047 / 1.36745)),
                new CurrencyExchangeRate("SGD", String.format("%.8f", 1.36745 / 1.36745)),
                new CurrencyExchangeRate("SHP", String.format("%.8f", 0.77446 / 1.36745)),
                new CurrencyExchangeRate("SLL", String.format("%.8f", 9929.999973 / 1.36745)),
                new CurrencyExchangeRate("SOS", String.format("%.8f", 583.000258 / 1.36745)),
                new CurrencyExchangeRate("SRD", String.format("%.8f", 14.153991 / 1.36745)),
                new CurrencyExchangeRate("STD", String.format("%.8f", 21031.906016 / 1.36745)),
                new CurrencyExchangeRate("SVC", String.format("%.8f", 8.749365 / 1.36745)),
                new CurrencyExchangeRate("SYP", String.format("%.8f", 512.61336 / 1.36745)),
                new CurrencyExchangeRate("SZL", String.format("%.8f", 16.379817 / 1.36745)),
                new CurrencyExchangeRate("THB", String.format("%.8f", 31.239631 / 1.36745)),
                new CurrencyExchangeRate("TJS", String.format("%.8f", 10.334089 / 1.36745)),
                new CurrencyExchangeRate("TMT", String.format("%.8f", 3.5 / 1.36745)),
                new CurrencyExchangeRate("TND", String.format("%.8f", 2.776498 / 1.36745)),
                new CurrencyExchangeRate("TOP", String.format("%.8f", 2.323499 / 1.36745)),
                new CurrencyExchangeRate("TRY", String.format("%.8f", 8.285205 / 1.36745)),
                new CurrencyExchangeRate("TTD", String.format("%.8f", 6.798891 / 1.36745)),
                new CurrencyExchangeRate("TWD", String.format("%.8f", 28.624983 / 1.36745)),
                new CurrencyExchangeRate("TZS", String.format("%.8f", 2318.825008 / 1.36745)),
                new CurrencyExchangeRate("UAH", String.format("%.8f", 28.443894 / 1.36745)),
                new CurrencyExchangeRate("UGX", String.format("%.8f", 3739.731821 / 1.36745)),
                new CurrencyExchangeRate("USD", String.format("%.8f", 1 / 1.36745)),
                new CurrencyExchangeRate("UYU", String.format("%.8f", 42.701957 / 1.36745)),
                new CurrencyExchangeRate("UZS", String.format("%.8f", 10364.00049 / 1.36745)),
                new CurrencyExchangeRate("VEF", String.format("%.8f", 9.987499 / 1.36745)),
                new CurrencyExchangeRate("VND", String.format("%.8f", 23180 / 1.36745)),
                new CurrencyExchangeRate("VUV", String.format("%.8f", 114.23611 / 1.36745)),
                new CurrencyExchangeRate("WST", String.format("%.8f", 2.630378 / 1.36745)),
                new CurrencyExchangeRate("XAF", String.format("%.8f", 560.409912 / 1.36745)),
                new CurrencyExchangeRate("XAG", String.format("%.8f", 0.042658 / 1.36745)),
                new CurrencyExchangeRate("XAU", String.format("%.8f", 0.000535 / 1.36745)),
                new CurrencyExchangeRate("XCD", String.format("%.8f", 2.70255 / 1.36745)),
                new CurrencyExchangeRate("XDR", String.format("%.8f", 0.707564 / 1.36745)),
                new CurrencyExchangeRate("XOF", String.format("%.8f", 554.498872 / 1.36745)),
                new CurrencyExchangeRate("XPF", String.format("%.8f", 102.649882 / 1.36745)),
                new CurrencyExchangeRate("YER", String.format("%.8f", 250.349783 / 1.36745)),
                new CurrencyExchangeRate("ZAR", String.format("%.8f", 16.386983 / 1.36745)),
                new CurrencyExchangeRate("ZMK", String.format("%.8f", 9001.201691 / 1.36745)),
                new CurrencyExchangeRate("ZMW", String.format("%.8f", 20.554898 / 1.36745)),
                new CurrencyExchangeRate("ZWL", String.format("%.8f", 321.999941 / 1.36745)));
        tableView.setItems(list);
        tableView.getColumns().addAll(currency, exchangeRate);
    }

    /**
     * Shows the currency exchange rate table window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        constructTable();
        logger.fine("Showing command currency exchange rates!");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(URL);
        clipboard.setContent(url);
    }

}
