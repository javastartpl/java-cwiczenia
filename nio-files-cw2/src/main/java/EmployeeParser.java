import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;

class EmployeeParser {

    public static void main(String[] args) {
        try {
            List<Employee> employees = readEmployeeData(Path.of("input.csv"));
            OptionalDouble avgSalary = getAvgSalary(employees);
            avgSalary.ifPresentOrElse(
                    avg -> saveAvgSalary(avg, Path.of("avg.txt")),
                    () -> System.out.println("Brak danych w pliku wejściowym")
            );
        } catch (IOException e) {
            System.err.println("Nie udało się odczytać pliku wejściowego. Upewnij się, że plik istnieje.");
        }
    }

    private static List<Employee> readEmployeeData(Path filePath) throws IOException {
        return Files.readAllLines(filePath)
                .stream()
                .skip(1)
                .map(EmployeeParser::textToEmployee)
                .toList();
    }

    private static OptionalDouble getAvgSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average();
    }

    private static void saveAvgSalary(double avgSalary, Path filePath) {
        LocalDateTime now = LocalDateTime.now();
        String lineToSave = STR."\{now};\{avgSalary}\n";
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
            Files.writeString(filePath, lineToSave, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać danych do pliku");
        }
    }

    private static Employee textToEmployee(String text) {
        String[] split = text.split(";");
        return new Employee(split[0], split[1], Double.parseDouble(split[2]));
    }

    private record Employee(String firstName, String lastName, double salary) {
    }
}
