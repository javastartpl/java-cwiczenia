package pl.javastart.regex;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TodayQuestionEvaluator extends QuestionEvaluator {
    private final static Pattern PATTERN = Pattern.compile(
            "[Jj]aki dziś dzień tygodnia\\??"
    );

    public TodayQuestionEvaluator() {
        super(PATTERN);
    }

    @Override
    String evaluateQuestion(String input, Matcher matcher) {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String displayDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.of("pl"));
        return "Dziś jest " + displayDay;
    }
}
