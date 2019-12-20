package by.epam.xml_parser.entity;

import java.util.Objects;

public class CallPrices {
    private double callsInOtherNetworks;
    private double callsInsideNetworks;
    private double callsToLandLine;

    private CallPrices(Builder builder) {
        this.callsInOtherNetworks = builder.callsInOtherNetworks;
        this.callsInsideNetworks = builder.callsInsideNetworks;
        this.callsToLandLine = builder.callsToLandLine;
    }

    public static class Builder {
        private double callsInOtherNetworks;
        private double callsInsideNetworks;
        private double callsToLandLine;

        private CallPrices newCallPrices;

        public Builder() {
        }

        public Builder withCallsInOtherNetworks(double callsInOtherNetworks) {
            this.callsInOtherNetworks = callsInOtherNetworks;
            return this;
        }

        public Builder withCallsInsideNetworks(double callsInsideNetworks) {
            this.callsInsideNetworks = callsInsideNetworks;
            return this;
        }

        public Builder withCallsToLandLine(double callsToLandLine) {
            this.callsToLandLine = callsToLandLine;
            return this;
        }

        public CallPrices build() {
            newCallPrices = new CallPrices(this);
            return newCallPrices;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices that = (CallPrices) o;
        return Double.compare(that.callsInOtherNetworks, callsInOtherNetworks) == 0 &&
                Double.compare(that.callsInsideNetworks, callsInsideNetworks) == 0 &&
                Double.compare(that.callsToLandLine, callsToLandLine) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(callsInOtherNetworks, callsInsideNetworks, callsToLandLine);
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "callsInOtherNetworks=" + callsInOtherNetworks +
                ", callsInsideNetworks=" + callsInsideNetworks +
                ", callsToLandLine=" + callsToLandLine +
                '}';
    }
}
