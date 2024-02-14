package pl.javastart.weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class DailyTemperatureReader {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String SEPARATOR = ";";

    static Map<LocalDate, DailyTemperature> readMeasurements(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        Map<LocalDate, DailyTemperature> result = new HashMap<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            DailyTemperature dailyTemperature = createDailyTemperature(nextLine);
            result.put(dailyTemperature.date(), dailyTemperature);
        }
        return result;
    }

    private static DailyTemperature createDailyTemperature(String text) {
        String[] tokens = text.split(SEPARATOR);
        try {
            return new DailyTemperature(
                    LocalDate.parse(tokens[0], DATE_FORMATTER),
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]),
                    DailyTemperature.Unit.valueOf(tokens[3])
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Nie można przetworzyć wiersza: " + text);
        }
    }
}
