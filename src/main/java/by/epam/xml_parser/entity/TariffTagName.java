package by.epam.xml_parser.entity;

public enum TariffTagName {
    TARIFFS, TARIFF, PAYROLL, SMS_PRICE,
    CALL_PRICES, CALLS_IN_OTHER_NETWORKS, CALLS_INSIDE_NETWORKS, CALLS_TO_LANDLINE,
    PARAMETERS, FAVOURITE_NUMBERS, TARIFFICATION, CONNECTION_PAYMENT,
    WRONG_REQUEST;

    public static TariffTagName getElementTagName(String element) {
        switch (element) {
            case "tariffs":
                return TARIFFS;
            case "tariff":
                return TARIFF;
            case "payroll":
                return PAYROLL;
            case "callPrices":
                return CALL_PRICES;
            case "smsPrice":
                return SMS_PRICE;
            case "parameters":
                return PARAMETERS;
            case "callsInOtherNetworks":
                return CALLS_IN_OTHER_NETWORKS;
            case "callsInsideNetworks":
                return CALLS_INSIDE_NETWORKS;
            case "callsToLandLine":
                return CALLS_TO_LANDLINE;
            case "favouriteNumbers":
                return FAVOURITE_NUMBERS;
            case "tariffication":
                return TARIFFICATION;
            case "connectionPayment":
                return CONNECTION_PAYMENT;

            default:
                return WRONG_REQUEST;
        }
    }
}
