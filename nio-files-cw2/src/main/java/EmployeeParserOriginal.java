import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

class EmployeeParserOriginal {

    public static void main(String[] args) {
        try {
            List<Employee> employees = readEmployeeData("input.csv");
            OptionalDouble avgSalary = getAvgSalary(employees);
            avgSalary.ifPresentOrElse(
                    avg -> saveAvgSalary(avg, "avg.txt"),
                    () -> System.out.println("Brak danych w pliku wejściowym")
            );
        } catch (FileNotFoundException e) {
            System.err.println("Nie udało się odczytać pliku wejściowego. Upewnij się, że plik istnieje.");
        }
    }

    private static List<Employee> readEmployeeData(String fileName) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            return scanner.tokens()
                    .skip(1)
                    .map(EmployeeParserOriginal::textToEmployee)
                    .toList();
        }
    }

    private static OptionalDouble getAvgSalary(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(Employee::salary)
                .average();
    }

    private static void saveAvgSalary(double avgSalary, String fileName) {
        try (
                var fileWriter = new FileWriter(fileName, true);
                var bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            LocalDateTime now = LocalDateTime.now();
            bufferedWriter.write(now.toString());
            bufferedWriter.write(";");
            bufferedWriter.write(Double.toString(avgSalary));
            bufferedWriter.write("\n");
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać danych do pliku");
        }
    }

    private static Employee textToEmployee(String text) {
        String[] split = text.split(";");
        return new Employee(split[0], split[1], Double.parseDouble(split[2]));
    }

    private record Employee(String firstName, String lastName, double salary) { }
}
