package seedu.address.model.expense;

import java.util.HashMap;


public class CurrencyConverter {
    private HashMap<Currency, ExchangeRate> usdToExchangeRates;

    /**
     * Construct a CurrencyConverter with a HashMap to store all the exchange rates from USD.
     * The data are read from txt file in doc folder, which can be update manually.
     */
    public CurrencyConverter() {
        usdToExchangeRates = new HashMap<>();
        loadExchangeRates();
    }

    /**
     * Calculate the exchange rate from one currency to another, using USD as a base.
     * @param fromCurr the currency being converted.
     * @param toCurr target currency.
     * @return Exchange rate from 'fromCurr' to 'toCurr'.
     */
    public ExchangeRate convert(Currency fromCurr, Currency toCurr) {
        ExchangeRate usdToFrom = usdToExchangeRates.get(fromCurr);
        ExchangeRate usdToTo = usdToExchangeRates.get(toCurr);
        return new ExchangeRate(usdToTo.getRate() / usdToFrom.getRate());
    }

    /**
     * Added by Zhang Yuanxing on 2020-10-30. Exchange Rates are get from currencylayer website.
     */
    private void loadExchangeRates() {
        usdToExchangeRates.put(new Currency("AED"), new ExchangeRate(3.673005));
        usdToExchangeRates.put(new Currency("AFN"), new ExchangeRate(76.897058));
        usdToExchangeRates.put(new Currency("ALL"), new ExchangeRate(106.399855));
        usdToExchangeRates.put(new Currency("AMD"), new ExchangeRate(493.150079));
        usdToExchangeRates.put(new Currency("ANG"), new ExchangeRate(1.794897));
        usdToExchangeRates.put(new Currency("AOA"), new ExchangeRate(662.941003));
        usdToExchangeRates.put(new Currency("ARS"), new ExchangeRate(78.3152));
        usdToExchangeRates.put(new Currency("AUD"), new ExchangeRate(1.42508));
        usdToExchangeRates.put(new Currency("AWG"), new ExchangeRate(1.8));
        usdToExchangeRates.put(new Currency("AZN"), new ExchangeRate(1.700615));
        usdToExchangeRates.put(new Currency("BAM"), new ExchangeRate(1.670957));
        usdToExchangeRates.put(new Currency("BBD"), new ExchangeRate(2.018907));
        usdToExchangeRates.put(new Currency("BDT"), new ExchangeRate(84.888486));
        usdToExchangeRates.put(new Currency("BGN"), new ExchangeRate(1.673433));
        usdToExchangeRates.put(new Currency("BHD"), new ExchangeRate(0.377075));
        usdToExchangeRates.put(new Currency("BIF"), new ExchangeRate(1940));
        usdToExchangeRates.put(new Currency("BMD"), new ExchangeRate(1));
        usdToExchangeRates.put(new Currency("BND"), new ExchangeRate(1.36626));
        usdToExchangeRates.put(new Currency("BOB"), new ExchangeRate(6.904488));
        usdToExchangeRates.put(new Currency("BRL"), new ExchangeRate(5.765572));
        usdToExchangeRates.put(new Currency("BSD"), new ExchangeRate(0.999927));
        usdToExchangeRates.put(new Currency("BTC"), new ExchangeRate(0.000073494475));
        usdToExchangeRates.put(new Currency("BTN"), new ExchangeRate(74.511335));
        usdToExchangeRates.put(new Currency("BWP"), new ExchangeRate(11.467127));
        usdToExchangeRates.put(new Currency("BYN"), new ExchangeRate(2.632181));
        usdToExchangeRates.put(new Currency("BYR"), new ExchangeRate(19600));
        usdToExchangeRates.put(new Currency("BZD"), new ExchangeRate(2.015575));
        usdToExchangeRates.put(new Currency("CAD"), new ExchangeRate(1.334925));
        usdToExchangeRates.put(new Currency("CDF"), new ExchangeRate(1965.999921));
        usdToExchangeRates.put(new Currency("CHF"), new ExchangeRate(0.91611));
        usdToExchangeRates.put(new Currency("CLF"), new ExchangeRate(0.028025));
        usdToExchangeRates.put(new Currency("CLP"), new ExchangeRate(773.295715));
        usdToExchangeRates.put(new Currency("CNY"), new ExchangeRate(6.714987));
        usdToExchangeRates.put(new Currency("COP"), new ExchangeRate(3855.5));
        usdToExchangeRates.put(new Currency("CRC"), new ExchangeRate(606.697224));
        usdToExchangeRates.put(new Currency("CUC"), new ExchangeRate(1));
        usdToExchangeRates.put(new Currency("CUP"), new ExchangeRate(26.5));
        usdToExchangeRates.put(new Currency("CVE"), new ExchangeRate(94.650032));
        usdToExchangeRates.put(new Currency("CZK"), new ExchangeRate(23.445002));
        usdToExchangeRates.put(new Currency("DJF"), new ExchangeRate(178.011679));
        usdToExchangeRates.put(new Currency("DKK"), new ExchangeRate(6.38402));
        usdToExchangeRates.put(new Currency("DOP"), new ExchangeRate(58.529885));
        usdToExchangeRates.put(new Currency("DZD"), new ExchangeRate(128.817921));
        usdToExchangeRates.put(new Currency("EGP"), new ExchangeRate(15.694803));
        usdToExchangeRates.put(new Currency("ERN"), new ExchangeRate(14.999699));
        usdToExchangeRates.put(new Currency("ETB"), new ExchangeRate(37.550172));
        usdToExchangeRates.put(new Currency("EUR"), new ExchangeRate(0.857415));
        usdToExchangeRates.put(new Currency("FJD"), new ExchangeRate(2.144979));
        usdToExchangeRates.put(new Currency("FKP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("GBP"), new ExchangeRate(0.774295));
        usdToExchangeRates.put(new Currency("GEL"), new ExchangeRate(3.2402));
        usdToExchangeRates.put(new Currency("GGP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("GHS"), new ExchangeRate(5.840363));
        usdToExchangeRates.put(new Currency("GIP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("GMD"), new ExchangeRate(51.75996));
        usdToExchangeRates.put(new Currency("GNF"), new ExchangeRate(9750.000196));
        usdToExchangeRates.put(new Currency("GTQ"), new ExchangeRate(7.794376));
        usdToExchangeRates.put(new Currency("GYD"), new ExchangeRate(209.16116));
        usdToExchangeRates.put(new Currency("HKD"), new ExchangeRate(7.75322));
        usdToExchangeRates.put(new Currency("HNL"), new ExchangeRate(24.550079));
        usdToExchangeRates.put(new Currency("HRK"), new ExchangeRate(6.4939));
        usdToExchangeRates.put(new Currency("HTG"), new ExchangeRate(63.214821));
        usdToExchangeRates.put(new Currency("HUF"), new ExchangeRate(315.239845));
        usdToExchangeRates.put(new Currency("IDR"), new ExchangeRate(14774.8));
        usdToExchangeRates.put(new Currency("ILS"), new ExchangeRate(3.42194));
        usdToExchangeRates.put(new Currency("IMP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("INR"), new ExchangeRate(74.40185));
        usdToExchangeRates.put(new Currency("IQD"), new ExchangeRate(1190));
        usdToExchangeRates.put(new Currency("IRR"), new ExchangeRate(42104.999943));
        usdToExchangeRates.put(new Currency("ISK"), new ExchangeRate(141.54015));
        usdToExchangeRates.put(new Currency("JEP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("JMD"), new ExchangeRate(145.768634));
        usdToExchangeRates.put(new Currency("JOD"), new ExchangeRate(0.708996));
        usdToExchangeRates.put(new Currency("JPY"), new ExchangeRate(104.637502));
        usdToExchangeRates.put(new Currency("KES"), new ExchangeRate(108.794649));
        usdToExchangeRates.put(new Currency("KGS"), new ExchangeRate(81.814885));
        usdToExchangeRates.put(new Currency("KHR"), new ExchangeRate(4060.000029));
        usdToExchangeRates.put(new Currency("KMF"), new ExchangeRate(421.874948));
        usdToExchangeRates.put(new Currency("KPW"), new ExchangeRate(900.017837));
        usdToExchangeRates.put(new Currency("KRW"), new ExchangeRate(1132.909655));
        usdToExchangeRates.put(new Currency("KWD"), new ExchangeRate(0.305796));
        usdToExchangeRates.put(new Currency("KYD"), new ExchangeRate(0.833244));
        usdToExchangeRates.put(new Currency("KZT"), new ExchangeRate(433.035752));
        usdToExchangeRates.put(new Currency("LAK"), new ExchangeRate(9260.000155));
        usdToExchangeRates.put(new Currency("LBP"), new ExchangeRate(1519.000112));
        usdToExchangeRates.put(new Currency("LKR"), new ExchangeRate(184.310863));
        usdToExchangeRates.put(new Currency("LRD"), new ExchangeRate(183.550338));
        usdToExchangeRates.put(new Currency("LSL"), new ExchangeRate(16.37979));
        usdToExchangeRates.put(new Currency("LTL"), new ExchangeRate(2.95274));
        usdToExchangeRates.put(new Currency("LVL"), new ExchangeRate(0.60489));
        usdToExchangeRates.put(new Currency("LYD"), new ExchangeRate(1.370072));
        usdToExchangeRates.put(new Currency("MAD"), new ExchangeRate(9.204938));
        usdToExchangeRates.put(new Currency("MDL"), new ExchangeRate(17.037461));
        usdToExchangeRates.put(new Currency("MGA"), new ExchangeRate(3924.999987));
        usdToExchangeRates.put(new Currency("MKD"), new ExchangeRate(52.639897));
        usdToExchangeRates.put(new Currency("MMK"), new ExchangeRate(1286.901071));
        usdToExchangeRates.put(new Currency("MNT"), new ExchangeRate(2863.90923));
        usdToExchangeRates.put(new Currency("MOP"), new ExchangeRate(7.983973));
        usdToExchangeRates.put(new Currency("MRO"), new ExchangeRate(357.000515));
        usdToExchangeRates.put(new Currency("MUR"), new ExchangeRate(39.954881));
        usdToExchangeRates.put(new Currency("MVR"), new ExchangeRate(15.402159));
        usdToExchangeRates.put(new Currency("MWK"), new ExchangeRate(754.999779));
        usdToExchangeRates.put(new Currency("MXN"), new ExchangeRate(21.357698));
        usdToExchangeRates.put(new Currency("MYR"), new ExchangeRate(4.157025));
        usdToExchangeRates.put(new Currency("MZN"), new ExchangeRate(73.159818));
        usdToExchangeRates.put(new Currency("NAD"), new ExchangeRate(16.379828));
        usdToExchangeRates.put(new Currency("NGN"), new ExchangeRate(381.00041));
        usdToExchangeRates.put(new Currency("NIO"), new ExchangeRate(34.698212));
        usdToExchangeRates.put(new Currency("NOK"), new ExchangeRate(9.534803));
        usdToExchangeRates.put(new Currency("NPR"), new ExchangeRate(119.218102));
        usdToExchangeRates.put(new Currency("NZD"), new ExchangeRate(1.513155));
        usdToExchangeRates.put(new Currency("OMR"), new ExchangeRate(0.385054));
        usdToExchangeRates.put(new Currency("PAB"), new ExchangeRate(0.999927));
        usdToExchangeRates.put(new Currency("PEN"), new ExchangeRate(3.611499));
        usdToExchangeRates.put(new Currency("PGK"), new ExchangeRate(3.505004));
        usdToExchangeRates.put(new Currency("PHP"), new ExchangeRate(48.4155));
        usdToExchangeRates.put(new Currency("PKR"), new ExchangeRate(160.703969));
        usdToExchangeRates.put(new Currency("PLN"), new ExchangeRate(3.9623));
        usdToExchangeRates.put(new Currency("PYG"), new ExchangeRate(7021.960982));
        usdToExchangeRates.put(new Currency("QAR"), new ExchangeRate(3.641012));
        usdToExchangeRates.put(new Currency("RON"), new ExchangeRate(4.1788));
        usdToExchangeRates.put(new Currency("RSD"), new ExchangeRate(100.824992));
        usdToExchangeRates.put(new Currency("RUB"), new ExchangeRate(78.975402));
        usdToExchangeRates.put(new Currency("RWF"), new ExchangeRate(970));
        usdToExchangeRates.put(new Currency("SAR"), new ExchangeRate(3.750324));
        usdToExchangeRates.put(new Currency("SBD"), new ExchangeRate(8.101947));
        usdToExchangeRates.put(new Currency("SCR"), new ExchangeRate(19.324765));
        usdToExchangeRates.put(new Currency("SDG"), new ExchangeRate(55.275018));
        usdToExchangeRates.put(new Currency("SEK"), new ExchangeRate(8.92047));
        usdToExchangeRates.put(new Currency("SGD"), new ExchangeRate(1.36745));
        usdToExchangeRates.put(new Currency("SHP"), new ExchangeRate(0.77446));
        usdToExchangeRates.put(new Currency("SLL"), new ExchangeRate(9929.999973));
        usdToExchangeRates.put(new Currency("SOS"), new ExchangeRate(583.000258));
        usdToExchangeRates.put(new Currency("SRD"), new ExchangeRate(14.153991));
        usdToExchangeRates.put(new Currency("STD"), new ExchangeRate(21031.906016));
        usdToExchangeRates.put(new Currency("SVC"), new ExchangeRate(8.749365));
        usdToExchangeRates.put(new Currency("SYP"), new ExchangeRate(512.61336));
        usdToExchangeRates.put(new Currency("SZL"), new ExchangeRate(16.379817));
        usdToExchangeRates.put(new Currency("THB"), new ExchangeRate(31.239631));
        usdToExchangeRates.put(new Currency("TJS"), new ExchangeRate(10.334089));
        usdToExchangeRates.put(new Currency("TMT"), new ExchangeRate(3.5));
        usdToExchangeRates.put(new Currency("TND"), new ExchangeRate(2.776498));
        usdToExchangeRates.put(new Currency("TOP"), new ExchangeRate(2.323499));
        usdToExchangeRates.put(new Currency("TRY"), new ExchangeRate(8.285205));
        usdToExchangeRates.put(new Currency("TTD"), new ExchangeRate(6.798891));
        usdToExchangeRates.put(new Currency("TWD"), new ExchangeRate(28.624983));
        usdToExchangeRates.put(new Currency("TZS"), new ExchangeRate(2318.825008));
        usdToExchangeRates.put(new Currency("UAH"), new ExchangeRate(28.443894));
        usdToExchangeRates.put(new Currency("UGX"), new ExchangeRate(3739.731821));
        usdToExchangeRates.put(new Currency("USD"), new ExchangeRate(1));
        usdToExchangeRates.put(new Currency("UYU"), new ExchangeRate(42.701957));
        usdToExchangeRates.put(new Currency("UZS"), new ExchangeRate(10364.00049));
        usdToExchangeRates.put(new Currency("VEF"), new ExchangeRate(9.987499));
        usdToExchangeRates.put(new Currency("VND"), new ExchangeRate(23180));
        usdToExchangeRates.put(new Currency("VUV"), new ExchangeRate(114.23611));
        usdToExchangeRates.put(new Currency("WST"), new ExchangeRate(2.630378));
        usdToExchangeRates.put(new Currency("XAF"), new ExchangeRate(560.409912));
        usdToExchangeRates.put(new Currency("XAG"), new ExchangeRate(0.042658));
        usdToExchangeRates.put(new Currency("XAU"), new ExchangeRate(0.000535));
        usdToExchangeRates.put(new Currency("XCD"), new ExchangeRate(2.70255));
        usdToExchangeRates.put(new Currency("XDR"), new ExchangeRate(0.707564));
        usdToExchangeRates.put(new Currency("XOF"), new ExchangeRate(554.498872));
        usdToExchangeRates.put(new Currency("XPF"), new ExchangeRate(102.649882));
        usdToExchangeRates.put(new Currency("YER"), new ExchangeRate(250.349783));
        usdToExchangeRates.put(new Currency("ZAR"), new ExchangeRate(16.386983));
        usdToExchangeRates.put(new Currency("ZMK"), new ExchangeRate(9001.201691));
        usdToExchangeRates.put(new Currency("ZMW"), new ExchangeRate(20.554898));
        usdToExchangeRates.put(new Currency("ZWL"), new ExchangeRate(321.999941));
    }

    public boolean isValidCurrency(Currency currency) {
        return usdToExchangeRates.containsKey(currency);
    }

}



