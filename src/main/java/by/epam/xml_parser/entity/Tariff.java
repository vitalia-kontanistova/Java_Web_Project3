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

    private Tariff() {
    }

    public static class Builder {
        private Tariff newTariff;

        public Builder() {
            newTariff = new Tariff();
        }

        public Builder setId(int id) {
            newTariff.id = id;
            return this;
        }

        public Builder setName(String name) {
            newTariff.name = name;
            return this;
        }

        public Builder setOperatorName(String operatorName) {
            newTariff.operatorName = operatorName;
            return this;
        }

        public Builder setPayroll(double payroll) {
            newTariff.payroll = payroll;
            return this;
        }

        public Builder setCallPrices(CallPrices callPrices) {
            newTariff.callPrices = callPrices;
            return this;
        }

        public Builder setSmsPrice(double smsPrice) {
            newTariff.smsPrice = smsPrice;
            return this;
        }

        public Builder setParameters(Parameters parameters) {
            newTariff.parameters = parameters;
            return this;
        }

        public Tariff build() {
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