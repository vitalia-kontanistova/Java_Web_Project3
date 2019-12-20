package by.epam.xml_parser.entity;

import java.util.Objects;

public class Tariff {
    private int id;
    private String name;
    private String operatorName;
    private double payroll;
    private CallPrices callPrices;
    private double smsPrice;
    private Parameters parameters;


    private Tariff(Tariff.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.operatorName = builder.operatorName;
        this.payroll = builder.payroll;
        this.callPrices = builder.callPrices;
        this.smsPrice = builder.smsPrice;
        this.parameters = builder.parameters;
    }

    public static class Builder {
        private int id;
        private String name;
        private String operatorName;
        private double payroll;
        private CallPrices callPrices;
        private double smsPrice;
        private Parameters parameters;

        private Tariff newTariff;

        public Builder() {
        }

        public Tariff.Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Tariff.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Tariff.Builder withOperatorName(String operatorName) {
            this.operatorName = operatorName;
            return this;
        }

        public Tariff.Builder withPayroll(double payroll) {
            this.payroll = payroll;
            return this;
        }

        public Tariff.Builder withCallPrices(CallPrices callPrices) {
            this.callPrices = callPrices;
            return this;
        }

        public Tariff.Builder withSmsPrice(double smsPrice) {
            this.smsPrice = smsPrice;
            return this;
        }

        public Tariff.Builder withParameters(Parameters parameters) {
            this.parameters = parameters;
            return this;
        }

        public Tariff build() {
            newTariff = new Tariff(this);
            return newTariff;
        }

        public Tariff getTariff() {
            return newTariff;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return id == tariff.id &&
                Double.compare(tariff.payroll, payroll) == 0 &&
                Double.compare(tariff.smsPrice, smsPrice) == 0 &&
                Objects.equals(name, tariff.name) &&
                Objects.equals(operatorName, tariff.operatorName) &&
                Objects.equals(callPrices, tariff.callPrices) &&
                Objects.equals(parameters, tariff.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, operatorName, payroll, callPrices, smsPrice, parameters);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name=" + name +
                ", operatorName=" + operatorName +
                ", payroll=" + payroll +
                ", \n\t" + callPrices +
                ", smsPrice=" + smsPrice +
                ", \n\t" + parameters +
                "}";
    }
}