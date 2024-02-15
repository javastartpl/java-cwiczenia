package pl.javastart.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MathAddQuestionEvaluator extends QuestionEvaluator {
    private static final Pattern PATTERN = Pattern.compile(
            "[Jj]aki jest wynik dodawania (?<a>\\d+) do (?<b>\\d+)\\??"
    );

    public MathAddQuestionEvaluator() {
        super(PATTERN);
    }

    @Override
    String evaluateQuestion(String input, Matcher matcher) {
        int a = Integer.parseInt(matcher.group("a"));
        int b = Integer.parseInt(matcher.group("b"));
        int sum = a + b;
        return "Wynik dodawania %d + %d to %d".formatted(a, b, sum);
    }
}
