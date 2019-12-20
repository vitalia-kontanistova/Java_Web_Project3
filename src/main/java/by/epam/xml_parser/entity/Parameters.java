package by.epam.xml_parser.entity;

import java.util.Objects;

public class Parameters {
    private int favouriteNumbers;
    private Tariffication tariffication;
    private double connectionPayment;

    private Parameters(Builder builder) {
        this.connectionPayment = builder.connectionPayment;
        this.favouriteNumbers = builder.favouriteNumbers;
        this.tariffication = builder.tariffication;
    }

    public static class Builder {
        private int favouriteNumbers;
        private Tariffication tariffication;
        private double connectionPayment;

        private Parameters newParameters;

        public Builder() {
        }

        public Builder withFavouriteNumbers(int favouriteNumbers) {
            this.favouriteNumbers = favouriteNumbers;
            return this;
        }

        public Builder withTariffication(Tariffication tariffication) {
            this.tariffication = tariffication;
            return this;
        }

        public Builder withConnectionPayment(double connectionPayment) {
            this.connectionPayment = connectionPayment;
            return this;
        }

        public Parameters build() {
            newParameters = new Parameters(this);
            return newParameters;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return favouriteNumbers == that.favouriteNumbers &&
                Double.compare(that.connectionPayment, connectionPayment) == 0 &&
                tariffication == that.tariffication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(favouriteNumbers, tariffication, connectionPayment);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "favouriteNumbers=" + favouriteNumbers +
                ", tariffication=" + tariffication +
                ", connectionPayment=" + connectionPayment +
                '}';
    }
}