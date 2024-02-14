package pl.javastart.weather;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

import static pl.javastart.weather.DailyTemperatureReader.DATE_FORMATTER;

class WeatherApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String measurementsFile = "weather.csv";
        try {
            Map<LocalDate, DailyTemperature> measurements = DailyTemperatureReader.readMeasurements(measurementsFile);
            LocalDate date = readDate();
            DailyTemperature.Unit unit = readUnit();
            DailyTemperature dailyTemperature = measurements.get(date);
            printDailyTemperature(dailyTemperature, unit);
        } catch (FileNotFoundException e) {
            System.err.println("Nie udało się odczytać pliku " + measurementsFile);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Błędny format daty");
        }
    }

    private static LocalDate readDate() {
        System.out.println("Dane z którego dnia Cię interesują? (format yyyy-MM-dd)");
        String input = SCANNER.nextLine();
        return LocalDate.parse(input, DATE_FORMATTER);
    }

    private static DailyTemperature.Unit readUnit() {
        System.out.println("W jakiej jednostce wyświetlić pomiary? (C / F)?");
        String input = SCANNER.nextLine();
        return DailyTemperature.Unit.fromUnitSign(input);
    }

    private static void printDailyTemperature(DailyTemperature dailyTemperature, DailyTemperature.Unit unit) {
        if (dailyTemperature == null) {
            System.out.println("Brak pomiaru dla wskazanej daty");
        } else {
            double minTemp = dailyTemperature.minTempAs(unit);
            double maxTemp = dailyTemperature.maxTempAs(unit);
            String template = "Minimalna temperatura dnia %s wynosiła %.2f°%s, a maksymalna %.2f°%s";
            System.out.println("Odnaleziony pomiar");
            System.out.printf(
                    template,
                    dailyTemperature.date(),
                    minTemp,
                    unit.getUnitSign(),
                    maxTemp,
                    unit.getUnitSign()
            );
        }
    }
}
