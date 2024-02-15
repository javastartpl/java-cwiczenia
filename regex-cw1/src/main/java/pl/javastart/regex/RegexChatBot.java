package pl.javastart.regex;

import java.util.Scanner;

class RegexChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cześć, jestem javowym czatbotem.");
        System.out.println("W czym mogę pomóc? (\"koniec\" przerywa program)");
        boolean close = false;
        do {
            String inputText = scanner.nextLine();
            try {
                String result = QuestionEvaluatorFacade.evaluate(inputText);
                System.out.println(result);
            } catch (IllegalArgumentException e) {
                if (inputText.equalsIgnoreCase("koniec")) {
                    close = true;
                } else {
                    System.out.println("Niestety nie mogę Ci w tym pomóc :(");
                }
            }
        } while (!close);
    }
}
