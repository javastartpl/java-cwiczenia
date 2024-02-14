package pl.javastart.weather;

import java.time.LocalDate;
import java.util.Objects;

record DailyTemperature(
        LocalDate date,
        double minTemp,
        double maxTemp,
        Unit unit
) {
    DailyTemperature {
        Objects.requireNonNull(date);
        Objects.requireNonNull(unit);
        if (maxTemp < minTemp) {
            throw new IllegalArgumentException("maxTemp < minTemp");
        }
    }

    double minTempAs(Unit targetUnit) {
        return Unit.convert(unit, targetUnit, minTemp);
    }

    double maxTempAs(Unit targetUnit) {
        return Unit.convert(unit, targetUnit, maxTemp);
    }

    enum Unit {
        CELSIUS('C'),
        FAHRENHEIT('F');

        private final char unitSign;

        Unit(char unitSign) {
            this.unitSign = unitSign;
        }

        public char getUnitSign() {
            return unitSign;
        }

        static Unit fromUnitSign(String sign) {
            return switch (sign) {
                case "C" -> Unit.CELSIUS;
                case "F" -> Unit.FAHRENHEIT;
                default -> throw new IllegalArgumentException("Nieobsługiwana jednostka temperatury");
            };
        }

        static double convert(Unit from, Unit to, double value) {
            if (from == CELSIUS && to == FAHRENHEIT) {
                return value * 1.8 + 32;
            }
            if (from == FAHRENHEIT && to == CELSIUS) {
                return (value - 32) / 1.8;
            } else {
                return value;
            }
        }
    }
}
