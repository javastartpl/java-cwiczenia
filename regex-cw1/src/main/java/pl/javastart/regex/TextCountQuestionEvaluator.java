package pl.javastart.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TextCountQuestionEvaluator extends QuestionEvaluator {
    private static final Pattern PATTERN = Pattern.compile(
            "[Ii]le znaków ma tekst \"(?<text>.*)\"\\??"
    );

    public TextCountQuestionEvaluator() {
        super(PATTERN);
    }

    @Override
    String evaluateQuestion(String input, Matcher matcher) {
        String text = matcher.group("text");
        int textLength = text.length();
        int whitespace = text.replaceAll("\\S", "").length();
        int nonWhitespace = textLength - whitespace;
        return "Tekst ma %d znaków (czarne: %d, białe: %d)"
                .formatted(textLength, nonWhitespace, whitespace);
    }
}
