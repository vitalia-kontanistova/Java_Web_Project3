package by.epam.xml_parser.entity;

import java.util.Objects;

public class CallPrices {
    private double callsInOtherNetworks;
    private double callsInsideNetworks;
    private double callsToLandLine;

    private CallPrices() {
    }

    public static class Builder {
        private CallPrices newCallPrices;

        public Builder() {
            newCallPrices = new CallPrices();
        }

        public Builder setCallsInOtherNetworks(double callsInOtherNetworks) {
            newCallPrices.callsInOtherNetworks = callsInOtherNetworks;
            return this;
        }

        public Builder setCallsInsideNetworks(double callsInsideNetworks) {
            newCallPrices.callsInsideNetworks = callsInsideNetworks;
            return this;
        }

        public Builder setCallsToLandLine(double callsToLandLine) {
            newCallPrices.callsToLandLine = callsToLandLine;
            return this;
        }

        public CallPrices build() {
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
