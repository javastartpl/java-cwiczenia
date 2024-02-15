package pl.javastart.regex;

class QuestionEvaluatorFacade {
    private static final QuestionEvaluator[] EVALUATORS = {
            new MathAddQuestionEvaluator(),
            new TodayQuestionEvaluator(),
            new TextCountQuestionEvaluator()
    };

    static String evaluate(String input) {
        for (QuestionEvaluator evaluator : EVALUATORS) {
            if (evaluator.match(input)) {
                return evaluator.evaluate(input);
            }
        }
        throw new IllegalArgumentException("QuestionEvaluator for provided input not found");
    }
}
