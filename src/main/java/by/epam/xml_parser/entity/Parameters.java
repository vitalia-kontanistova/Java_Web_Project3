package by.epam.xml_parser.entity;

import java.util.Objects;

public class Parameters {
    private int favouriteNumbers;
    private Tariffication tariffication;
    private double connectionPayment;

    private Parameters() {
    }

    public static class Builder {
        private Parameters newParameters;

        public Builder() {
            newParameters = new Parameters();
        }

        public Builder setFavouriteNumbers(int favouriteNumbers) {
            newParameters.favouriteNumbers = favouriteNumbers;
            return this;
        }

        public Builder setTariffication(Tariffication tariffication) {
            newParameters.tariffication = tariffication;
            return this;
        }

        public Builder setConnectionPayment(double connectionPayment) {
            newParameters.connectionPayment = connectionPayment;
            return this;
        }

        public Parameters build() {
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