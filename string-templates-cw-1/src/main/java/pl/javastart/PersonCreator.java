package pl.javastart;

import java.util.Scanner;

class PersonCreator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Person person = createPerson();
        String outputFormat = readOutputFormat();
        printFormatted(person, outputFormat);
    }

    private static Person createPerson() {
        System.out.println(">Wprowadź informacje o osobie");
        System.out.println(">Imię:");
        String firstName = scanner.nextLine();
        System.out.println(">Nazwisko:");
        String lastName = scanner.nextLine();
        System.out.println(">Wiek:");
        int age = scanner.nextInt();
        scanner.nextLine();
        return new Person(firstName, lastName, age);
    }

    private static String readOutputFormat() {
        System.out.println(">Wybierz format danych: JSON / XML");
        return scanner.nextLine();
    }

    private static void printFormatted(Person person, String format) {
        String output = switch (format) {
            case "JSON" -> person.toJson();
            case "XML" -> person.toXml();
            default -> person.toString();
        };
        System.out.println(STR.">Oto obiekt w formacie \{format}:");
        System.out.println(output);
    }
}
